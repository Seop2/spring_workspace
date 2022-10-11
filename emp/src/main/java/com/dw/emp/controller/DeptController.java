package com.dw.emp.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dw.emp.sevice.DeptService;
import com.dw.emp.vo.DeptVO;
import com.dw.emp.vo.EmpVO;
import com.github.pagehelper.PageInfo;
@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class DeptController {
	@Autowired
	private DeptService deptService;
	
	//부서목록 crud
	//부서 추가
	@PostMapping("/dept")
	public int callDeptInsert(@RequestBody DeptVO vo)
	{
		return deptService.DeptInsert(vo);
	}
	
//	//특정 부서 삭제
	@DeleteMapping("/dept/{deptno}")
	public int DelectDept(@PathVariable int deptno) {
	
		return deptService.getDeleteDept(deptno);
	}
//	//부서 전체 조회
//	@GetMapping("/dept")
//	public PageInfo <Map<String, Object>> callDeptPages(@RequestParam int page)
//	{
//		List<Map<String, Object>>list = deptService.getDeptPageList(page);
//		int navigatePages = 5;//한 블럭에 보여줄 페이지 수(네이버웹툰은 1블록에 10페이지
//		return new PageInfo<Map<String, Object>>(list,navigatePages);
//	}
//	public List<Map<String, Object>> CallEmpAll()
//	{
//		
//		return empMapper.EmpSelectAll();
//	}
	@GetMapping("/dept")
	public List<Map<String, Object>>CallDeptAll(){
		return deptService.getDeptAll();
	}
	@GetMapping("/dept/deptno/{deptno}")
	public DeptVO CallDeptByDeptno(@PathVariable int deptno)
	{
		return deptService.getDeptByDeptno(deptno);
	}
//	
//	//부서 수정
	@PatchMapping("/dept")
	public int CallEmpUpdate(@RequestBody DeptVO vo) {
		
		return deptService.getDeptByUpdate(vo);
	}
	
}
