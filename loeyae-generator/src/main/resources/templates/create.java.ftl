package ${package.Controller?remove_ending("controller")}DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.Length;
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
public class ${table.entityName}Create implements Serializable {
    private static final long serialVersionUID = 1L;

<#-- ----------  BEGIN 字段循环遍历  ---------->

<#assign Int=["Integer", "Long", "Short"]>
<#assign Dec=["Float", "BigDecimal", "Double"]>
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
    <#if fieldPatch.isNull == "no" && fieldPatch._default?default("null") == "null">
    @NotNull
        <#if field.propertyType == "String">
    @NotBlank
        </#if>
    </#if>
    <#if field.propertyType == "String">
        <#if fieldPatch.length?default("0")?number gt 0>
    @Length(max = ${fieldPatch.length})
        </#if>
    <#elseif Int?seq_contains(field.propertyType)>
        <#if fieldPatch.restrain?default("null")?lower_case == "unsigned">
    @PositiveOrZero
        </#if>
    <#elseif Dec?seq_contains(field.propertyType)>
        <#if fieldPatch.restrain?default("null")?lower_case == "unsigned">
    @PositiveOrZero
        </#if>
    @Digits(integer = ${fieldPatch.length?default(0)}, fraction = ${fieldPatch.scale?default(0)})
    </#if>
    private ${field.propertyType} ${field.propertyName};
</#list>
<#------------  END 字段循环遍历  ---------->

}