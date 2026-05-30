package com.apex.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.apex.common.core.domain.PageResult;
import com.apex.common.core.exception.BusinessException;
import com.apex.common.core.utils.SecurityUtils;
import com.apex.common.core.utils.StringUtils;
import com.apex.common.mybatis.core.BaseServiceImpl;
import com.apex.system.domain.SysUser;
import com.apex.system.domain.dto.UserDTO;
import com.apex.system.domain.vo.UserVO;
import com.apex.system.mapper.SysUserMapper;
import com.apex.system.service.SysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户服务实现
 *
 * @author apex
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    /**
     * 分页查询用户列表
     */
    @Override
    public PageResult<UserVO> selectUserList(UserDTO userDTO) {
        Page<SysUser> page = new Page<>(userDTO.getPageNum(), userDTO.getPageSize());

        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.isNotEmpty(userDTO.getUsername()), SysUser::getUsername, userDTO.getUsername());
        wrapper.like(StringUtils.isNotEmpty(userDTO.getNickname()), SysUser::getNickname, userDTO.getNickname());
        wrapper.like(StringUtils.isNotEmpty(userDTO.getPhone()), SysUser::getPhone, userDTO.getPhone());
        wrapper.eq(userDTO.getStatus() != null, SysUser::getStatus, userDTO.getStatus());
        wrapper.eq(userDTO.getDeptId() != null, SysUser::getDeptId, userDTO.getDeptId());
        wrapper.orderByAsc(SysUser::getUserId);

        Page<SysUser> result = baseMapper.selectPage(page, wrapper);

        List<UserVO> voList = result.getRecords().stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());

        return new PageResult<>(result.getTotal(), voList, userDTO.getPageNum(), userDTO.getPageSize());
    }

    /**
     * 根据用户ID查询用户信息
     */
    @Override
    public UserVO selectUserById(Long userId) {
        SysUser user = baseMapper.selectById(userId);
        if (user == null) {
            throw new BusinessException("用户不存在");
        }
        return convertToVO(user);
    }

    /**
     * 根据用户名查询用户信息
     */
    @Override
    public SysUser selectUserByUsername(String username) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUsername, username);
        return baseMapper.selectOne(wrapper);
    }

    /**
     * 新增用户
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long insertUser(UserDTO userDTO) {
        // 校验用户名唯一
        if (!checkUsernameUnique(userDTO.getUsername())) {
            throw new BusinessException("用户名已存在");
        }

        // 校验手机号唯一
        if (StringUtils.isNotEmpty(userDTO.getPhone()) && !checkPhoneUnique(userDTO.getPhone())) {
            throw new BusinessException("手机号已存在");
        }

        // 校验邮箱唯一
        if (StringUtils.isNotEmpty(userDTO.getEmail()) && !checkEmailUnique(userDTO.getEmail())) {
            throw new BusinessException("邮箱已存在");
        }

        SysUser user = new SysUser();
        user.setUsername(userDTO.getUsername());
        user.setNickname(userDTO.getNickname());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setSex(userDTO.getSex());
        user.setAvatar(userDTO.getAvatar());
        user.setDeptId(userDTO.getDeptId());
        user.setStatus(userDTO.getStatus() != null ? userDTO.getStatus() : 0);
        user.setRemark(userDTO.getRemark());

        // 加密密码
        String password = StringUtils.isNotEmpty(userDTO.getPassword()) ? userDTO.getPassword() : "123456";
        user.setPassword(SecurityUtils.encryptPassword(password));

        // 设置创建信息
        user.setCreateBy(SecurityUtils.getUsername());
        user.setCreateTime(LocalDateTime.now());

        baseMapper.insert(user);

        // TODO: 保存用户角色关联
        // TODO: 保存用户岗位关联

        log.info("新增用户成功: {}", user.getUsername());
        return user.getUserId();
    }

    /**
     * 修改用户
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateUser(UserDTO userDTO) {
        SysUser user = baseMapper.selectById(userDTO.getUserId());
        if (user == null) {
            throw new BusinessException("用户不存在");
        }

        // 校验手机号唯一
        if (StringUtils.isNotEmpty(userDTO.getPhone()) && !checkPhoneUnique(userDTO.getPhone())) {
            throw new BusinessException("手机号已存在");
        }

        // 校验邮箱唯一
        if (StringUtils.isNotEmpty(userDTO.getEmail()) && !checkEmailUnique(userDTO.getEmail())) {
            throw new BusinessException("邮箱已存在");
        }

        user.setNickname(userDTO.getNickname());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setSex(userDTO.getSex());
        user.setAvatar(userDTO.getAvatar());
        user.setDeptId(userDTO.getDeptId());
        user.setStatus(userDTO.getStatus());
        user.setRemark(userDTO.getRemark());

        // 设置更新信息
        user.setUpdateBy(SecurityUtils.getUsername());
        user.setUpdateTime(LocalDateTime.now());

        boolean result = baseMapper.updateById(user) > 0;

        // TODO: 更新用户角色关联
        // TODO: 更新用户岗位关联

        if (result) {
            log.info("修改用户成功: {}", user.getUsername());
        }

        return result;
    }

    /**
     * 删除用户
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteUserByIds(List<Long> userIds) {
        // 检查是否包含管理员
        if (userIds.contains(1L)) {
            throw new BusinessException("不允许删除管理员用户");
        }

        boolean result = baseMapper.deleteBatchIds(userIds) > 0;

        // TODO: 删除用户角色关联
        // TODO: 删除用户岗位关联

        if (result) {
            log.info("删除用户成功: {}", userIds);
        }

        return result;
    }

    /**
     * 重置用户密码
     */
    @Override
    public boolean resetUserPwd(Long userId, String password) {
        SysUser user = baseMapper.selectById(userId);
        if (user == null) {
            throw new BusinessException("用户不存在");
        }

        user.setPassword(SecurityUtils.encryptPassword(password));
        user.setUpdateBy(SecurityUtils.getUsername());
        user.setUpdateTime(LocalDateTime.now());

        boolean result = baseMapper.updateById(user) > 0;
        if (result) {
            log.info("重置用户密码成功: {}", user.getUsername());
        }

        return result;
    }

    /**
     * 修改用户状态
     */
    @Override
    public boolean updateUserStatus(Long userId, Integer status) {
        SysUser user = baseMapper.selectById(userId);
        if (user == null) {
            throw new BusinessException("用户不存在");
        }

        user.setStatus(status);
        user.setUpdateBy(SecurityUtils.getUsername());
        user.setUpdateTime(LocalDateTime.now());

        boolean result = baseMapper.updateById(user) > 0;
        if (result) {
            log.info("修改用户状态成功: {} -> {}", user.getUsername(), status == 0 ? "正常" : "停用");
        }

        return result;
    }

    /**
     * 校验用户名是否唯一
     */
    @Override
    public boolean checkUsernameUnique(String username) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUsername, username);
        return baseMapper.selectCount(wrapper) == 0;
    }

    /**
     * 校验手机号是否唯一
     */
    @Override
    public boolean checkPhoneUnique(String phone) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getPhone, phone);
        return baseMapper.selectCount(wrapper) == 0;
    }

    /**
     * 校验邮箱是否唯一
     */
    @Override
    public boolean checkEmailUnique(String email) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getEmail, email);
        return baseMapper.selectCount(wrapper) == 0;
    }

    /**
     * 转换为 VO
     */
    private UserVO convertToVO(SysUser user) {
        return UserVO.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .phone(user.getPhone())
                .sex(user.getSex())
                .avatar(user.getAvatar())
                .status(user.getStatus())
                .deptId(user.getDeptId())
                .loginIp(user.getLoginIp())
                .loginDate(user.getLoginDate())
                .createTime(user.getCreateTime())
                .remark(user.getRemark())
                .build();
    }
}
