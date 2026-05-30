package com.apex.system.service;

import com.apex.common.mybatis.core.BaseService;
import com.apex.system.domain.SysDept;

import java.util.List;

/**
 * 部门服务接口
 *
 * @author apex
 */
public interface SysDeptService extends BaseService<SysDept> {

    /**
     * 查询部门列表
     *
     * @param dept 部门信息
     * @return 部门列表
     */
    List<SysDept> selectDeptList(SysDept dept);

    /**
     * 查询部门树
     *
     * @param dept 部门信息
     * @return 部门树
     */
    List<SysDept> selectDeptTree(SysDept dept);

    /**
     * 根据部门ID查询部门信息
     *
     * @param deptId 部门ID
     * @return 部门信息
     */
    SysDept selectDeptById(Long deptId);

    /**
     * 新增部门
     *
     * @param dept 部门信息
     * @return 部门ID
     */
    Long insertDept(SysDept dept);

    /**
     * 修改部门
     *
     * @param dept 部门信息
     * @return 是否成功
     */
    boolean updateDept(SysDept dept);

    /**
     * 删除部门
     *
     * @param deptId 部门ID
     * @return 是否成功
     */
    boolean deleteDeptById(Long deptId);

    /**
     * 校验部门名称是否唯一
     *
     * @param deptName 部门名称
     * @param parentId 父部门ID
     * @return 是否唯一
     */
    boolean checkDeptNameUnique(String deptName, Long parentId);

    /**
     * 是否有子部门
     *
     * @param deptId 部门ID
     * @return 是否有子部门
     */
    boolean hasChildByDeptId(Long deptId);

    /**
     * 是否有用户使用该部门
     *
     * @param deptId 部门ID
     * @return 是否有用户使用
     */
    boolean checkDeptExistUser(Long deptId);
}
