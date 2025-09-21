package com.sol.app.club;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sol.app.Execute;
import com.sol.app.Result;
import com.sol.app.club.dao.FileSmallClubDAO;
import com.sol.app.club.dao.SmallClubDAO;
import com.sol.app.club.dao.SmallClubMemberApplicantDAO;
import com.sol.app.dto.FileMemberProfileDTO;
import com.sol.app.dto.FileSmallClubDTO;
import com.sol.app.dto.SmallClubDTO;
import com.sol.app.myPage.dao.FileMemberProfileDAO;

public class SmallClubDetailOkController implements Execute{

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Result result = new Result();
		
		String smallClubNumberStr = request.getParameter("smallClubNumber");
		SmallClubMemberApplicantDAO applicantDAO = new SmallClubMemberApplicantDAO();
		
		if (smallClubNumberStr == null || smallClubNumberStr.trim().isEmpty()) {
			System.out.println("smallClubNumber 값이 없습니다");
			result.setPath("/app/club/small-club-list.jsp");
			result.setRedirect(true);
			return result;
		}

		int smallClubNumber = Integer.parseInt(smallClubNumberStr);
		
		SmallClubDAO smallClubDAO = new SmallClubDAO();
		FileSmallClubDAO fileSmallClubDAO = new FileSmallClubDAO();
		FileMemberProfileDAO fileMemberProfileDAO = new FileMemberProfileDAO();
		
		SmallClubDTO smallClubDTO = smallClubDAO.select(smallClubNumber);
		
		if (smallClubDTO == null) {
			System.out.println("존재하지 않는 게시글입니다" + smallClubNumber);
			result.setPath("/app/club/small-club-list.jsp");
			result.setRedirect(true);
			return result;
		}
		
		// 게시글 첨부파일 가져오기
		List<FileSmallClubDTO> files = fileSmallClubDAO.select(smallClubNumber);
		System.out.println("=====확인=====");
		System.out.println(files);
		System.out.println("=============");
		
		smallClubDTO.setFileSmallClubList(files);
		
		// 로그인한 사용자 번호 가져오기
		int memberNumber = (Integer) request.getSession().getAttribute("memberNumber");
		System.out.println("로그인 한 멤버 번호 : " + memberNumber);
		
		// 현재 게시글의 작성자 번호 가져오기
		int smallClubWriterNumber = smallClubDTO.getMemberNumber();
		System.out.println("현재 게시글 작성자 번호 : " + smallClubWriterNumber);
		
		// 작성자 프로필 가져오기
		FileMemberProfileDTO profiles = fileMemberProfileDAO.select(smallClubWriterNumber);
		System.out.println("작성자 프로필 번호" + profiles);
		
		smallClubDTO.setFileWriterProFileList(profiles);
		
		Map<String, Integer> map = new HashMap<>();

		map.put("smallClubNumber", smallClubNumber);
		map.put("memberNumber", memberNumber);
		System.out.println(smallClubNumber + "|" + memberNumber);

		request.setAttribute("applicant", applicantDAO.select(map));
		System.out.println(applicantDAO.select(map));
		smallClubDTO.setSmallClubApplicantCount(applicantDAO.getCount(smallClubNumber));
		
		request.setAttribute("smallClub", smallClubDTO);
		result.setPath("/app/club/small-club-detail.jsp");
		result.setRedirect(false);
		
		return result;
	}
	
	
	
}
