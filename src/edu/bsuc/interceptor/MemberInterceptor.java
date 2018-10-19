package edu.bsuc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import edu.bsuc.entity.Member;
import edu.bsuc.mapper.MemberMapper;


public class MemberInterceptor extends HandlerInterceptorAdapter{
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		//当前登陆会员开始
		if (modelAndView != null) {
			HttpSession session =request.getSession();
			Integer id = (Integer)session.getAttribute("mid");
			if(id != null){
				Member member = memberMapper.getById(id);
				modelAndView.addObject("member",member);
			}
		}
		//当前登陆会员结束
	}
	
}
