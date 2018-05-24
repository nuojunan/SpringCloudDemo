package com.bjnja.i18n.demo;

import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 * 国际化语言设置拦截器
 * @author ldy
 *
 */
public class NjaLocaleChangeInterceptor extends LocaleChangeInterceptor{

	// head set lang key
	private static final String ACCEPT_LANGUAGE = "Accept-Language";
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws ServletException {
		String newLocale = request.getHeader(ACCEPT_LANGUAGE);
		if (newLocale != null) {
				newLocale = newLocale.replace("-", "_");
				Locale locale = LocaleContextHolder.getLocale();
				if (newLocale.equals(locale.getLanguage())) {
					// 相同不在设置
					return true;
				}
				LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
				if (localeResolver == null) {
					throw new IllegalStateException(
							"No LocaleResolver found: not in a DispatcherServlet request?");
				}
				try {
					localeResolver.setLocale(request, response, parseLocaleValue(newLocale));
				}
				catch (IllegalArgumentException ex) {
					if (isIgnoreInvalidLocale()) {
						logger.debug("Ignoring invalid locale value [" + newLocale + "]: " + ex.getMessage());
					}
					else {
						throw ex;
					}
				}
		}
		return true;
	}
}
