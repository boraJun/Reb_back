package com.sol.app.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.sol.app.dto.MemberDTO;
import com.sol.app.dto.MemberSignupDTO;
import com.sol.config.MyBatisConfig;

public class MemberDAO {
	
	public SqlSession sqlSession;
	
	public MemberDAO() {
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}
	
	public int login(MemberDTO memberDTO) {
		Integer memberNumber = sqlSession.selectOne("member.login", memberDTO);
		// NullPointerException 방지
		return memberNumber == null ? -1 : memberNumber;
	}
	
	public void signup(MemberSignupDTO memberSignupDTO) {
		sqlSession.insert("member.signupAddress", memberSignupDTO);
		sqlSession.insert("member.signup", memberSignupDTO);
	}
	
	public boolean checkNickname(String memberNickname) {
		return (Integer) sqlSession.selectOne("member.checkNickname", memberNickname) < 1;
	}
	
	// 회원번호 반환 메소드
	public String getMemberId(int memberNumber) {
		return sqlSession.selectOne("member.getId", memberNumber);
	}
	
	public List<MemberDTO> findIds(MemberDTO memberDTO) {
        return sqlSession.selectList("member.findId", memberDTO);
    }
	
	public MemberDTO findPw(MemberDTO memberDTO) {
		return sqlSession.selectOne("member.findPw", memberDTO);
	}
	
	public boolean checkId(String memberId) {
		return (Integer) sqlSession.selectOne("member.checkId", memberId) < 1;
	}
	public MemberDTO findExpert(int memberNumber) {
		return sqlSession.selectOne("member.findExpert",memberNumber);
	}
	public String findName(int memberNumber) {
		return sqlSession.selectOne("member.findName",memberNumber);
	}
}
