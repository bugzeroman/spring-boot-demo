package com.yuwen.spring.uitl.generate;

import java.util.Collections;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * 
 * 代码生成器 ，先修改下面的常量配置参数，然后执行 main方法
 */
public class CodeGenerator {

	// 数据库连接配置
	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
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
		FastAutoGenerator.create(JDBC_URL, JDBC_USER_NAME, JDBC_PASSOWRD).globalConfig(builder -> {
			builder.author("yuwen") // 设置作者
					// .enableSwagger() // 开启 swagger 模式
					.fileOverride() // 覆盖已生成文件
					.disableOpenDir()// 不打开生成文件目录
					.outputDir("src\\main\\java"); // 指定输出目录,注意使用反斜杠\\
		}).packageConfig(builder -> {
			builder.parent(PACKAGE_NAME) // 设置父包名
					.moduleName(MODULE_NAME) // 设置父包模块名
					.pathInfo(Collections.singletonMap(OutputFile.mapperXml, "src\\main\\resources")); // 设置mapperXml生成路径
		}).strategyConfig(builder -> {
			builder.addInclude(TBL_NAMES) // 设置需要生成的表名
					.addTablePrefix(TABLE_PREFIX); // 设置过滤表前缀
		}).templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
				.execute();
	}

}
