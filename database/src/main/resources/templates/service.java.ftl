package ${package.Service};

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;
import ${package.Entity}.${entity}DO;
import ${cfg.qryPackage}.${entity}Qry;
import ${cfg.coPackage}.${entity}CO;
import ${cfg.cmdPackage}.${entity}Cmd;
import ${cfg.repositoryPackage}.${entity}Repository;
import jakarta.annotation.Resource;
import java.util.List;

/**
* ${table.comment!} 服务类
*
* @author ${author}
* @since ${date}
*/
@Service
public class ${entity}Service {

    @Resource
    private  ${entity}Repository ${entity?lower_case}Repository;

    public Page<${entity}CO> page(${entity}Qry qry) {
        Page page = ${entity?lower_case}Repository.page(qry);
        return page;
    }

    public boolean create(${entity}Cmd cmd) {
        ${entity}DO dataObj = new ${entity}DO();
        BeanUtils.copyProperties(cmd, dataObj);
        boolean    save    = ${entity?lower_case}Repository.save(dataObj);
        return save;
    }

    public boolean update(${entity}Cmd cmd) {
        ${entity}DO dataObj = new ${entity}DO();
        BeanUtils.copyProperties(cmd, dataObj);
        boolean    save    = ${entity?lower_case}Repository.updateById(dataObj);
        return save;
    }

    public ${entity}CO get(String id) {
        ${entity}DO dataObject = ${entity?lower_case}Repository.getById(id);
        ${entity}CO ${entity}CO = new ${entity}CO();
        BeanUtils.copyProperties(dataObject, ${entity}CO);
        return ${entity}CO;
    }

    public void delete(List${r"<"}String${r">"} ids) {
        ${entity?lower_case}Repository.removeByIds(ids);
    }

}