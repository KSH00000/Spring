package com.yedam.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yedam.app.emp.mapper.EmpMapper;
import com.yedam.app.emp.service.EmpVO;

@SpringBootTest
class Boot02ApplicationTests {

	@Autowired
	EmpMapper empMapper;
	
	//@Test
	public void empList() {
		List<EmpVO> list = empMapper.selectEmpAll();
//		for(EmpVO vo : list) {
//			System.out.println(vo.toString());
//		}
		assertTrue(!list.isEmpty());
	}

	//@Test
	public void empInfo() {
		EmpVO empVO = new EmpVO();
		empVO.setEmployeeId(100);
		
		EmpVO findVO = empMapper.selectEmpInfo(empVO);
		assertEquals(findVO.getLastName(), "King");
		
	}
	
	//@Test
	public void empInsert() {
		EmpVO empVO = new EmpVO();
		empVO.setLastName("TEST");
		empVO.setEmail("TEST3@google.com");
		empVO.setJobId("IT_PROG");
		
		int result = empMapper.insertEmpInfo(empVO);
		System.out.println(empVO.getEmployeeId());
		assertEquals(result, 1);
	}
	
	//@Test
	public void empUpdt() {
		// -1) 대상 조회
		EmpVO empVO = new EmpVO();
		empVO.setEmployeeId(207);
		
		EmpVO findVO = empMapper.selectEmpInfo(empVO);
		System.out.println(findVO);
		
		// -2) 정보 수정
		findVO.setLastName("YEDAM");
		int result
			= empMapper.updateEmpInfo(findVO.getEmployeeId(), findVO);
		assertEquals(result, 1);
	}
	
	//@Test
	public void empDel() {
		int result = empMapper.deleteEmpInfo(207);
		assertEquals(result, 1);
	}
}
