package com.apex.common.core.domain;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 分页查询参数
 *
 * @author apex
 */
@Data
public class PageQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 当前页码 */
    private Integer pageNum = 1;

    /** 每页条数 */
    private Integer pageSize = 10;

    /** 排序字段 */
    private String orderByColumn;

    /** 排序方向 asc/desc */
    private String isAsc;

    /**
     * 计算偏移量
     */
    public int getOffset() {
        return (pageNum - 1) * pageSize;
    }
}
