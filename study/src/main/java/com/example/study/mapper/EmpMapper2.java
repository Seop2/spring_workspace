package com.example.study.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.study.vo.DeptVO;
import com.example.study.vo.Empvo;

//@Mapper라고 선언해야 Spring이 run할 때 해당 interface를 작동시킨다
//Mapper역할은 SQL문과 자바를 연결해주는 다리
@Mapper
public interface EmpMapper2 {
	public List<Empvo> selectAllEmp();
	public int InsertEmp(Empvo vo);
	public int updateEmp(Empvo vo);
	public int DeleteEmp(int empno);
	public List<Map<String, Object>>selectEmpJoinDept();
	public List<Map<String, Object>>selectEmpAllJoinDept();
}
