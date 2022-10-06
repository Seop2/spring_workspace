package com.dw.emp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dw.emp.sevice.EmpService;
import com.dw.emp.vo.EmpVO;
import com.github.pagehelper.PageInfo;

@CrossOrigin //누구나 자원을 요청할 수 았게 권한 해제
@RestController
@RequestMapping("/api/v1")
//@RequsetMapping-> 전역변수 개념
public class EmpController {
	/*
	 * M(model == Mapper(Service))  V  (view)  C(controller) MVC패턴,
	 * 구조가 큰 프로젝트에서 업무분담을 하기위해 사용하는 적절한 디자인 패턴 중 하나,
	 * 장점: 유지보수 쉽다, 직관적이다, 인력 채용 쉽다
	 * 단점: 프로젝트 규모가 작다면, 시간낭비, 가독성 떨어짐
	 * Point: MVC는 Spring뿐만 아니라 여러 프레임워크에서 사용하는 디자인 패턴이다
	 * MVC는 spring만의 기술이 아니다! 
	 * */
	
	@Autowired
	//프레임워크(스프링부트)가 클래스를 관리해주는 기술을 Dependency Injection이라고 표현한다 
	private EmpService service;
	
	@GetMapping("/emp/deptno/{deptno}")
	public List<EmpVO> callEmp(@PathVariable int deptno)
	{
		//get요청을 하면서도 추가,삭제,수정이 있을수도 있다
		return service.getEmpList(deptno);
	}
	//전체 사원 조회
	//PageInfo:페이징 처리 도와주는 라이브러리!
	@GetMapping("/emp")
	public PageInfo <Map<String, Object>> callEmpPages(@RequestParam int page)
	{
		List<Map<String, Object>>list = service.getEmpPageList(page);
		int navigatePages = 5;//한 블럭에 보여줄 페이지 수(네이버웹툰은 1블록에 10페이지
		return new PageInfo<Map<String, Object>>(list,navigatePages);
	}
	@GetMapping("/emp/statistics")
	public Map<String, Object> callStatistics(){
		return service.getEmpStatistics();
	}
	@PostMapping("/emp")
	public int callEmpInsert(@RequestBody EmpVO vo)
	{
		return service.EmpInsert(vo);
	} 
	//특정 사원 조회
	@GetMapping("/emp/empno/{empno}")
	public EmpVO CallEmpByEmpno(@PathVariable int empno)
	{
		return service.getEmpByEmpno(empno);
	}
	//사원 정보 수정
	@PatchMapping("/emp")
	public int CallEmpUpdate(@RequestBody EmpVO vo) {
		
		return service.getEmpByUpdate(vo);
	}
	//특정 회원 탈퇴
	@PatchMapping("/emp/empno/{empno}")
	public int CallFireEmp(@PathVariable int empno)
	{
		return service.getFireEmp(empno);
	}
}
