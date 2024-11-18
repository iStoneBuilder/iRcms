package com.stone.it.rcms.base.vo;

import com.stone.it.rcms.core.vo.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author cj.stone
 * @Desc
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserVO extends BaseVO {

    // 用户ID（系统生成）
    private String userId;
    // 用户自定义账户
    private String account;
    // 用户昵称
    private String nickName;
    // 密码
    private String password;

    private String name;
    private String phone;
    private String gender;

}
