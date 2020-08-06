package com.loeyae.cloud.generator;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.springframework.boot.ApplicationArguments;

import java.io.File;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.*;

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
        gc.setAuthor("ZhangYi<loeyae@gmail.com>");
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
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null).setController("/templates/controller.java");
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setEntitySerialVersionUID(true);
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
        Map<String, String> packageConfig = mpg.getConfig().getPackageInfo();
        String outdir = mpg.getGlobalConfig().getOutputDir();
        outdir += File.separator + packageName.replaceAll("\\.", StringPool.BACK_SLASH + File.separator) + File.separator + moduleName;
        String dtoDir = outdir + File.separator + "DTO";
        File dtodir = new File(dtoDir);
        if (!dtodir.exists()) {
            dtodir.mkdirs();
        }
        String voDir = outdir + File.separator + "VO";
        File vodir = new File(voDir);
        if (!vodir.exists()) {
            vodir.mkdirs();
        }
        String apiDir = outdir + File.separator + "api";
        File apidir = new File(apiDir);
        if (!apidir.exists()) {
            apidir.mkdirs();
        }
        for (TableInfo tableinfo : mpg.getConfig().getTableInfoList()) {
            Map<String, Map<String, Object>> fieldsPatch = new HashMap<>(tableinfo.getFields().size());
            try {
                ResultSet resultSet =
                        mpg.getDataSource().getConn().prepareStatement("SHOW FULL COLUMNS FROM " + tableinfo.getName()).executeQuery();
                while (resultSet.next()) {
                    String name = resultSet.getString("Field");
                    Map<String, Object> patch = new HashMap<>(5);
                    String type = resultSet.getString("Type");
                    String length = null;
                    String scale = null;
                    String restrain = null;
                    int n = type.indexOf("(");
                    if (n > 0) {
                        int m = type.indexOf(")");
                        length = type.substring(n + 1, m).trim();
                        int l = length.indexOf(",");
                        if (l > 0) {
                            scale = length.substring(l + 1).trim();
                            length = String.valueOf(Integer.parseInt(length.substring(0, l).trim()) - Integer.parseInt(scale));
                        }
                        if (type.length() > m + 2) {
                            restrain = type.substring(m+2).trim();
                        }
                    }
                    patch.put("length", length);
                    patch.put("scale", scale);
                    patch.put("restrain", restrain);
                    String none = resultSet.getString("Null");
                    patch.put("isNull", none.toLowerCase());
                    String dft = resultSet.getString("Default");
                    patch.put("_default", dft);
                    fieldsPatch.put(name, patch);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            Map<String, Object> objectMap = new HashMap<>(4);
            objectMap.put("author", mpg.getGlobalConfig().getAuthor());
            objectMap.put("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            objectMap.put("package", packageConfig);
            objectMap.put("table", tableinfo);
            objectMap.put("patch", fieldsPatch);
            try {
                mpg.getTemplateEngine().writer(objectMap, "/templates/api.java.ftl",
                        apiDir + File.separator + tableinfo.getEntityName() +"Api.java");
                mpg.getTemplateEngine().writer(objectMap, "/templates/create.java.ftl",
                        dtoDir + File.separator + tableinfo.getEntityName() +"Create.java");
                mpg.getTemplateEngine().writer(objectMap, "/templates/update.java.ftl",
                        dtoDir + File.separator + tableinfo.getEntityName() +"Update.java");
                mpg.getTemplateEngine().writer(objectMap, "/templates/primary.java.ftl",
                        dtoDir + File.separator + tableinfo.getEntityName() +"Primary.java");
                mpg.getTemplateEngine().writer(objectMap, "/templates/query.java.ftl",
                        dtoDir + File.separator + tableinfo.getEntityName() +"Query.java");
                mpg.getTemplateEngine().writer(objectMap, "/templates/view.java.ftl",
                        voDir + File.separator + tableinfo.getEntityName() +"View.java");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}