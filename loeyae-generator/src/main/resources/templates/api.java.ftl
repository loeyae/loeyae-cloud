package ${package.Controller?remove_ending("controller")}api;

import com.loeyae.cloud.commons.common.ApiResult;
import com.loeyae.cloud.commons.common.PageResult;
import ${package.Controller?remove_ending(".controller")}.VO.${table.entityName}View;

import java.util.List;

/**
 * <p>
 * ${table.comment!} 接口定义
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
public interface ${table.entityName}Api {

    @PostMapping("/api/${table.entityName?lower_case}")
    ApiResult<${table.entityName}View> create(@RequestBody ${table.entityName}Create data);

    @PutMapping("/api/${table.entityName?lower_case}/{id}")
    ApiResult<${table.entityName}View> update(@PathVariable("id") int id, @RequestBody ${table.entityName}Update data);

    @GetMapping("/api/${table.entityName?lower_case}/{id}")
    ApiResult<${table.entityName}View> get(@PathVariable("id") int id);

    @DeleteMapping("/api/${table.entityName?lower_case}/{id}")
    ApiResult<Integer> delete(@PathVariable("id") int id);

    @GetMapping("/api/${table.entityName?lower_case}")
    ApiResult<${table.entityName}View> one(${table.entityName}Query data);

    @GetMapping("/api/${table.entityName?lower_case}/all")
    ApiResult<List<${table.entityName}View>> all(${table.entityName}Query data);

    @GetMapping("/api/${table.entityName?lower_case}/page")
    ApiResult<PageResult<${table.entityName}View>> page(${table.entityName}Query data);
}
