package ${package.Controller?remove_ending("controller")}api;

import com.loeyae.cloud.commons.common.ApiResult;
import com.loeyae.cloud.commons.common.PageResult;
import ${package.Controller?remove_ending(".controller")}.DTO.*;
import ${package.Controller?remove_ending(".controller")}.VO.${table.entityName}View;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/")
    ApiResult<${table.entityName}View> create(@RequestBody ${table.entityName}Create data);

    @PutMapping("/{id}")
    ApiResult<${table.entityName}View> update(@PathVariable("id") int id, @RequestBody ${table.entityName}Update data);

    @GetMapping("/{id}")
    ApiResult<${table.entityName}View> get(@PathVariable("id") int id);

    @DeleteMapping("/{id}")
    ApiResult<Integer> delete(@PathVariable("id") int id);

    @GetMapping("/")
    ApiResult<${table.entityName}View> one(${table.entityName}Query data);

    @GetMapping("/all/")
    ApiResult<List<${table.entityName}View>> all(${table.entityName}Query data);

    @GetMapping("/page/")
    ApiResult<PageResult<${table.entityName}View>> page(${table.entityName}Query data);
}
