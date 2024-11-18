package com.stone.it.rcms.auth.service;

import com.stone.it.rcms.auth.vo.AuthAccountVO;
import com.stone.it.rcms.auth.vo.SystemApiVO;
import java.util.List;
import java.util.Set;

/**
 *
 * @author cj.stone
 * @Date 2024/11/1
 * @Desc
 */
public interface IAuthSettingService {

    AuthAccountVO getUserInfoByUserId(String accountCode);

    List<SystemApiVO> getApiPathByRoleCodes(Set<String> roleSets);

}
