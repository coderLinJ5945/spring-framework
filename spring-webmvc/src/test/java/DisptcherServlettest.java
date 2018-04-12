import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.view.tiles3.TilesConfigurerTests;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class DisptcherServlettest implements WebApplicationInitializer {

	/**
	 * 实现 WebApplicationInitializer 中的 onStartup 方法
	 * @param servletCxt
	 */
	@Override
	public void onStartup(ServletContext servletCxt) {
		// 加载 Spring web 应用配置
		AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
		ac.register(TilesConfigurerTests.AppConfig.class);
		ac.refresh();

		// 注册和初始化 DispatcherServlet
		DispatcherServlet servlet = new DispatcherServlet(ac);
		ServletRegistration.Dynamic registration = servletCxt.addServlet("app", servlet);
		registration.setLoadOnStartup(1);
		registration.addMapping("/app/*");
	}
	/**
	 * java代码代替或者结合web.xml 实现注册DispatcherServlet

	@Override
	public void onStartup(ServletContext container) {
		XmlWebApplicationContext appContext = new XmlWebApplicationContext();
		appContext.setConfigLocation("/WEB-INF/spring/dispatcher-config.xml");

		ServletRegistration.Dynamic registration = container.addServlet("dispatcher", new DispatcherServlet(appContext));
		registration.setLoadOnStartup(1);
		registration.addMapping("/");
	} */
}

