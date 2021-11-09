package com.yuwen.spring.uitl.generate;

import java.util.Collections;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;

/**
 * 
 * 代码生成器 ，先修改下面的常量配置参数，然后执行 main方法
 */
public class CodeGenerator {

	// 数据库连接配置
	// private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String JDBC_URL = "jdbc:mysql://10.21.13.14:3306/demodb?useUnicode=true&useSSL=false&characterEncoding=utf8";
	private static final String JDBC_USER_NAME = "demo";
	private static final String JDBC_PASSOWRD = "demo123456";

	// 包名和模块名
	private static final String PACKAGE_NAME = "com.example";
	private static final String MODULE_NAME = "demo";

	// 表名，多个表使用英文逗号分割
	private static final String[] TBL_NAMES = { "tbl_user", "tbl_role" };

	// 表名的前缀，从表生成代码时会去掉前缀
	private static final String TABLE_PREFIX = "tbl_";

	// 生成代码入口main方法
	public static void main(String[] args) {
		// 1.数据库配置
		DataSourceConfig.Builder dataSourceConfigBuilder = new DataSourceConfig.Builder(JDBC_URL, JDBC_USER_NAME,
				JDBC_PASSOWRD).dbQuery(new MySqlQuery()).typeConvert(new MySqlTypeConvert())
						.keyWordsHandler(new MySqlKeyWordsHandler());

		// 1.1.快速生成器
		FastAutoGenerator fastAutoGenerator = FastAutoGenerator.create(dataSourceConfigBuilder);

		// 2.全局配置
		fastAutoGenerator.globalConfig(globalConfigBuilder -> globalConfigBuilder.fileOverride().disableOpenDir()
				.outputDir("src\\main\\java").author("yuwen").dateType(DateType.TIME_PACK).commentDate("yyyy-MM-dd"));

		// 3.包配置
		fastAutoGenerator.packageConfig(packageConfigBuilder -> packageConfigBuilder.parent(PACKAGE_NAME)
				.moduleName(MODULE_NAME).entity("entity").mapper("dao").service("service").serviceImpl("service.impl")
				.controller("controller").other("other")
				.pathInfo(Collections.singletonMap(OutputFile.mapperXml, "src\\main\\resources\\mapper")));

		// 4.模板配置
		AbstractTemplateEngine templateEngine = new FreemarkerTemplateEngine();
		fastAutoGenerator.templateEngine(templateEngine);

		// 5.注入配置 TODO

		// 6.策略配置
		fastAutoGenerator.strategyConfig(strategyConfigBuilder -> strategyConfigBuilder.enableCapitalMode()
				.enableSkipView().disableSqlFilter().addInclude(TBL_NAMES).addTablePrefix(TABLE_PREFIX));

		// 6.1.Entity策略配置 TODO

		// 6.2.Controller策略配置
		// 开启生成@RestController 控制器
		fastAutoGenerator
				.strategyConfig(strategyConfigBuilder -> strategyConfigBuilder.controllerBuilder().enableRestStyle());

		// 6.3.Service策略配置
		// 格式化service接口和实现类的文件名称，去掉默认的ServiceName前面的I
		fastAutoGenerator.strategyConfig(strategyConfigBuilder -> strategyConfigBuilder.serviceBuilder()
				.formatServiceFileName("%sService").formatServiceImplFileName("%sServiceImpl"));

		// 6.4.Mapper策略配置
		// 格式化 mapper文件名,格式化xml实现类文件名称
		fastAutoGenerator.strategyConfig(strategyConfigBuilder -> strategyConfigBuilder.mapperBuilder()
				.formatMapperFileName("%sDao").formatXmlFileName("%sDao"));

		// 7.生成代码
		fastAutoGenerator.execute();
	}

}
