package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色表 数据层
 *
 * @author ruoyi
 */
@Mapper
public interface RoleMapper {
    /**
     * 根据条件分页查询角色数据
     *
     * @param role 角色信息
     * @return 角色数据集合信息
     */
    List<Role> selectRoleList(Role role);

    /**
     * 根据用户ID查询角色
     *
     * @param userId 用户ID
     * @return 角色列表
     */
    List<Role> selectRolesByUserId(Long userId);

    /**
     * 通过角色ID查询角色
     *
     * @param roleId 角色ID
     * @return 角色对象信息
     */
    Role selectRoleById(Long roleId);

    /**
     * 通过角色ID删除角色
     *
     * @param roleId 角色ID
     * @return 结果
     */
    int deleteRoleById(Long roleId);

    /**
     * 批量角色用户信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteRoleByIds(Long[] ids);

    /**
     * 修改角色信息
     *
     * @param role 角色信息
     * @return 结果
     */
    int updateRole(Role role);

    /**
     * 新增角色信息
     *
     * @param role 角色信息
     * @return 结果
     */
    int insertRole(Role role);

    /**
     * 校验角色名称是否唯一
     *
     * @param roleName 角色名称
     * @return 角色信息
     */
    Role checkRoleNameUnique(String roleName);

    /**
     * 校验角色权限是否唯一
     *
     * @param roleKey 角色权限
     * @return 角色信息
     */
    Role checkRoleKeyUnique(String roleKey);

    /**
     * 清除默认
     */
    void clearDefault();

    /**
     * 查找默认角色
     *
     * @return 默认角色，若不存在则返回null
     */
    Role findDefault();
}
