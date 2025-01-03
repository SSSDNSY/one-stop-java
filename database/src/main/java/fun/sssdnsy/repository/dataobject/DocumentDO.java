package fun.sssdnsy.repository.dataobject;

import lombok.Data;
import org.dromara.easyes.annotation.IndexId;
import org.dromara.easyes.annotation.IndexName;
import org.dromara.easyes.annotation.rely.IdType;

/**
 * @Description ES数据模型
 * @Since 2025-01-03
 */
@Data
@IndexName("document")
public class DocumentDO {

    /**
     * es中的唯一id
     */
    @IndexId(type = IdType.CUSTOMIZE)
    private String id;

    /**
     * 文档标题
     */
    private String title;
    /**
     * 文档内容
     */
    private String content;

}