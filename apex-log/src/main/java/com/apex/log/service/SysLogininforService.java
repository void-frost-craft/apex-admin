package com.apex.log.service;

import com.apex.common.core.domain.PageResult;
import com.apex.common.mybatis.core.BaseService;
import com.apex.log.domain.SysLogininfor;

public interface SysLogininforService extends BaseService<SysLogininfor> {
    PageResult<SysLogininfor> selectLogininforList(SysLogininfor logininfor, Integer pageNum, Integer pageSize);
    void insertLogininfor(SysLogininfor logininfor);
    boolean deleteLogininforByIds(Long[] infoIds);
    boolean cleanLogininfor();
}
