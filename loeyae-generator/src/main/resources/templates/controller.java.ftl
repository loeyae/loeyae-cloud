package ${package.Controller};

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.RequestMapping;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.loeyae.cloud.commons.common.ApiResult;
import com.loeyae.cloud.commons.common.PageResult;
import com.loeyae.cloud.commons.tool.BeanUtils;
import com.loeyae.cloud.commons.tool.QueryWapperUtils;
import com.loeyae.cloud.commons.tool.ValidateUtil;
import com.loeyae.cloud.commons.validation.*;

import ${package.Controller?remove_ending(".controller")}.DTO.*;
import ${package.Controller?remove_ending(".controller")}.VO.${table.entityName}View;
import ${package.Controller?remove_ending(".controller")}.api.${table.entityName}Api;
import ${package.Entity}.${table.entityName};
import ${package.Service}.${table.serviceName};

import org.springframework.beans.factory.annotation.Autowired;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>
import java.util.List;

/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} implements ${table.entityName}Api {
</#if>

    @Autowired
    ${table.serviceName} ${table.entityName?uncap_first}Service;

    /**
     * Create
     *
     * @param data
     * @return
     */
    @Override
    public ApiResult<${table.entityName}View> create(${table.entityName}Create data) {
        ValidateUtil.validateEntity(data);
        ${table.entityName} entity = BeanUtils.copyToEntity(data, ${table.entityName}.class);
        ${table.entityName?uncap_first}Service.save(entity);
        return ApiResult.ok(BeanUtils.copyToEntity(entity, ${table.entityName}View.class));
    }

    /**
     * Update
     *
     * @param id
     * @param data
     * @return
     */
    @Override
    public ApiResult<${table.entityName}View> update(int id, ${table.entityName}Update data) {
        ValidateUtil.validateParameter(${table.entityName}Primary.class, "id", id, Primary.class);
        ValidateUtil.validateEntity(data);
        ${table.entityName} entity = BeanUtils.copyToEntity(data, ${table.entityName}.class);
        entity.setId(id);
        ${table.entityName?uncap_first}Service.updateById(entity);
        return ApiResult.ok(BeanUtils.copyToEntity(entity, ${table.entityName}View.class));
    }

    /**
     * Get
     *
     * @param id
     * @return
     */
    @Override
    public ApiResult<${table.entityName}View> get(int id)
    {
        ${table.entityName} entity = ${table.entityName?uncap_first}Service.getById(id);
        return ApiResult.ok(BeanUtils.copyToEntity(entity, ${table.entityName}View.class));
    }

    /**
     * Delete
     *
     * @param id
     * @return
     */
    @Override
    public ApiResult<Integer> delete(int id)
    {
        boolean ret = ${table.entityName?uncap_first}Service.removeById(id);
        return ApiResult.ok(ret ? 1 : 0);
    }

    /**
     * One
     *
     * @param data
     * @return
     */
    @Override
    public ApiResult<${table.entityName}View> one(${table.entityName}Query data)
    {
        ValidateUtil.validateEntity(data);
        QueryWrapper<${table.entityName}> queryWrapper = QueryWapperUtils.queryToWrapper(data, ${table.entityName}.class);
        List<${table.entityName}> entities = ${table.entityName?uncap_first}Service.list(queryWrapper);
        if (!entities.isEmpty()) {
            return ApiResult.ok(BeanUtils.copyToEntity(entities.get(0), ${table.entityName}View.class));
        }
        return ApiResult.ok(null);
    }

    /**
     * Page
     *
     * @param data
     * @return
     */
    @Override
    public ApiResult<PageResult<${table.entityName}View>> page(${table.entityName}Query data)
    {
        ValidateUtil.validateEntity(data);
        QueryWrapper<${table.entityName}> queryWrapper = QueryWapperUtils.queryToWrapper(data, ${table.entityName}.class);
        Page<${table.entityName}> page = new Page<>();
        page.setCurrent(0);
        page.setSize(20);
        IPage<${table.entityName}> pageResult = ${table.entityName?uncap_first}Service.page(page, queryWrapper);
        return ApiResult.ok(PageResult.fromPage(pageResult, ${table.entityName}View.class));
    }

    /**
     * All
     *
     * @param data
     * @return
     */
    @Override
    public ApiResult<List<${table.entityName}View>> all(${table.entityName}Query data)
    {
        ValidateUtil.validateEntity(data);
        QueryWrapper<${table.entityName}> queryWrapper = QueryWapperUtils.queryToWrapper(data, ${table.entityName}.class);
        List<${table.entityName}> result = ${table.entityName?uncap_first}Service.list(queryWrapper);
        return ApiResult.ok(BeanUtils.copyObjListProperties(result, ${table.entityName}View.class));
    }
}
</#if>
