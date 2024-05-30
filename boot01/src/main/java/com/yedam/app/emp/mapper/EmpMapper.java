package com.yedam.app.emp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yedam.app.emp.service.EmpVO;

@Mapper
public interface EmpMapper {
	// DAO - Data Access Object를 위한 Interface
	
	// 전체조회 - SELECT문, 조건 X, 결과 다수
	public List<EmpVO> selectEmpAll();
	// 단건조회
	// 등록
	// 수정
	// 삭제
}
