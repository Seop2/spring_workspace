package com.dw.emp.conf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
//<요구사항>
//어떤 요청이 오든 method,url,ip를 알고싶다...
//HttpServletRequest를 메소드 마다 만드는 건 비효율적이라고 생각함
//

import com.dw.emp.mapper.LogsMapper;
import com.dw.emp.vo.LogsVO;

//Spring 인터셉터
//Spring에서 모든 사용자 요청을 인터셉터 한다
//ex) 
//사원조회 요청  -> 인터셉터! (preHandle)-> Controller->postHandle

//인터셉터란?
//컨트롤러의 핸들러를 호출하기 전과 후에 요청과 응답을 참조하거나 가공할 수 있는 일종의 필터
//인터셉터 동작 위치 및 순서
//1) 사용자는 서버에 자신이 원하는 작업을 요청하기 위해 url을 통해 Request 객체를 보낸다.
//
//2) DispatcherServlet은 해당 Request 객체를 받아서 분석한뒤 '핸들러 매핑(HandlerMapping)' 에게
//
//사용자의 요청을 처리할 핸들러를 찾도록 요청 한다.
//
//3) 그결과로 핸들러 실행체인(HandlerExectuonChanin)이 동작하게 되는데, 이 핸들러 실행체인은 하나이상의 핸들러 인터셉터를 거쳐서 컨트롤러가 실행될수 있도록 구성되어 있다.
//
//(핸들러 인터셉터를 등록하지 않았다면, 곧바로 컨트롤러가 실행된다. 반대로 하나이상의 인터셉터가 지정되어 있다면 지정된 순서에 따라서 인터셉터를 거쳐서 컨트롤러를 실행한다)

@Component
public class Interceptor implements HandlerInterceptor{
	@Autowired 
	private LogsMapper logsMapper;
	private final Logger logger = LoggerFactory.getLogger(Interceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//이제 System.out.println()은 그만!
		//logger를 이용해서 기록을 남기자
		//logger를 이용하면 원하는 레벨에 맞게 출력이 가능하다
		//레벨? (디버깅 모드, 경고 모드, 출력 모드)
//		logger.debug("디버그 전용 메세지");
//		logger.warn("경고!");
		logger.info("==========================================preHandle");
		String requestUrl = request.getRequestURI();//접속 url호출
		String httpMethod = request.getMethod();//http 메소드 호출
		String userIP = request.getHeader("X-Forwarded-For");
		if(userIP == null)userIP = request.getRemoteAddr();
		
		logger.info("요청 url: "+ requestUrl);
		logger.info("요청 HTTP Method: "+ httpMethod);
		logger.info("사용자 IP: "+ userIP);
		logger.info("==========================================preHandle");
		
		
		LogsVO logVO = new LogsVO();
		//logVO.setHttpMethod(httpMethod);
		logVO.setHttpMethod(httpMethod);
		logVO.setIp(userIP);
		logVO.setUrl(requestUrl);
		
		logsMapper.InsertLogs(logVO);//접속 로그 insert!
		
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
}
