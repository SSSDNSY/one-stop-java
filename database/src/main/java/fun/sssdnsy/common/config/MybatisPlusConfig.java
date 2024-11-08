package fun.sssdnsy.common.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author kevin
 * @date 2018/2/22
 */
@Configuration
@MapperScan({"fun.sssdnsy"})
public class MybatisPlusConfig {

    @Bean
    public MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean(
            @Qualifier("customizeDataSource") DataSource dataSource,
            MybatisPlusInterceptor mybatisPlusInterceptor,
            MetaObjectHandler metaObjectHandler) throws Exception {

        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage("fun.sssdnsy");
        sqlSessionFactoryBean.setTypeEnumsPackage("fun.sssdnsy");

        MybatisConfiguration configuration = new MybatisConfiguration();

        configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        // 没有这句可能会导致字段映射有误
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCacheEnabled(false);

        sqlSessionFactoryBean.setConfiguration(configuration);

        sqlSessionFactoryBean.setPlugins(mybatisPlusInterceptor);

        GlobalConfig globalConfig = new GlobalConfig();

        // 自定义填充策略接口实现
        globalConfig.setMetaObjectHandler(metaObjectHandler);
        // 开启SQL解析缓存注解生效，开启后多租户 @SqlParser 注解生效
        // globalConfig.setSqlParserCache(true);

        GlobalConfig.DbConfig dbConfig = new GlobalConfig.DbConfig();
        // 主键类型：从3.3.0版本开始，默认使用雪花算法结合不含中划线的UUID作为ID生成方式。
        dbConfig.setIdType(IdType.ASSIGN_ID);
        // 字段策略
        dbConfig.setUpdateStrategy(FieldStrategy.NOT_EMPTY);
        dbConfig.setInsertStrategy(FieldStrategy.NOT_EMPTY);
        // 驼峰下划线转换
        dbConfig.setTableUnderline(true);
        // 数据库大写下划线转换
        dbConfig.setCapitalMode(true);
        // 序列接口实现类配置
        // globalConfiguration.setKeyGenerator(keyGenerator);

        // 配置逻辑删除(由于mybatis-plus不支持在逻辑删除的时候设置逻辑删除的时间，所以此处不用该功能)
        // globalConfig.setSqlInjector(new LogicSqlInjector());
        // 逻辑删除配置
        dbConfig.setLogicDeleteValue("1");
        dbConfig.setLogicNotDeleteValue("0");

        globalConfig.setDbConfig(dbConfig);
        globalConfig.setBanner(false);
        sqlSessionFactoryBean.setGlobalConfig(globalConfig);

        return sqlSessionFactoryBean;
    }

    @Bean
    public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


    /**
     * 分页插件
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(paginationInnerInterceptor());
        return interceptor;
    }

    private PaginationInnerInterceptor paginationInnerInterceptor() {
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor(DbType.MYSQL);
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
        paginationInnerInterceptor.setMaxLimit(100L);
        // 开启 count 的 join 优化,只针对 left join !!!
        //paginationInnerInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        return paginationInnerInterceptor;
    }

//    @Bean
//    public LogicDeleteInterceptor logicDeleteInterceptor() {
//        return new LogicDeleteInterceptor();
//    }

    /**
     * 自动填充处理器
     */
    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new CustomizeMetaObjectHandler();
    }
}
