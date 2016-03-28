package cn.timgise.nutz.ioc;

import org.nutz.ioc.Ioc2;
import org.nutz.ioc.IocContext;
import org.nutz.ioc.IocException;
import org.nutz.ioc.ValueProxyMaker;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.NutConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;

public class SpringIoc implements Ioc2 {
	private Log log = Logs.get();
	
	protected ApplicationContext appContext;
	
	public SpringIoc(NutConfig nc){
		log.info("webapplicationcontext------------" + WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		appContext = (ApplicationContext) nc.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
	}
	
	public SpringIoc(String[] paths) {
		log.info("Config Locations ---------" + Json.toJson(paths));
		appContext = new ClassPathXmlApplicationContext(paths);
	}

	public void depose() {
		if (appContext != null) {
			appContext.publishEvent(new ContextClosedEvent(appContext));
			appContext = null;
        }
	}

	public <T> T get(Class<T> type) throws IocException {
		InjectName inm = type.getAnnotation(InjectName.class);
		if (null != inm && (!Strings.isBlank(inm.value())))
			return get(type, inm.value());
		IocBean iocBean = type.getAnnotation(IocBean.class);
		if (iocBean != null && (!Strings.isBlank(iocBean.name())))
			return get(type, iocBean.name());
		return get(type, Strings.lowerFirst(type.getSimpleName()));
	}

	public <T> T get(Class<T> type, String name) throws IocException {

		return appContext.getBean(type, name);

	}

	public <K> K getByType(Class<K> type) {

		return null;
	}

	public String[] getNames() {

		return appContext.getBeanDefinitionNames();
	}

	public String[] getNamesByType(Class<?> type) {
		return appContext.getBeanNamesForType(type);
	}

	public boolean has(String name) throws IocException {
		return appContext.containsBean(name);
	}

	public void reset() {
		appContext.publishEvent(new ContextRefreshedEvent(appContext));
	}

	public void addValueProxyMaker(ValueProxyMaker vpm) {

	}

	public <T> T get(Class<T> type, String arg1, IocContext ic) {

		return null;
	}

	public <T> T getByType(Class<T> type, IocContext ic) {

		return null;
	}

	public IocContext getIocContext() {

		return null;
	}

	public String[] getNamesByType(Class<?> type, IocContext ic) {

		return null;
	}

}
