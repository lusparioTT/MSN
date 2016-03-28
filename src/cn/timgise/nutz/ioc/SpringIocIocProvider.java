package cn.timgise.nutz.ioc;

import org.nutz.ioc.Ioc;
import org.nutz.lang.Lang;
import org.nutz.mvc.IocProvider;
import org.nutz.mvc.NutConfig;

/**
 * Nutz - Spring Ioc Provider
 * Created by Timgise 2016年3月25日
 */
public class SpringIocIocProvider implements IocProvider {

	public Ioc create(NutConfig nc, String[] paths) {
		if (nc == null || Lang.length(paths) > 0)
			return new SpringIoc(paths);
		else {
			return new SpringIoc(nc);
		}
	}
}
