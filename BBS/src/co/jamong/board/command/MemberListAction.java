package co.jamong.board.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.jamong.board.common.Action;
import co.jamong.board.dao.MemberDao;
import co.jamong.board.vo.MemberVO;

public class MemberListAction implements Action {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 회원 리스트 보기 구현
		MemberDao dao = new MemberDao(); // 인스턴스는 클래스명을 소문자로 적는걸 권장
		List<MemberVO> list = new ArrayList<MemberVO>();

		list = dao.selectAll();
		// members 변수는 jsp에서 사용하는 것
		request.setAttribute("members", list);

		return "jsp/member/memberList.jsp";
	}
}