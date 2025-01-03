package fun.sssdnsy.controller;

import fun.sssdnsy.repository.dataobject.DocumentDO;
import fun.sssdnsy.repository.mapper.DocumentMapper;
import lombok.RequiredArgsConstructor;
import org.dromara.easyes.core.conditions.select.LambdaEsQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import utils.snow.SnowIdUtils;

import java.util.List;

/**
 * 测试使用Easy-ES
 **/
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TestUseEeController {

    private final DocumentMapper documentMapper;

    @GetMapping("/createIndex")
    public Boolean createIndex() {
        // 初始化-> 创建索引
        return documentMapper.createIndex();
    }

    @PostMapping("/insert")
    public Integer insert(@RequestBody DocumentDO document) {
        // 初始化-> 新增数据
        document.setId(SnowIdUtils.nextStringID());
        return documentMapper.insert(document);
    }

    @PostMapping("/search")
    public List<DocumentDO> search(@RequestBody DocumentDO document) {
        // 查询出所有标题为老汉的文档列表
        LambdaEsQueryWrapper<DocumentDO> wrapper = new LambdaEsQueryWrapper<>();
        wrapper.like(DocumentDO::getTitle, document.getTitle())
                .or(i->i.like(DocumentDO::getContent, document.getContent()));
        String source = documentMapper.getSource(wrapper);
        System.out.println(source);
        return documentMapper.selectList(wrapper);
    }
}