package com.matching.action;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.matching.model.MatchingDAO;
import com.project.controller.Action;
import com.project.controller.ActionForward;

public class MatchingDeleteOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, MessagingException, Exception {
		// 세션으로 저장된 로그인 아이디로 DB 삭제

		MatchingDAO dao = MatchingDAO.getInstance();

		int check = dao.deleteMatching();

		return null;
	}

}
