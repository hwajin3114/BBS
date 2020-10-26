package co.jamong.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.jamong.board.common.Action;

public class MemberForm implements Action {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		return "jsp/member/memberForm.jsp";
	}
}