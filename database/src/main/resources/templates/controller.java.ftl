package ${package.Controller};

import fun.sssdnsy.common.DataResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${cfg.qryPackage}.${entity?replace("DO", "Qry")}Qry;
import ${cfg.cmdPackage}.${entity?replace("DO", "Cmd")}Cmd;
import ${cfg.coPackage}.${entity?replace("DO", "CO")}CO;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import jakarta.annotation.Resource;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>
import ${package.Service}.${entity}Service;

/**
* ${table.comment!}接口
*
* @author ${author}
* @since ${date}
*/
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName?? && package.ModuleName != "">/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

    @Resource
    private ${entity}Service ${entity?lower_case}Service;

    /**
     * 分页查询${table.comment!}
     */
    @PostMapping("/page")
    public DataResponse${r"<"}Page${r"<"}${entity}CO${r">"}${r">"} page(@RequestBody ${entity}Qry qry) {
        Page${r"<"}${entity}CO${r">"} page = ${entity?lower_case}Service.page(qry);
        return DataResponse.of(page);
    }

    /**
     * 创建${table.comment!}
     */
    @PostMapping("/create")
    public DataResponse create(@RequestBody ${entity}Cmd cmd) {
        return DataResponse.of(${entity?lower_case}Service.create(cmd));
    }

    /**
     * 修改${table.comment!}
     */
    @PostMapping("/update")
        public DataResponse modify(@RequestBody ${entity}Cmd cmd) {
        return DataResponse.of(${entity?lower_case}Service.update(cmd));
    }

    /**
     * 根据ID查询${table.comment!}
     */
    @GetMapping("/{id}")
    public DataResponse<${entity}CO> get(@PathVariable String id) {
        ${entity}CO co = ${entity?lower_case}Service.get(id);
        return DataResponse.of(co);
    }

    /**
     * 批量删除${table.comment!}
     */
    @PostMapping("/delete")
    public DataResponse delete(@RequestBody List<String> ids) {
        ${entity?lower_case}Service.delete(ids);
        return DataResponse.success();
    }

}
</#if>
