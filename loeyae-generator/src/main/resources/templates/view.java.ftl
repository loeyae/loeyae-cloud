package ${package.Controller?remove_ending("controller")}DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

<#list table.importPackages as pkg>
<#if pkg?starts_with("com.baomidou.mybatisplus.annotation")>
    <#continue>
</#if>
import ${pkg};
</#list>

/**
 * <p>
 * ${table.comment!}
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ${table.entityName}View implements Serializable {
    private static final long serialVersionUID = 1L;

<#-- ----------  BEGIN 字段循环遍历  ---------->

<#assign Int=["Integer", "Long", "Short"]>
<#assign Dec=["Float", "BigDecimal", "Double"]>
<#list table.fields as field>

    <#if field.comment!?length gt 0>
    /**
     * ${field.comment}
     */
    </#if>
    private ${field.propertyType} ${field.propertyName};
</#list>
<#------------  END 字段循环遍历  ---------->

}