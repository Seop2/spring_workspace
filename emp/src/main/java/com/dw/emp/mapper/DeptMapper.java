package com.dw.emp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.dw.emp.vo.DeptVO;

@Mapper
public interface DeptMapper {
	//부서번호 사원 조회
//		public List<DeptVO> selectEmpByDeptno(int deptno);
//		//사원 급여별로 comm 10% 지급	
//		public int updateEmpComm(int deptno);
//		public int deleteEmp(int empno);
//		
//		public List <Map<String, Object>> selectEmp();
//		public int EmpInsert(EmpVO vo);
//		public Map<String, Object>selectEmpStatistics();
//		
//		public EmpVO selectEmpByEmpno(int empno);
		//부서 추가
		public int InsertDept(DeptVO vo);
		//부서 수정
		public int UpdateDept(DeptVO vo);
		//부서삭제
		public int deleteDept(int deptno);
		//부서조회
		public List <Map<String,Object>> SelectDeptJoin();
		
}
