package com.apex.log.service;

import com.apex.common.core.domain.PageResult;
import com.apex.common.mybatis.core.BaseService;
import com.apex.log.domain.SysOperLog;

public interface SysOperLogService extends BaseService<SysOperLog> {
    PageResult<SysOperLog> selectOperLogList(SysOperLog operLog, Integer pageNum, Integer pageSize);
    void insertOperLog(SysOperLog operLog);
    boolean deleteOperLogByIds(Long[] operIds);
    boolean cleanOperLog();
}
