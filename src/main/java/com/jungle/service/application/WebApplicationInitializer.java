package com.jungle.service.application;

import com.jungle.service.fiilters.CorsFilter;
import com.jungle.service.fiilters.ExceptionFilter;
import org.apache.commons.lang3.CharEncoding;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.*;
import java.util.EnumSet;

/**
 * @author Jungle
 * @version Created on 2016/4/27.
 */
@Order(1)
public class WebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] {
                ApplicationConfig.class,
                WebSecurityConfig.class,
                MongoConfig.class,
                MySQLConfig.class,
                I18NConfig.class
        };
    }
    @Override
    public void onStartup(ServletContext servletContext)
            throws ServletException {
        super.onStartup(servletContext);
        initFilters(servletContext);
    }

    protected void initFilters(ServletContext servletContext) {
//        addFilter(servletContext, "exceptionFilter", new DelegatingFilterProxy("exceptionFilter"));
        addFilter(servletContext, "exceptionFilter", new ExceptionFilter());
        initCharacterEncodingFilter(servletContext);
        addFilter(servletContext, "corsFilter", new CorsFilter());
    }
    protected void initCharacterEncodingFilter(ServletContext servletContext) {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        //characterEncodingFilter.setForceEncoding(true);
        characterEncodingFilter.setEncoding(CharEncoding.UTF_8);
        addFilter(servletContext, "characterEncodingFilter", characterEncodingFilter);
    }
    protected void addFilter(ServletContext servletContext, String filterName, Filter filter) {
        FilterRegistration.Dynamic filterRegistration = servletContext.addFilter(filterName, filter);
        filterRegistration.setAsyncSupported(isAsyncSupported());
        filterRegistration.addMappingForUrlPatterns(getDispatcherTypes(), false, "/*");
    }
    protected EnumSet<DispatcherType> getDispatcherTypes() {
        return isAsyncSupported() ?
                EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE, DispatcherType.ASYNC) :
                EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE);
    }
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebMvcConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
