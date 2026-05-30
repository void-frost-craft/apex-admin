package com.apex.system.service;

import com.apex.common.core.domain.PageResult;
import com.apex.common.mybatis.core.BaseService;
import com.apex.system.domain.SysRole;

import java.util.List;

/**
 * 角色服务接口
 *
 * @author apex
 */
public interface SysRoleService extends BaseService<SysRole> {

    /**
     * 分页查询角色列表
     *
     * @param role 角色信息
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @return 角色列表
     */
    PageResult<SysRole> selectRoleList(SysRole role, Integer pageNum, Integer pageSize);

    /**
     * 根据角色ID查询角色信息
     *
     * @param roleId 角色ID
     * @return 角色信息
     */
    SysRole selectRoleById(Long roleId);

    /**
     * 新增角色
     *
     * @param role 角色信息
     * @return 角色ID
     */
    Long insertRole(SysRole role);

    /**
     * 修改角色
     *
     * @param role 角色信息
     * @return 是否成功
     */
    boolean updateRole(SysRole role);

    /**
     * 删除角色
     *
     * @param roleIds 角色ID列表
     * @return 是否成功
     */
    boolean deleteRoleByIds(List<Long> roleIds);

    /**
     * 修改角色状态
     *
     * @param roleId 角色ID
     * @param status 状态
     * @return 是否成功
     */
    boolean updateRoleStatus(Long roleId, Integer status);

    /**
     * 校验角色名称是否唯一
     *
     * @param roleName 角色名称
     * @return 是否唯一
     */
    boolean checkRoleNameUnique(String roleName);

    /**
     * 校验角色权限是否唯一
     *
     * @param roleKey 角色权限
     * @return 是否唯一
     */
    boolean checkRoleKeyUnique(String roleKey);

    /**
     * 根据用户ID查询角色列表
     *
     * @param userId 用户ID
     * @return 角色列表
     */
    List<SysRole> selectRolesByUserId(Long userId);

    /**
     * 授权用户角色
     *
     * @param userId 用户ID
     * @param roleIds 角色ID列表
     * @return 是否成功
     */
    boolean insertUserRoles(Long userId, List<Long> roleIds);

    /**
     * 取消授权用户角色
     *
     * @param userId 用户ID
     * @param roleIds 角色ID列表
     * @return 是否成功
     */
    boolean deleteUserRoles(Long userId, List<Long> roleIds);
}
