package com.yuwen.spring.uitl.generate;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.IDbQuery;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.PostgreSqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;

/**
 *
 * PostgreSQL数据库代码生成器 ，先修改下面的常量配置参数，然后执行generate方法
 */
public class CodeGeneratorPostgreSQL {

	// 数据库类型 MySQL
	// private static final IDbQuery dbQuery = new MySqlQuery();
	// 数据库类型PostgreSql
	private static final IDbQuery dbQuery = new PostgreSqlQuery();
	// 数据库连接配置
	private static final String JDBC_URL = "jdbc:postgresql://10.21.13.48:5432/postgres?currentSchema=public";
	private static final String JDBC_USER_NAME = "antdb";
	private static final String JDBC_PASSOWRD = "SDN@AntDB";

	// 包名和模块名
	private static final String PACKAGE_NAME = "com.ai.ipc.common";
	private static final String MODULE_NAME = "db";

	// 表名，指定需要生成代码的表，多个表使用英文逗号分割
	private static final String[] TBL_NAMES = { "tbl_device", "tbl_device_info", "tbl_device_mac", "tbl_interface",
			"tbl_physical_link", "tbl_protocol" };

	// 注意不要引入xxl-job相关的表，业务不会直接使用这些表

	// 表名的前缀，从表生成代码时会去掉前缀
	private static final String TABLE_PREFIX = "tbl_";

	// 生成代码入口，执行Run -> Junit Test
	@Test
	public void generate() {
		// 1.数据库配置
		DataSourceConfig.Builder dataSourceConfigBuilder = new DataSourceConfig.Builder(JDBC_URL, JDBC_USER_NAME,
				JDBC_PASSOWRD).dbQuery(dbQuery).typeConvert(new MySqlTypeConvert())
						.keyWordsHandler(new MySqlKeyWordsHandler());

		// 1.1.快速生成器
		FastAutoGenerator fastAutoGenerator = FastAutoGenerator.create(dataSourceConfigBuilder);

		// 2.全局配置
		// 覆盖已生成文件
		// 不打开生成文件目录
		// 指定输出目录,注意使用反斜杠\
		// 设置注释的作者
		// 设置注释的日期格式
		// 使用java8新的时间类型
		fastAutoGenerator.globalConfig(globalConfigBuilder -> globalConfigBuilder.fileOverride().disableOpenDir()
				.outputDir("src\\main\\java").author("yuwen").commentDate("yyyy-MM-dd").dateType(DateType.TIME_PACK));

		// 3.包配置
		// 设置父包名
		// 设置父包模块名
		// 设置MVC下各个模块的包名
		// 设置XML资源文件的目录
		fastAutoGenerator.packageConfig(packageConfigBuilder -> packageConfigBuilder.parent(PACKAGE_NAME)
				.moduleName(MODULE_NAME).entity("entity").mapper("dao").service("service").serviceImpl("service.impl")
				.controller("controller").other("other")
				.pathInfo(Collections.singletonMap(OutputFile.mapperXml, "src\\main\\resources\\mapper")));

		// 4.模板配置
		// 使用Freemarker引擎模板，默认的是Velocity引擎模板
		AbstractTemplateEngine templateEngine = new FreemarkerTemplateEngine();
		fastAutoGenerator.templateEngine(templateEngine);

		// 5.注入配置 TODO

		// 6.策略配置
		// 设置需要生成的表名
		// 设置过滤表前缀
		fastAutoGenerator.strategyConfig(strategyConfigBuilder -> strategyConfigBuilder.enableCapitalMode()
				.enableSkipView().disableSqlFilter().addInclude(TBL_NAMES).addTablePrefix(TABLE_PREFIX));

		// 6.1.Entity策略配置
		// 生成实体时生成字段的注解，包括@TableId注解等
		// 去掉.enableTableFieldAnnotation()，不生成注解
		// 数据库表和字段映射到实体的命名策略，为下划线转驼峰
		// 全局主键类型为None
		// 实体名称格式化为XXXEntity
		fastAutoGenerator.strategyConfig(strategyConfigBuilder -> strategyConfigBuilder.entityBuilder()
				.naming(NamingStrategy.underline_to_camel).columnNaming(NamingStrategy.underline_to_camel)
				.idType(IdType.NONE).formatFileName("%sEntity"));

		// 6.2.Controller策略配置
		// 不生成Controller
		fastAutoGenerator.templateConfig(templateConfig -> templateConfig.controller(""));

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