package com.bjnja.cloud.zuul.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
/**
 * 路由过滤器
 * @author deyi
 *
 */
@Component
public class MyZuulFilter extends ZuulFilter{
	
	private static Logger log = LoggerFactory.getLogger(MyZuulFilter.class);

	@Override
	public Object run() {
		RequestContext context = RequestContext.getCurrentContext();
		HttpServletRequest request = context.getRequest();
		log.info("request url: {}, method: {}", //
				request.getRequestURL().toString(), request.getMethod() );
		String token = request.getParameter("token");
		if (StringUtils.isEmpty(token)) {
			log.warn("request error, token is undefind");
			context.setSendZuulResponse(false);
			context.setResponseStatusCode(401);
			try {
				context.getResponse().getWriter().write("token is undefind");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		log.info("request is ok!");
		return null;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String filterType() {
		/**
		 	ilterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，具体如下： 
			pre：路由之前
			routing：路由之时
			post： 路由之后
			error：发送错误调用
			filterOrder：过滤的顺序
			shouldFilter：这里可以写逻辑判断，是否要过滤，本文true,永远过滤。
			run：过滤器的具体逻辑。可用很复杂，包括查sql，nosql去判断该请求到底有没有权限访问。
		 */
		return "pre";
	}

}
