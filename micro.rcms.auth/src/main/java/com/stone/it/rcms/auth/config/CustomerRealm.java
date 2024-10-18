package com.stone.it.rcms.auth.config;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 *
 * @author cj.stone
 * @Date 2024/10/18
 * @Desc
 */
public class CustomerRealm extends AuthorizingRealm {

  /**
   * 授权认证
   * @param authenticationToken
   * @return
   * @throws AuthenticationException
   */

  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
    throws AuthenticationException {
    SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
    return null;
  }
}
