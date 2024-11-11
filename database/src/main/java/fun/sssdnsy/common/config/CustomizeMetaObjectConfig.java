package fun.sssdnsy.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

/**
 * 公共字段填充
 */
public class CustomizeMetaObjectConfig implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {


//        this.setFieldValByName("creator", currentUser.getAccountName(), metaObject);
//        this.setFieldValByName("creatorId", currentUser.getAccountId(), metaObject);
//        this.setFieldValByName("createDt", LocalDateTime.now(), metaObject);
//
//        this.setFieldValByName("lastUpdator", currentUser.getAccountName(), metaObject);
//        this.setFieldValByName("lastUpdatorId", currentUser.getAccountId(), metaObject);
//        this.setFieldValByName("lastUpdateDt", LocalDateTime.now(), metaObject);
//
//        this.setFieldValByName("deleted", RecordConstant.DeleteState.NOT_DELETE, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {

//        this.setFieldValByName("lastUpdator", currentUser.getAccountName(), metaObject);
//        this.setFieldValByName("lastUpdatorId", currentUser.getAccountId(), metaObject);
//        this.setFieldValByName("lastUpdateDt", LocalDateTime.now(), metaObject);
    }

}
