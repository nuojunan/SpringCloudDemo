package com.bjnja.i18n.demo;

import java.util.Locale;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 * 多语言配置
 * 需要配置文件中添加下面资源文件
 * spring.messages.basename=i18n/messages
 * src/main/resources
 *      i18n
 *        messages.properties
 *        messages_en.properties
 *        messages_zh_CN.properties
 *    
 * @author ldy
 *
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class NjaLocaleConfig extends WebMvcConfigurerAdapter{

	@Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        // 默认语言
        slr.setDefaultLocale(Locale.ENGLISH);
        return slr;
    }

    @Bean
    public NjaLocaleChangeInterceptor localeChangeInterceptor() {
    	// 国际化拦截器
    	return new NjaLocaleChangeInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}
