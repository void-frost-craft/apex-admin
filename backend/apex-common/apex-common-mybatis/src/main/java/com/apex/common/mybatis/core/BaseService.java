package com.apex.common.mybatis.core;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;
import java.util.Collection;

/**
 * 基础 Service
 *
 * @author apex
 */
public interface BaseService<T> extends IService<T> {

    /**
     * 根据 ID 查询
     */
    T getById(Serializable id);

    /**
     * 根据条件查询一条记录
     */
    T getOne(Wrapper<T> queryWrapper);

    /**
     * 根据条件查询一条记录
     */
    T getOne(Wrapper<T> queryWrapper, boolean throwEx);

    /**
     * 查询列表
     */
    java.util.List<T> list();

    /**
     * 查询列表
     */
    java.util.List<T> list(Wrapper<T> queryWrapper);

    /**
     * 分页查询
     */
    IPage<T> page(IPage<T> page);

    /**
     * 分页查询
     */
    IPage<T> page(IPage<T> page, Wrapper<T> queryWrapper);

    /**
     * 新增
     */
    boolean save(T entity);

    /**
     * 批量新增
     */
    boolean saveBatch(Collection<T> entityList);

    /**
     * 批量新增
     */
    boolean saveBatch(Collection<T> entityList, int batchSize);

    /**
     * 更新
     */
    boolean updateById(T entity);

    /**
     * 批量更新
     */
    boolean updateBatchById(Collection<T> entityList);

    /**
     * 批量更新
     */
    boolean updateBatchById(Collection<T> entityList, int batchSize);

    /**
     * 根据 ID 删除
     */
    boolean removeById(Serializable id);

    /**
     * 根据 ID 批量删除
     */
    boolean removeByIds(Collection<? extends Serializable> idList);

    /**
     * 根据条件删除
     */
    boolean remove(Wrapper<T> queryWrapper);

    /**
     * 统计数量
     */
    long count();

    /**
     * 根据条件统计数量
     */
    long count(Wrapper<T> queryWrapper);

    /**
     * 根据 ID 判断是否存在
     */
    boolean existsById(Serializable id);

    /**
     * 根据条件判断是否存在
     */
    boolean exists(Wrapper<T> queryWrapper);
}
