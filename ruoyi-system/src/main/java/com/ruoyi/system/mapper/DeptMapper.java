package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * 部门管理 数据层
 *
 * @author ruoyi
 */
@Mapper
public interface DeptMapper {
    /**
     * 查询部门人数
     *
     * @param dept 部门信息
     * @return 结果
     */
    int selectDeptCount(Dept dept);

    /**
     * 查询部门是否存在用户
     *
     * @param deptId 部门ID
     * @return 结果
     */
    int checkDeptExistUser(Long deptId);

    /**
     * 查询部门管理数据
     *
     * @param dept 部门信息
     * @return 部门信息集合
     */
    List<Dept> selectDeptList(Dept dept);

    /**
     * 删除部门管理信息
     *
     * @param deptId 部门ID
     * @return 结果
     */
    int deleteDeptById(Long deptId);

    /**
     * 新增部门信息
     *
     * @param dept 部门信息
     * @return 结果
     */
    int insertDept(Dept dept);

    /**
     * 修改部门信息
     *
     * @param dept 部门信息
     * @return 结果
     */
    int updateDept(Dept dept);

    /**
     * 修改子元素关系
     *
     * @param depts 子元素
     * @return 结果
     */
    int updateDeptChildren(@Param("depts") List<Dept> depts);

    /**
     * 根据部门ID查询信息
     *
     * @param deptId 部门ID
     * @return 部门信息
     */
    Dept selectDeptById(Long deptId);

    /**
     * 校验部门名称是否唯一
     *
     * @param deptName 部门名称
     * @param parentId 父部门ID
     * @return 结果
     */
    Dept checkDeptNameUnique(@Param("deptName") String deptName, @Param("parentId") Long parentId);

    /**
     * 根据角色ID查询部门
     *
     * @param roleId 角色ID
     * @return 部门列表
     */
    List<String> selectRoleDeptTree(Long roleId);

    /**
     * 修改所在部门的父级部门状态
     *
     * @param dept 部门
     */
    void updateDeptStatus(Dept dept);

    /**
     * 根据ID查询所有子部门
     *
     * @param id
     * @return
     */
    List<Dept> selectChildrenDeptById(Long id);

    /**
     * 根据角色编号查询所有部门ID
     *
     * @param roleId
     * @return
     * @author zmr
     */
    Set<String> selectRoleDeptIds(Long roleId);

    /**
     * 清除默认
     */
    void clearDefault();

    /**
     * 查找默认角色
     *
     * @return 默认角色，若不存在则返回null
     */
    Dept findDefault();
}
