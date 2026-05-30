package com.apex.system.service;

import com.apex.common.core.domain.PageResult;
import com.apex.common.mybatis.core.BaseService;
import com.apex.system.domain.SysUser;
import com.apex.system.domain.dto.UserDTO;
import com.apex.system.domain.vo.UserVO;

import java.util.List;

/**
 * 用户服务接口
 *
 * @author apex
 */
public interface SysUserService extends BaseService<SysUser> {

    /**
     * 分页查询用户列表
     *
     * @param userDTO 查询参数
     * @return 用户列表
     */
    PageResult<UserVO> selectUserList(UserDTO userDTO);

    /**
     * 根据用户ID查询用户信息
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    UserVO selectUserById(Long userId);

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    SysUser selectUserByUsername(String username);

    /**
     * 新增用户
     *
     * @param userDTO 用户信息
     * @return 用户ID
     */
    Long insertUser(UserDTO userDTO);

    /**
     * 修改用户
     *
     * @param userDTO 用户信息
     * @return 是否成功
     */
    boolean updateUser(UserDTO userDTO);

    /**
     * 删除用户
     *
     * @param userIds 用户ID列表
     * @return 是否成功
     */
    boolean deleteUserByIds(List<Long> userIds);

    /**
     * 重置用户密码
     *
     * @param userId 用户ID
     * @param password 新密码
     * @return 是否成功
     */
    boolean resetUserPwd(Long userId, String password);

    /**
     * 修改用户状态
     *
     * @param userId 用户ID
     * @param status 状态
     * @return 是否成功
     */
    boolean updateUserStatus(Long userId, Integer status);

    /**
     * 校验用户名是否唯一
     *
     * @param username 用户名
     * @return 是否唯一
     */
    boolean checkUsernameUnique(String username);

    /**
     * 校验手机号是否唯一
     *
     * @param phone 手机号
     * @return 是否唯一
     */
    boolean checkPhoneUnique(String phone);

    /**
     * 校验邮箱是否唯一
     *
     * @param email 邮箱
     * @return 是否唯一
     */
    boolean checkEmailUnique(String email);
}
