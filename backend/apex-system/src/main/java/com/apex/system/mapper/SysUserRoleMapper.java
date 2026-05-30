package com.apex.system.mapper;

import com.apex.common.mybatis.core.BaseMapper;
import com.apex.system.domain.SysUserRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户角色关联 Mapper
 *
 * @author apex
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
}
