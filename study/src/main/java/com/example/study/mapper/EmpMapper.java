package com.example.study.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.study.vo.DeptVO;
import com.example.study.vo.Empvo;

//@Mapper라고 선언해야 Spring이 run할 때 해당 interface를 작동시킨다
//Mapper역할은 SQL문과 자바를 연결해주는 다리
@Mapper
public interface EmpMapper {
	//인터페이스 에서는 메소드 정의만 구현하지 않는다
	public List<Empvo> selectEmp();
	public Empvo selectEmpOne(int empno);
	public DeptVO selectDeptOne(int deptno);
	//클린코드 규칙에서 파라미터 개수가 3개이상일 때는
	//객체로 넘겨준다
	public int insertEmp(Empvo vo);
	public int DeleteEmp(int empno);
	public int UpdateEmp(Empvo vo);
}
