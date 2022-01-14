package ${packageName};

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
*  @author leon
*/
//注意贴一下mp的注解
@TableName("${trueTableName}")
public class ${className} {
<#list attrs as attr>
    <#if attr.name == "id">
        @TableId(type = IdType.AUTO)
        private ${attr.type} ${attr.name};
        <#else>
            private ${attr.type} ${attr.name};
    </#if>
</#list>

<#list attrs as attr>
    public void set${attr.name?cap_first}(${attr.type} ${attr.name}){
    this.${attr.name} = ${attr.name};
    }
    public ${attr.type} get${attr.name?cap_first}(){
    return this.${attr.name};
    }

</#list>

}