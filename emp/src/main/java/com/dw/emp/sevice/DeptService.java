package com.dw.emp.sevice;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dw.emp.mapper.DeptMapper;
import com.dw.emp.vo.DeptVO;
import com.dw.emp.vo.EmpVO;
import com.github.pagehelper.PageHelper;

@Service
//서비스는 로직을 구현하는 곳
public class DeptService {
	@Autowired
	private DeptMapper mapper;
	
	//부서 추가
	public int DeptInsert(DeptVO vo) {
		return mapper.InsertDept(vo);
	}
	//부서 삭제
	public int getDeleteDept(int deptno) {
		// TODO Auto-generated method stub
		return mapper.deleteDept(deptno);
	}
	//부서 수정
	public int getDeptByUpdate(DeptVO vo)
	{
		return mapper.UpdateDept(vo);
	}
	//부서 조회
	public List<Map<String, Object>>getDeptAll()
	{
		return mapper.SelectDeptJoin();
	}
//	public List <Map<String, Object>> getDeptPageList(int page){
//		int pageSize = 5;
//		PageHelper.startPage(page, pageSize);
//		return mapper.SelectDeptJoin();
//				
//	}
//	public List<Map<String, Object>> getEmpPageList(int page){
//		int pageSize = 10;//한페이지에 보여줄 게시물 수
//		PageHelper.startPage(page, pageSize);
//		return mapper.selectEmp();
//	}
}
