package com.stone.it.rcms.admin.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.stone.it.rcms.admin.dao.ILoginDao;
import com.stone.it.rcms.admin.service.ILoginService;
import com.stone.it.rcms.admin.vo.AccountSecretVO;
import com.stone.it.rcms.admin.vo.AppSecretVO;
import com.stone.it.rcms.admin.vo.LoginResponseVO;
import com.stone.it.rcms.core.exception.RcmsApplicationException;
import com.stone.it.rcms.core.util.DateUtil;
import com.stone.it.rcms.core.util.JwtUtils;
import com.stone.it.rcms.core.util.ResponseUtil;
import com.stone.it.rcms.user.vo.AuthAccountVO;
import com.stone.it.rcms.user.vo.EnterpriseDetailVO;
import org.apache.http.HttpStatus;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Map;

/**
 * @author cj.stone
 * @Date 2025/1/6
 * @Desc
 */
@Named
public class LoginService extends BaseService implements ILoginService {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginService.class);

	@Inject
	private ILoginDao loginDao;

	@Override
	public LoginResponseVO accountLogin(AccountSecretVO userVO) {
		// 登录认证
		String sessionId = subjectLogin(userVO.getAccount(), userVO.getPassword(), "account");
		// 获取用户信息
		AuthAccountVO dbUser = loginDao.findAccountInfoById(userVO.getAccount());
		Calendar expTime = JwtUtils.getExpireTime(60 * 5);
		String accessToken = buildJwtToken(sessionId, userVO.getAccount(), dbUser.getTenantId(), "account", expTime,
		                                   dbUser.getEnterpriseId(), userVO.getPassword());
		String refreshToken = buildJwtToken(sessionId, userVO.getAccount(), dbUser.getTenantId(), "account",
		                                    JwtUtils.getExpireTime(60 * 10), dbUser.getEnterpriseId(), userVO.getPassword());
		LoginResponseVO loginResVO = new LoginResponseVO();
		loginResVO.setAccessToken(accessToken);
		loginResVO.setRefreshToken(refreshToken);
		loginResVO.setUsername(dbUser.getAccountCode());
		loginResVO.setNickname(dbUser.getAccountName());
		if ("".equals(dbUser.getAccountRoles()) || null == dbUser.getAccountRoles()) {
			throw new RcmsApplicationException(500, "用户角色为空，请联系管理员！");
		}
		String[] roles = (dbUser.getAccountRoles()).split(",");
		ArrayList<String> roleList = new ArrayList<>();
		Collections.addAll(roleList, roles);
		loginResVO.setRoles(roleList);
		ArrayList<String> permissions = new ArrayList<String>();
		if (dbUser.getAccountRoles().contains("platformAdmin")) {
			permissions.add("*:*:*");
		} else {
			permissions.addAll(loginDao.findPermsByRoles(roleList));
		}
		loginResVO.setPermissions(permissions);
		loginResVO.setExpires(DateUtil.formatDate(expTime.getTime(), "yyyy-MM-dd HH:mm:ss"));
		loginResVO.setEnterpriseId(dbUser.getEnterpriseId());
		// 查询当前登录用户的企业信息
		EnterpriseDetailVO extraInfo = loginDao.findEnterpriseDetailById(dbUser.getEnterpriseId());
		loginResVO.setExtraInfo(extraInfo);
		return loginResVO;
	}

	@Override
	public LoginResponseVO accountLoginRefresh(LoginResponseVO loginResVO) {
		LoginResponseVO newLoginResVO;
		Map<String, Object> verify = JwtUtils.verifyToken(loginResVO.getRefreshToken());
		if (!(boolean)verify.get("state")) {
			throw new RcmsApplicationException(401, "请求认证已失效", verify.get("msg"));
		}
		Map<String, String> user = JwtUtils.getTokenInfo(loginResVO.getRefreshToken());
		AccountSecretVO userVO = new AccountSecretVO();
		userVO.setAccount(user.get("account"));
		userVO.setPassword(user.get("password"));
		// 先退出上次登录
		accountLogout();
		// 重新登录
		LoginResponseVO resVO = accountLogin(userVO);
		newLoginResVO = new LoginResponseVO(resVO.getAccessToken(), resVO.getRefreshToken(), resVO.getExpires());
		return newLoginResVO;
	}

	@Override
	public JSONObject appToken(AppSecretVO appSecretVO) {
		String sessionId = subjectLogin(appSecretVO.getAppId(), appSecretVO.getSecret(), "app");
		if (sessionId == null) {
			return null;
		}
		AuthAccountVO dbUser = loginDao.findAccountInfoById(appSecretVO.getAppId());
		JSONObject result = new JSONObject();
		Calendar expTime = JwtUtils.getExpireTime(60 * 30);
		String accessToken = buildJwtToken(sessionId, appSecretVO.getAppId(), dbUser.getTenantId(), "app", expTime,
		                                   dbUser.getEnterpriseId(), appSecretVO.getSecret());
		result.put("Authorization", accessToken);
		result.put("expires", DateUtil.formatDate(expTime.getTime(), "yyyy-MM-dd HH:mm:ss"));
		return result;
	}

	@Override
	public JSONObject accountLogout() {
		Subject currentUser = SecurityUtils.getSubject();
		try {
			PrincipalCollection principals = currentUser.getPrincipals();
			if (principals == null) {
				return ResponseUtil.responseBuild(HttpStatus.SC_INTERNAL_SERVER_ERROR, "您当前未登录！");
			}
			@SuppressWarnings("unchecked")
			Map<String, String> user = (Map<String, String>)principals.getPrimaryPrincipal();
			// 数据库记录日志，执行退出
			currentUser.logout();
			return ResponseUtil.responseBuild(HttpStatus.SC_OK, "退出成功！");
		} catch (Exception e) {
			LOGGER.error("user login error.", e);
			return ResponseUtil.responseBuild(HttpStatus.SC_INTERNAL_SERVER_ERROR, "退出失败！", e.getMessage());
		}
	}

}
