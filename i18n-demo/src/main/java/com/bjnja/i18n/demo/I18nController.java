package com.bjnja.i18n.demo;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

@RestController
@RequestMapping("i18n")
@CrossOrigin
public class I18nController {

	@Autowired
	private MessageSource messageSource;
	
	@GetMapping("/hi")
	public String hi() {
		Locale locale = LocaleContextHolder.getLocale();
		String message = messageSource.getMessage("hi", null, locale);
		System.out.println("hi=="+locale.getLanguage()+"==" + message);
		return "say: hi" + message;
	}
	
	@GetMapping("/set")
	public String set(HttpServletRequest request, HttpServletResponse response, String lang) {
	   LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
       if("zh".equals(lang)){
           localeResolver.setLocale(request, response, new Locale("zh"));
       }else if("en".equals(lang)){
           localeResolver.setLocale(request, response, new Locale("en"));
       }
       return lang;
	}
}
