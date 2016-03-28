package cn.timgise.main.web;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.timgise.service.JianGuoService;

@At
@Component
@IocBean
public class JianGuoModule {
	Log log=Logs.getLog(JianGuoModule.class);
	
	@Autowired JianGuoService jianGuoService;
	
	@At
	public String hi(){
		jianGuoService.sayT(); 
		return "springframework";
	}
}
