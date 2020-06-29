package com.loeyae.cloud.generator;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.springframework.boot.ApplicationArguments;

import java.util.ArrayList;
import java.util.List;

/**
 * MySql Generator.
 *
 * @author ZhangYi<loeyae @ gmail.com>
 * @version 1.0
 * @date 2020-06-27
 */
public class MysqlGenerator {
    private static String dbUrl = "jdbc:mysql://{host}:{port}/{name}?useUnicode=true" +
            "&useSSL=false&characterEncoding=utf8&serverTimezone=UTC";
    private static String dbDriver = "com.mysql.cj.jdbc.Driver";
    private static String dbUsername = "root";
    private static String dbPassword = "";

    public static void run(ApplicationArguments args)
    {
        dbUrl = dbUrl.replace("{host}", args.getOptionValues("host") != null ? args.getOptionValues("host").get(0) :
                "localhost");
        dbUrl = dbUrl.replace("{port}", args.getOptionValues("port") != null ? args.getOptionValues("port").get(0) :
                "3306");
        dbUrl = dbUrl.replace("{name}", args.getOptionValues("db") != null ? args.getOptionValues("db").get(0) :
                "test");
        dbUsername = args.getOptionValues("user") != null ? args.getOptionValues("user").get(0) : "root";
        dbPassword = args.getOptionValues("pws") != null ? args.getOptionValues("pwd").get(0) : "";
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("zhang yi");
        gc.setOpen(false);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(dbUrl);
        dsc.setDriverName(dbDriver);
        dsc.setUsername(dbUsername);
        dsc.setPassword(dbPassword);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        String packageName = args.getOptionValues("package") != null ? args.getOptionValues("package").get(0) : "com" +
                ".loeyae.cloud";

        pc.setParent(packageName);
        String moduleName = args.getOptionValues("module") != null ? args.getOptionValues("module").get(0) : "demo";
        pc.setModuleName(moduleName);
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName() + "/" +
                        tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        mpg.setTemplate(new TemplateConfig().setXml(null));

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
//        strategy.setSuperEntityClass("com.loeyae.commons.comman.SuperEntity");
//        strategy.setSuperEntityColumns("create_time, update_time");
//        strategy.setSuperMapperClass("com.loeyae.commons.comman.SuperMapper");
//        strategy.setSuperServiceClass("com.loeyae.commons.comman.SuperService");
//        strategy.setSuperServiceImplClass("com.loeyae.commons.comman.SuperServiceImpl");
//        strategy.setSuperControllerClass("com.loeyae.commons.comman.SuperController");
        if (args.getOptionValues("includes") != null) {
            List<String> includes = args.getOptionValues("includes");
            String[] includeArr = new String[includes.size()];
            for (int i = 0; i < includes.size(); i++) {
                includeArr[i] = includes.get(i);
            }
            strategy.setInclude(includeArr);
        }
        if (args.getOptionValues("excludes") != null) {
            List<String> excludes = args.getOptionValues("excludes");
            String[] excludeArr = new String[excludes.size()];
            for (int i = 0; i < excludes.size(); i++) {
                excludeArr[i] = excludes.get(i);
            }
            strategy.setInclude(excludeArr);
        }
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
        //mpg.getTemplateEngine().writer();
        for (TableInfo tableinfo : mpg.getConfig().getTableInfoList()) {
            tableinfo.getFields();
        }
    }
}