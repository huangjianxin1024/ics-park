package com.ruoyi.system.aspect;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.redis.util.RedisUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.Role;
import com.ruoyi.system.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 数据过滤处理
 *
 * @author ruoyi
 */
@Aspect
@Slf4j
@Component
public class DataScopeAspect {

    @Autowired
    private RedisUtils redis;

    /**
     * 全部数据权限
     */
    public static final String DATA_SCOPE_ALL = "1";

    /**
     * 自定数据权限
     */
    public static final String DATA_SCOPE_CUSTOM = "2";

    /**
     * 部门数据权限
     */
    public static final String DATA_SCOPE_DEPT = "3";

    /**
     * 部门及以下数据权限
     */
    public static final String DATA_SCOPE_DEPT_AND_CHILD = "4";

    /**
     * 仅本人数据权限
     */
    public static final String DATA_SCOPE_SELF = "5";

    /**
     * 数据权限过滤关键字
     */
    public static final String DATA_SCOPE = "dataScope";

    /**
     * 配置织入点
     */
    @Pointcut("@annotation(com.ruoyi.common.annotation.DataScope)")
    public void dataScopePointCut() {
    }

    @Before("dataScopePointCut()")
    public void doBefore(JoinPoint point) throws Throwable {
        handleDataScope(point);
    }

    protected void handleDataScope(final JoinPoint joinPoint) {
        // 获得注解
        DataScope controllerDataScope = getAnnotationLog(joinPoint);
        if (controllerDataScope == null) {
            return;
        }
        // 获取当前的用户
        HttpServletRequest request = ServletUtils.getRequest();
        String token = request.getHeader("token");
        User currentUser = redis.get(Constants.ACCESS_TOKEN + token, User.class);
        if (currentUser != null) {
            // 如果是超级管理员，则不过滤数据
            if (!User.isAdmin(currentUser.getUserId())) {
                dataScopeFilter(joinPoint, currentUser, controllerDataScope.deptAlias(), controllerDataScope.userAlias(), controllerDataScope.parkAlias());
            }
        } else {
            log.warn("数据权限拦截失败,执行对象 currentUser is null");
        }
    }

    /**
     * 数据范围过滤
     *
     * @param joinPoint 切点
     * @param user      用户
     * @param userAlias 别名
     */
    public static void dataScopeFilter(JoinPoint joinPoint, User user, String deptAlias, String userAlias, String parkAlias) {
        StringBuilder sqlString = new StringBuilder();
        for (Role role : user.getRoles()) {
            String dataScope = role.getDataScope();
            if (DATA_SCOPE_ALL.equals(dataScope)) {
                if (StringUtils.isNotBlank(parkAlias)) {
                    sqlString = sqlString.append(StringUtils.format(" OR {}.park_id = {} ", parkAlias, user.getParkId()));
                    break;
                }
            } else if (DATA_SCOPE_CUSTOM.equals(dataScope)) {
                if (StringUtils.isNotBlank(deptAlias)) {
                    sqlString.append(StringUtils.format(" OR {}.dept_id IN (SELECT dept_id FROM sys_role_dept WHERE role_id = {} ) AND {}.park_id = {}", deptAlias, role.getRoleId(), parkAlias, user.getParkId()));
                } else {
                    sqlString.append(StringUtils.format(" OR {}.park_id = {}", parkAlias, user.getParkId()));
                }
            } else if (DATA_SCOPE_DEPT.equals(dataScope)) {
                if (StringUtils.isNotBlank(deptAlias)) {
                    sqlString.append(StringUtils.format(" OR {}.dept_id = {} AND {}.park_id = {}", deptAlias, user.getDeptId(), parkAlias, user.getParkId()));
                } else {
                    sqlString.append(StringUtils.format(" OR {}.park_id = {}", parkAlias, user.getParkId()));
                }

            } else if (DATA_SCOPE_DEPT_AND_CHILD.equals(dataScope)) {
                String deptChild = user.getDept().getParentId() + "," + user.getDeptId();
                if (StringUtils.isNotBlank(deptAlias)) {
                    sqlString.append(StringUtils.format(" OR {}.dept_id IN (SELECT dept_id FROM sys_dept WHERE dept_id = {} or ancestors LIKE '%{}%' ) AND {}.park_id = {}", deptAlias, user.getDeptId(), deptChild, parkAlias, user.getParkId()));
                } else {
                    sqlString.append(StringUtils.format(" OR {}.park_id = {}", parkAlias, user.getParkId()));
                }
            } else if (DATA_SCOPE_SELF.equals(dataScope)) {
                if (StringUtils.isNotBlank(userAlias)) {
                    sqlString.append(StringUtils.format(" OR {}.user_id = {} AND {}.park_id = {}", userAlias, user.getUserId(), parkAlias, user.getParkId()));
                } else {
                    if (StringUtils.isNotBlank(deptAlias)) {
                        sqlString.append(StringUtils.format(" OR {}.dept_id IS NULL AND {}.park_id = {}", deptAlias, parkAlias, user.getParkId()));
                    } else {
                        sqlString.append(StringUtils.format(" OR {}.park_id = {}", parkAlias, user.getParkId()));
                    }
                }
            }
        }
        if (StringUtils.isNotBlank(sqlString.toString())) {
            BaseEntity<BaseEntity> baseEntity = (BaseEntity<BaseEntity>) joinPoint.getArgs()[0];
            baseEntity.getParams().put(DATA_SCOPE, " AND (" + sqlString.substring(4) + ")");
        }
    }

    /**
     * 是否存在注解，如果存在就获取
     */
    private DataScope getAnnotationLog(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method != null) {
            return method.getAnnotation(DataScope.class);
        }
        return null;
    }
}