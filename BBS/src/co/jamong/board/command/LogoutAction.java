package co.jamong.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.jamong.board.common.Action;

public class LogoutAction implements Action {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 서버로 갈 필요없다. 세션만 삭제해 주면된다.
		HttpSession session = request.getSession();
		// session은 프로퍼티 값이라 string으로 형변환
		String name = (String) session.getAttribute("name");
		session.invalidate(); // 세션의 모든 값을 삭제
		request.setAttribute("name", name);

		return "jsp/main/logout.jsp";
	}
}
