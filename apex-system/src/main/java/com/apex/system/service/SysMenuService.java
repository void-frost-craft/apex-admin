package com.apex.system.service;

import com.apex.common.mybatis.core.BaseService;
import com.apex.system.domain.SysMenu;

import java.util.List;
import java.util.Set;

/**
 * 菜单服务接口
 *
 * @author apex
 */
public interface SysMenuService extends BaseService<SysMenu> {

    /**
     * 查询菜单列表
     *
     * @param menu 菜单信息
     * @return 菜单列表
     */
    List<SysMenu> selectMenuList(SysMenu menu);

    /**
     * 查询菜单树
     *
     * @param menu 菜单信息
     * @return 菜单树
     */
    List<SysMenu> selectMenuTree(SysMenu menu);

    /**
     * 根据用户ID查询菜单列表
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    List<SysMenu> selectMenuListByUserId(Long userId);

    /**
     * 根据用户ID查询权限列表
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    Set<String> selectMenuPermsByUserId(Long userId);

    /**
     * 根据角色ID查询菜单列表
     *
     * @param roleId 角色ID
     * @return 菜单列表
     */
    List<SysMenu> selectMenuListByRoleId(Long roleId);

    /**
     * 根据菜单ID查询菜单信息
     *
     * @param menuId 菜单ID
     * @return 菜单信息
     */
    SysMenu selectMenuById(Long menuId);

    /**
     * 新增菜单
     *
     * @param menu 菜单信息
     * @return 菜单ID
     */
    Long insertMenu(SysMenu menu);

    /**
     * 修改菜单
     *
     * @param menu 菜单信息
     * @return 是否成功
     */
    boolean updateMenu(SysMenu menu);

    /**
     * 删除菜单
     *
     * @param menuId 菜单ID
     * @return 是否成功
     */
    boolean deleteMenuById(Long menuId);

    /**
     * 校验菜单名称是否唯一
     *
     * @param menuName 菜单名称
     * @param parentId 父菜单ID
     * @return 是否唯一
     */
    boolean checkMenuNameUnique(String menuName, Long parentId);

    /**
     * 是否有子菜单
     *
     * @param menuId 菜单ID
     * @return 是否有子菜单
     */
    boolean hasChildByMenuId(Long menuId);

    /**
     * 是否有角色使用该菜单
     *
     * @param menuId 菜单ID
     * @return 是否有角色使用
     */
    boolean checkMenuExistRole(Long menuId);
}
