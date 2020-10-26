package co.jamong.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.jamong.board.common.Action;
import co.jamong.board.dao.MemberDao;
import co.jamong.board.vo.MemberVO;

public class LoginAction implements Action {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 로그인 인증 과정을 처리한다.
		MemberDao dao = new MemberDao();
		MemberVO vo = new MemberVO();
		HttpSession session = request.getSession(false);

		vo.setId(request.getParameter("id"));
		vo.setPassword(request.getParameter("pwd"));

		vo = dao.select(vo); // MemberDao를 실행
//		if(vo.getName() == null) {
//			// msg 있으면 실해. 없으면 성공
//			// 원래는 true, false 값을 return해서 로그인 성공을 확인한다.
//			request.setAttribute("msg", "등록된 회원정보가 없습니다.");
//		}

		// 세션에 값을 담을거다.
		session.setAttribute("id", vo.getId()); // session에 id 담기
		session.setAttribute("author", vo.getAuthor());
		session.setAttribute("name", vo.getName());

		// 기존의 request 객체에 vo라는 변수명을 사용해서 vo값을 담겠다.
		// 위에서 빈값 비교하기 싫으면 여기서 vo 보내고 jsp에서 받았을때 null을 비교해서 처리해줘도 된다.
		// 원래 여기서는 true/false를 받아서 비교해주는 정도만 하니까 여기서 비교하지말고 jsp 쪽에서 비교해주는걸 권장
		request.setAttribute("vo", vo); // 멤버를 담아서 보낸다.

		return "jsp/main/loginResult.jsp";
	}

}
