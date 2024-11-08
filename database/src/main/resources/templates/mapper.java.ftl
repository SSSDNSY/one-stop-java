package ${package.Mapper};

import ${package.Entity}.${entity}DO;
import ${superMapperClassPackage};
import org.apache.ibatis.annotations.Mapper;


/**
* ${table.comment!} Mapper 接口
*
* @author ${author}
* @since ${date}
*/
<#if kotlin>
    interface ${table.mapperName} : ${superMapperClass}<${entity}DO>
<#else>

@Mapper
public interface ${table.mapperName} extends ${superMapperClass}<${entity}DO> {

}
</#if>
