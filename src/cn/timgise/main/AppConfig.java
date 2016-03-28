package cn.timgise.main;

import org.nutz.mvc.annotation.Encoding;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.SetupBy;

import cn.timgise.nutz.ioc.SpringIocIocProvider;

@SetupBy(AppSetup.class)
// 自动搜索子模块
@Modules(scanPackage = true)
// UTF-8编码格式
@Encoding(input = "utf8", output = "utf8")
// Ioc Bean的依赖关系配置 js文件和注解类的扫描包的声明
@IocBy(type = SpringIocIocProvider.class, args = { "classpath*:beans.xml" })
// 默认视图
@Ok("json:full")
// 异常跳转
@Fail("jsp:error.500")
public class AppConfig {

}
