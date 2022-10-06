package com.dw.emp.sevice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dw.emp.mapper.EmpMapper;
import com.dw.emp.vo.EmpVO;
import com.github.pagehelper.PageHelper;

@Service //비즈니스 계층(=고객 요구사항)을 여기서 구현하겠다.....
//서비스 로직 = 비즈니스 로직
public class EmpService {
	@Autowired
	private EmpMapper mapper;
	//조회하는 과정에서
	//수정, 삭제, 추가가 이루어 질 수도 있다
	public List<EmpVO> getEmpList(int deptno){
		/*
		 * 1.사원들 comm 10%인상 후 사원 조회
		 * 2.가장 보너스를 많이 받은 사람 delete하기
		 * */
		int rows = mapper.updateEmpComm(deptno);
		
		if(rows>0) {//update가 완료됨
			List <EmpVO> list = mapper.selectEmpByDeptno(deptno);
			
			int sal = 0;
			List<Integer>empNoList = new ArrayList<Integer>();//가장 급여 많이 받는 사원list
			for(EmpVO vo : list)
			{
				if(sal<=vo.getSal()) {
					sal = vo.getSal();
					empNoList.add(vo.getEmpno());
				}
			}
			for(int empno:empNoList)
			{
				System.out.println("가장 급여를 많이 받는 사원번호:"+empno);
				//mapper.deleteEmp(empno);
				
			}
			list = mapper.selectEmpByDeptno(deptno);
			return list;
		}
		return null;//rows가 0이면 null리턴
	}
	
	public List<Map<String, Object>> getEmpPageList(int page){
		int pageSize = 10;//한페이지에 보여줄 게시물 수
		PageHelper.startPage(page, pageSize);
		return mapper.selectEmp();
	}
	public Map<String, Object> getEmpStatistics(){
		return mapper.selectEmpStatistics();
	}

	public int EmpInsert(EmpVO vo) {
		//이미 가입된 사원번호인지 체크하기
		int empno = vo.getEmpno();//사원번호를 불러온다
		int count = mapper.selectCountByEmpno(empno);//이미 가입된 회원 쿼리 로직 호출
		if(count >0) {//만약에 count가 1이면 이미 가입된 사원
			return 0;
		}
		return mapper.EmpInsert(vo);
	}
	//특정 사원 조회
	public EmpVO getEmpByEmpno(int empno)
	{
		return mapper.selectEmpByEmpno(empno);
	}
	//특정 사원 정보 수정
	public int getEmpByUpdate(EmpVO vo)
	{
		return mapper.updateEmp(vo);
	}
//	//특정 회원 탈퇴(삭제)
//	public int getFireEmp(int empno)
//	{
//		return mapper.fireEmp(empno);
//	}
	//특정 회원 탈퇴(삭제)
	public int getFireEmp(int empno) {
		// TODO Auto-generated method stub
		return mapper.fireEmp(empno);
	}
	
}
