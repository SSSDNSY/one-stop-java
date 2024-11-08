package fun.sssdnsy.common.util;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.util.*;

import static java.io.File.separator;


/**
 * 介绍：CRUD代码生成器 SQL -> Code
 * <p>
 * 用法：运行main()方法输入表名
 */
@UtilityClass
public class CodeGenerator {

    //数据库配置
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/test?serverTimezone=Hongkong&useSSL=false&&characterEncoding=UTF-8";
    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "imi123";

    //模块名
    private static final String PROJECT_NAME = "database";
    //包路径
    private static final String PACKAGE_PATH = "src/main/java/fun/sssdnsy/";

    public static void main(String[] args) throws IOException {
        // 获取项目目录
        String projectDir = System.getProperty("user.dir");
        // 获取项目名前缀
        String projectNamePrefix = projectDir + separator + PROJECT_NAME + separator;
        // 获取当前Git用户名，需要安装了Git
        String gitUserNameCMD = "git config user.name";
        // pojo目录
        String pojodir = STR."\{projectNamePrefix}\{PACKAGE_PATH}repository/dataobject/";
        // mapper目录
        String mapperdir = STR."\{projectNamePrefix}\{PACKAGE_PATH}repository/mapper/";
        // service目录
        String servicedir = STR."\{projectNamePrefix}\{PACKAGE_PATH}service/";
        // controller目录
        String controllerPath = STR."\{projectNamePrefix}\{PACKAGE_PATH}controller";

        // 设置自定义输出目录（分布式项目使用）
        Map<OutputFile, String> pathInfo = new HashMap<>();
        pathInfo.put(OutputFile.entity, pojodir);
        pathInfo.put(OutputFile.mapper, mapperdir);
        pathInfo.put(OutputFile.xml, mapperdir);
        pathInfo.put(OutputFile.service, servicedir);
        pathInfo.put(OutputFile.controller, controllerPath);

        FastAutoGenerator.create(DB_URL, DB_USERNAME, DB_PASSWORD)
                // 全局配置
                .globalConfig(builder -> builder.author(StrUtil.trim(CmdExecutorUtil.cmd(gitUserNameCMD)))
                        .disableOpenDir()
                        .dateType(DateType.TIME_PACK)
                )
                // 包配置
                .packageConfig(builder -> builder.parent("fun.sssdnsy")
                        .entity("repository.dataobject")
                        .mapper("repository.mapper")
                        .xml("repository.mapper")
                        .service("service")
                        .controller("controller")
                        .pathInfo(pathInfo)
                )
                // 策略配置
                .strategyConfig((scanner, builder) -> builder.addInclude(getTables(scanner.apply("请输入表名，多个英文逗号分隔，所有输入 all")))
                        .addTablePrefix(List.of("cs_", "co_","t_"))
                        .entityBuilder().disable()
                        .enableLombok().logicDeleteColumnName("deleted")
                        .enableTableFieldAnnotation().enableFileOverride()
                        .addTableFills(
                                new Column("creator", FieldFill.INSERT),
                                new Column("creator_id", FieldFill.INSERT),
                                new Column("create_dt", FieldFill.INSERT),
                                new Column("last_updator", FieldFill.INSERT_UPDATE),
                                new Column("last_updator_id", FieldFill.INSERT_UPDATE),
                                new Column("last_update_dt", FieldFill.INSERT_UPDATE)
                        ).columnNaming(NamingStrategy.underline_to_camel)
                        .naming(NamingStrategy.underline_to_camel)
                        .mapperBuilder().enableFileOverride().disable()
                        .serviceBuilder().enableFileOverride().disableService().disableServiceImpl()
                        .controllerBuilder().enableRestStyle().enableFileOverride().disable()
                        .build())
                // 注入配置
                .injectionConfig(builder -> {
                    Map<String, Object> customMap = new HashMap<>();
                    customMap.put("coPackage", "fun.sssdnsy.dto.clientobject");
                    customMap.put("qryPackage", "fun.sssdnsy.dto.query");
                    customMap.put("cmdPackage", "fun.sssdnsy.dto.command");
                    customMap.put("repositoryPackage", "fun.sssdnsy.repository");

                    List<CustomFile> customFiles = new ArrayList<>();

                    customFiles.add(new CustomFile.Builder().fileName("CO.java")
                            .filePath(STR."\{projectNamePrefix}\{PACKAGE_PATH}dto/clientobject/")
                            .templatePath("/templates/co.java.ftl").enableFileOverride().build());

                    customFiles.add(new CustomFile.Builder().fileName("Qry.java")
                            .filePath(STR."\{projectNamePrefix}\{PACKAGE_PATH}dto/query/")
                            .templatePath("/templates/qry.java.ftl").enableFileOverride().build());

                    customFiles.add(new CustomFile.Builder().fileName("Cmd.java")
                            .filePath(STR."\{projectNamePrefix}\{PACKAGE_PATH}dto/command/")
                            .templatePath("/templates/cmd.java.ftl").enableFileOverride().build());

                    customFiles.add(new CustomFile.Builder().fileName("DO.java")
                            .filePath(STR."\{projectNamePrefix}\{PACKAGE_PATH}repository/dataobject/")
                            .templatePath("/templates/entity.java.ftl").enableFileOverride().build());

                    customFiles.add(new CustomFile.Builder().fileName("Mapper.java")
                            .filePath(STR."\{projectNamePrefix}\{PACKAGE_PATH}repository/mapper/")
                            .templatePath("/templates/mapper.java.ftl").enableFileOverride().build());

                    customFiles.add(new CustomFile.Builder().fileName("Mapper.xml")
                            .filePath(STR."\{projectNamePrefix}\{PACKAGE_PATH}repository/mapper/")
                            .templatePath("/templates/mapper.xml.ftl").enableFileOverride().build());

                    customFiles.add(new CustomFile.Builder().fileName("Repository.java")
                            .filePath(STR."\{projectNamePrefix}\{PACKAGE_PATH}repository/")
                            .templatePath("/templates/repository.java.ftl").enableFileOverride().build());

                    customFiles.add(new CustomFile.Builder().fileName("Service.java")
                            .filePath(STR."\{projectNamePrefix}\{PACKAGE_PATH}service/")
                            .templatePath("/templates/service.java.ftl").enableFileOverride().build());

                    customFiles.add(new CustomFile.Builder().fileName("Controller.java")
                            .filePath(STR."\{projectNamePrefix}\{PACKAGE_PATH}controller/")
                            .templatePath("/templates/controller.java.ftl").enableFileOverride().build());

                    builder.customFile(customFiles).customMap(customMap);
                })
                // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }

    // 处理 all 情况
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }

}