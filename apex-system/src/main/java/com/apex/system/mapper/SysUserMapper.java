package com.apex.system.mapper;

import com.apex.common.mybatis.core.BaseMapper;
import com.apex.system.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户 Mapper
 *
 * @author apex
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
}
