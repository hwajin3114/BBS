package co.jamong.board.command;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.jamong.board.common.Action;
import co.jamong.board.dao.MemberDao;
import co.jamong.board.vo.MemberVO;

public class MemberInsertAction implements Action {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		MemberDao dao = new MemberDao();
		MemberVO vo = new MemberVO();

		vo.setId(request.getParameter("id"));
		vo.setName(request.getParameter("name"));
		vo.setPassword(request.getParameter("pwd"));
		vo.setAddress(request.getParameter("address"));
		vo.setTel(request.getParameter("tel"));
		vo.setEnterdate(Date.valueOf(request.getParameter("enterdate")));

		int n = dao.insert(vo);
		String page;
		if (n != 0) { // 회원가입 성공
			page = "jsp/member/insertSuccess.jsp";
		} else { // 회원가입 실패
			page = "jsp/member/insertFail.jsp";
		}

		return page;
	}
}
