package com.example.study.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.study.mapper.EmpMapper2;
import com.example.study.vo.Empvo;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class Emp2Controller {
	@Autowired//spring이 클래스를 관리해준다
	EmpMapper2 empMapper;
	//조회:GET
	@GetMapping("/emp")
	public List<Empvo> callEmp(){
		System.out.println("전체 사원 조회");
		return empMapper.selectAllEmp();
	}
	//생성:POST
	@PostMapping("/emp")//url이 같지만 @이 달라서 사용가능하다!
	public int callEmpInsert(@RequestBody Empvo vo)
	{
		
		return empMapper.InsertEmp(vo);
	}
	//수정:Patch
	@PatchMapping("/emp")//url이 같지만 @이 달라서 사용가능하다!
	public int callEmpUpdate(@RequestBody Empvo vo)
	{
		
		return empMapper.updateEmp(vo);
	}
	//삭제:DELETE
	@DeleteMapping("/emp/{empno}")
	public int callEmpDelete(@PathVariable int empno)
	{
		return empMapper.DeleteEmp(empno);
	}
	//조회: get(join 포함)
	@GetMapping("/emp/dept")
	public List<Map<String, Object>>callEmpDept()
	{
		return empMapper.selectEmpJoinDept();
	}
	@GetMapping("/emp/deptAll")
	//HashMap은 <Map>의 하위 카테고리
	public List<Map<String, Object>>callEmpAllDept()
	{
		return empMapper.selectEmpAllJoinDept();
	}
}
