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
public class create implements Serializable {
    private static final long serialVersionUID = 1L;

<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>

    <#if field.keyIdentityFlag>
        <#continue>
    </#if>
    <#assign fieldPatch=patch[field.name] >
    <#if fieldPatch._default?? && fieldPatch._default == "CURRENT_TIMESTAMP">
        <#continue>
    </#if>

    <#if field.comment!?length gt 0>
    /**
     * ${field.comment}
     */
    </#if>
        <#-- 普通字段 -->
    <#if fieldPatch.isNull == "no" && fieldPatch._default?default("") == "">
    @NotNull
        <#if field.propertyType == "String">
    @NotBlank
        </#if>
    <#elseif field.convert>
    @TableField("${field.name}")
    </#if>
    private ${field.propertyType} ${field.propertyName};
</#list>
<#------------  END 字段循环遍历  ---------->

}