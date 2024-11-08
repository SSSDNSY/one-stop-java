package ${cfg.repositoryPackage};

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ${package.Entity}.${entity}DO;
import ${package.Mapper}.${table.mapperName};
import org.springframework.stereotype.Component;

/**
* ${table.comment!}仓储实现
*
* @author ${author}
* @since ${date}
*/

@Component
public class ${entity}Repository extends ServiceImpl<${table.mapperName},${entity}DO> {

}

