package co.jamong.board.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.jamong.board.command.LoginAction;
import co.jamong.board.command.LoginForm;
import co.jamong.board.command.LogoutAction;
import co.jamong.board.command.MainAction;
import co.jamong.board.command.MemberForm;
import co.jamong.board.command.MemberInsertAction;
import co.jamong.board.command.MemberListAction;
import co.jamong.board.common.Action;

@WebServlet("/FrontController")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Action> map = new HashMap<String, Action>();

	public FrontController() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		// 요청 정의. (요청페이지명, 명령)
		map.put("/main.do", new MainAction()); // 처음 브라우저에 접근했을 때 사용
		map.put("/login.do", new LoginAction()); // 로그인 메뉴 처리
		map.put("/loginForm.do", new LoginForm());	// 로그인 폼 호출
		map.put("/memberList.do", new MemberListAction());	// 회원 전체 리스트보기
		map.put("/memberForm.do", new MemberForm());	// 회원 가입 폼 호출
		map.put("/memberInsert.do", new MemberInsertAction());
		map.put("/logout.do", new LogoutAction());
//		map.put("/memberList.do", new ());
//		map.put("/memberList.do", new ());
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 실제 수행할 명령 정리
		request.setCharacterEncoding("utf-8"); // 한글 처리
		// 실제 요청하는 Path를 구하기 위한 것
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String path = uri.substring(contextPath.length()); // 실제 요청 들어오는 요청 페이지
		
		// 인터페이스 Action으로 command라는걸 생성
		Action command = map.get(path);	// path : /main.do 같은거 -> MainAction()을 찾아온다.
		String viewPage = command.exec(request, response);	// 명령어가 수행되는 부분. 명령이 수행되고 나서 보여줄 페이지 선택(String 타입으로 반환)
		
		// 내가 요청(request)하는 객체 그대로 전달
		// viewPage : 내 요청 객체를 전달해줄 페이지
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);	// 선택된 페이지로 가기
		dispatcher.forward(request, response);	// 페이지 return 시켜줌(forward)
	}

}
