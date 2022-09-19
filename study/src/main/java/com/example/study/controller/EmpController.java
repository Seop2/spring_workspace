package com.example.study.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.study.mapper.EmpMapper;
import com.example.study.vo.DeptVO;
import com.example.study.vo.Empvo;
//@RestController는 결과값을 보여준다.
@RestController
@CrossOrigin
public class EmpController {
	//Spring에서 객체를 관리(생성과 소멸 관리)
	@Autowired 
	EmpMapper empMapper;
	/*
	 * @:어노테이션
	 * Get:조회하다
	 * Mapping:Url을 매핑하다
	 *  /emp:url주소
	 * */
	@GetMapping("/emp")
	public List<Empvo>callAllEmp(){
		List<Empvo> list = new ArrayList<Empvo>();
		Empvo smith = new Empvo();//VO 클래스 생성
		smith.setEmpno(7369);
		smith.setEname("smith");
		smith.setJob("CLERK");
		smith.setHiredate("1980-12-17");
		smith.setSal(800);
		
		list.add(smith);//list에 smith추가
		//list에 Allen추가하기
		Empvo Allen = new Empvo();//VO 클래스 생성
		list.add(Allen);//list에 smith추가
		Allen.setEmpno(7369);
		Allen.setEname("allen");
		Allen.setJob("SALAYMAN");
		Allen.setHiredate("1988-11-17");
		Allen.setSal(1200);
		return list;
	}
	
	@GetMapping("/dept")
	public List<DeptVO>callDeptAll(){
		List<DeptVO> list = new ArrayList<DeptVO>();

		DeptVO dept1 = new DeptVO();
		dept1.setDeptno(10);
		dept1.setDname("ACOUNTING");
		dept1.setLoc("NEW YORK");
		list.add(dept1);
		
		DeptVO dept2 = new DeptVO();
		dept2.setDeptno(20);
		dept2.setDname("RESEARCH");
		dept2.setLoc("DALLAS");
		list.add(dept2);
		return list;
		
	}
	//@PathVariable을 이용해서 url에 있는 데이터를 받아올 수 있다
	//localhost:8080/dept/300 ==> 300이라는 데이터를 파라미터로 받는다
	@GetMapping("/dept/{depno}")
	public DeptVO callDeptNo(@PathVariable int depno) 
	{
		List<DeptVO> list = new ArrayList<DeptVO>();

		DeptVO dept1 = new DeptVO();
		dept1.setDeptno(10);
		dept1.setDname("ACOUNTING");
		dept1.setLoc("NEW YORK");
		list.add(dept1);
		
		DeptVO dept2 = new DeptVO();
		dept2.setDeptno(20);
		dept2.setDname("RESEARCH");
		dept2.setLoc("DALLAS");
		list.add(dept2);
		
		System.out.println("부서번호는 => "+depno);
		
		for(int i=0; i<list.size(); i++)
		{
			int x = list.get(i).getDeptno(); //list에 add한 deptno를 불러온다
			if(x == depno)
			{
				//x가 url로 받아온 depno랑 같다면!
				return list.get(i);//부서번호 일치 deptno class return!
			}else if(x != depno)
			{
				System.out.println("없는 부서번호입니다.");
			}
		}
		//ArrayLlist,HashMap 공부
		return null;
	}
	//사원번호 조회하는 @GetMapping생성
	@GetMapping("/emp/{empno}")
	public Empvo callAllEmp(@PathVariable int empno){
		List<Empvo> list = new ArrayList<Empvo>();
		Empvo smith = new Empvo();//VO 클래스 생성
		smith.setEmpno(11);
		smith.setEname("smith");
		smith.setJob("CLERK");
		smith.setHiredate("1980-12-17");
		smith.setSal(800);
		
		list.add(smith);//list에 smith추가
		//list에 Allen추가하기
		Empvo Allen = new Empvo();//VO 클래스 생성
		list.add(Allen);//list에 smith추가
		Allen.setEmpno(22);
		Allen.setEname("allen");
		Allen.setJob("SALAYMAN");
		Allen.setHiredate("1988-11-17");
		Allen.setSal(1200);
		
		System.out.println("부서번호는 => "+empno);
		
		for(int i=0; i<list.size(); i++)
		{
			int x = list.get(i).getEmpno(); //list에 add한 deptno를 불러온다
			if(x == empno)
			{
				//x가 url로 받아온 depno랑 같다면!
				return list.get(i);//부서번호 일치 deptno class return!
			}
		}
		return null;
	}
	@GetMapping("/emp/sal/{money}")
	public String callEmpSal(@PathVariable String money) {
		return money;
	}
//	사이트 주소중 ?: 쿼리 스트링
//	@PathVariable처럼  url에 값을 넘겨주는 방법중 하나
	@GetMapping("/leaderboards/tier")
	//localhost:8080/leaderboards/tier?region=kr
	//---kr이 리턴됨
	public String queryStringTest(@RequestParam String region, @RequestParam String page)
	{
		return region+","+page;
	}
	//localhost:8080/board/search?writer=brian&page=10
	@GetMapping("/board/search")
	public String queryStringTest1(@RequestParam String writer, @RequestParam String page)
	{
		return writer+","+page;
	}
	@GetMapping("/db/emp")
	public List<Empvo>callDbEmp(){
		
		return empMapper.selectEmp();
	}
	//path방법으로 데이터받아오기
	@GetMapping("db/emp/{empno}")
	public Empvo CallEmpOne(@PathVariable int empno) {
		System.out.println("사원 번호: "+ empno);
		Empvo vo = empMapper.selectEmpOne(empno);
		if(empno==7521)
		{
			vo.setSal(0);
			vo.setHiredate(null);
		}
		return vo;
	}
	@GetMapping("db/dept/{deptno}")
	public DeptVO CallDeptOne(@PathVariable int deptno) {
		System.out.println("부서 번호: "+ deptno);
		if(deptno  == 10 || deptno == 20) {
			//return은 더 이상 아래 코드를 실행하지 않는다
			return null;
		}
		DeptVO dp = empMapper.selectDeptOne(deptno);
		return dp;
	}
	//Emp테이블에 사원 insert하기
//	GET: SELECT 
//	POST: INSERT
//	PATCH:UPDATE
//	DELETE: DELETE
//	HTTP method!(시간날 때 구글링해보기)
	//HTTP메소드가 다르면 url경로 중복 가능하다!
	@PostMapping("/emp")
	public int callInsertEmp(@RequestBody Empvo vo) {
		System.out.println("사원 번호는=>"+vo.getEmpno());
		System.out.println("사원 이름은=>"+vo.getEname());
		System.out.println("사원 직책=>"+vo.getJob());
		System.out.println("사원 급여=>"+vo.getSal());
		System.out.println("사원 부서=>"+vo.getDeptno());
		
		int row = empMapper.insertEmp(vo);
		return row;
	}
	
