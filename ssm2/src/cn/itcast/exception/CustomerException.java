package cn.itcast.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class CustomerException implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception e) {
		String msg = null;
		ModelAndView mav = new ModelAndView();
		if (e instanceof MyException) {
			msg = e.getMessage();
		} else {
			msg = "系统繁忙,稍候再试!!!";
		}
		mav.addObject("message", msg);
		mav.setViewName("item/error");
		return mav;
	}

}
