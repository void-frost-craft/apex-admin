package com.apex.system.service;

import com.apex.common.core.domain.PageResult;
import com.apex.common.mybatis.core.BaseService;
import com.apex.system.domain.SysPost;

import java.util.List;

/**
 * 岗位服务接口
 *
 * @author apex
 */
public interface SysPostService extends BaseService<SysPost> {

    /**
     * 分页查询岗位列表
     *
     * @param post 岗位信息
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @return 岗位列表
     */
    PageResult<SysPost> selectPostList(SysPost post, Integer pageNum, Integer pageSize);

    /**
     * 根据岗位ID查询岗位信息
     *
     * @param postId 岗位ID
     * @return 岗位信息
     */
    SysPost selectPostById(Long postId);

    /**
     * 新增岗位
     *
     * @param post 岗位信息
     * @return 岗位ID
     */
    Long insertPost(SysPost post);

    /**
     * 修改岗位
     *
     * @param post 岗位信息
     * @return 是否成功
     */
    boolean updatePost(SysPost post);

    /**
     * 删除岗位
     *
     * @param postIds 岗位ID列表
     * @return 是否成功
     */
    boolean deletePostByIds(List<Long> postIds);

    /**
     * 校验岗位编码是否唯一
     *
     * @param postCode 岗位编码
     * @return 是否唯一
     */
    boolean checkPostCodeUnique(String postCode);

    /**
     * 校验岗位名称是否唯一
     *
     * @param postName 岗位名称
     * @return 是否唯一
     */
    boolean checkPostNameUnique(String postName);

    /**
     * 根据用户ID查询岗位列表
     *
     * @param userId 用户ID
     * @return 岗位列表
     */
    List<SysPost> selectPostsByUserId(Long userId);
}