	@DeleteMapping("/emp/{empno}")
	public int callDeleteEmp(@PathVariable int empno)
	{
		System.out.println("사원 번호는?"+empno);
		int row = empMapper.DeleteEmp(empno);
		return row;
	}
	//update는 insert랑 유사하다, @RequestBody를 입력해주자
	@PatchMapping("/emp")
	public int callUpdateEmp(@RequestBody Empvo vo)
	{
		System.out.println("emp업데이트");
		System.out.println("사원 번호는=>"+vo.getEmpno());
		System.out.println("사원 보너스는=>"+vo.getComm());
		System.out.println("사원 급여=>"+vo.getSal());
		System.out.println("사원 부서=>"+vo.getDeptno());
		
		int row = empMapper.UpdateEmp(vo);
		return row;
	}
	//1. 사원이름으로 검색해서 사원번호, 사원이름, 직책, 급여 조회 하기(Like x) [select]
	@GetMapping("/emp/name/{ename}")
	public List<Empvo> callEmpName(@PathVariable String ename)
	{
		
		return empMapper.selectEmpByName(ename);
	}
	//2. 부서번호가 10번인 사원들 comm 100으로 update 
	//1번
//	@GetMapping("db/emp/{ename}"){x} = {ename} = {empno} 다 똑같이 인식한다
//	public Empvo callSelectEmp(@PathVariable String ename)
//	{
//		Empvo vo = empMapper.selectEmpName(ename);
//		return vo;
//	}
	//2번
	@PatchMapping("/up/comm")
	public int callupdateEmpComm(@RequestBody Empvo vo)
	{
		int row = empMapper.updateEmpComm(vo);
		return row;
	}
	//3. 급여가 2000이상인 모든 사원의 번호,이름,직업, 입사날짜를 조회하시오
	//단, 입사날짜는 년도만 보이게 출력하시오
	//쿼리 스트링으로 해보기(?)
	//결과 여러개 나올경우 List사용
	@GetMapping("/sal")
	public List<Empvo> callEmpSal(@RequestParam int sal)
	{
		
		return empMapper.selectEmpSal(sal);
	}
	//java의 최상위 class는 object!
	//object는 모든 데이터타입 형변환이 가능하다!
	@GetMapping("/emp/dname")//HashMap 공부!
	public List<Map<String, Object>>callEmpDname(){//object는 모든 타입 다 받음
		return empMapper.selectEmpJoinDept();
	}
	@GetMapping("/emp/loc")
	public List<Map<String, Object>>callEmpLoc()
	{
		//문제: 사원번호, 사원이름, 부서번호, 부서이름, 부서위치를 조회
		
		return empMapper.selectEmpJoinDept2();
	}
}
