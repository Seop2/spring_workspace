package com.example.study.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
//@:어노테이션 이게 있어야 호출가능
//ctrl + F11 실행할때 spring 엔진이 class에 있는 어노테이션을 스캔한다.
@RestController
public class HomeController {
	/*public :접근지정자
	 * String:리턴 타입
	 * callHello: 메소드 이름
	 * ():파라미터
	 * @GetMapping: 주소(자원)조회
	 * ("/hello")":/hello 라는 url주소가 됨
	 * localhost->자기 ip를 의미함
	 * IP접속 VS localhost 정답은 localhost
	 * why? ip는 매번 바뀌기 때문에 localhost라고 입력하는 게 더 좋다.
	 * */
	@GetMapping("/hello")
	public String callHello() {
		return "Hello World!";
	}
	
	@GetMapping("/test")
	public Map<String, Object>callTest(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("1", "곱창");
		map.put("2", "막창");
		map.put("3", "대창");
		
		return map;
		//json형태로 크롬에 출력됨
	}
	/*
	 * public : 접근지정자
	 * List<String>:리턴 타입
	 * callFood:메소드 이름
	 * ():파라미터
	 *  /food: url주소
	 * */
	@GetMapping("/food/jinseop")
	public List<String>callFood(){
		List<String> list = new ArrayList<String>();
		list.add("멜론");
		list.add("포도");
		list.add("키위");
	
		return list;
	}
//	##HTTP 오류
//	404:페이지를 찾을 수 없음
//	ex) 경로(url)를 잘못 씀
//	500:개발자 실수(프로그래밍 오류)
//	401: 권한 없음(권한 없어서 해당 url에 접속 불가능)
//	200: 성공
}