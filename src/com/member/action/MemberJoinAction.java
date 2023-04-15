package com.member.action;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemberDAO;
import com.member.model.MemberDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.project.controller.Action;
import com.project.controller.ActionForward;

public class MemberJoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		// 파일 업로드 할 때 설정해야할 내용.
		// 1.첨부 파일 저장 경로 지정.
//		String saveFolder = "C:\\NCS\\workspace(jsp2)\\15_Board_FileUpload\\src\\main\\webapp\\fileUpload";
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(request.getServletContext()
				.getRealPath("\\WEB-INF\\classes\\com\\project\\controller\\mapping.properties"));
		prop.load(fis);
		fis.close();
//		System.out.println(System.getenv("USERPROFILE")); //자기 컴터 이름 궁금하면 주석풀고 해보세요.
		// 시스템안에있는 환경변수중에서 USERPROFILE를 따옵니다. 그게 보통 C:\Users\KangChan 이렇게 나오는데 앞에 3개를
		// 없애요.
		// 그러면 Users\Kangchan이 나오겠죠.(미리 프로퍼티스에 저장해둬요 절대경로를) 거기에 폴더 경로(현재는 join)를 붙여줍니다.
		String saveFolder = prop.getProperty(System.getenv("USERPROFILE").substring(3)) + "\\join";
		// 2.첨부 파일 크기 지정.

		int fileSize = 20 * 1024 * 1024; // 20mb

		// 3.MultipartRequest 객체 생성 ==> 파일 업로드를 진행하기 위한 객체 생성

		MultipartRequest multi = new MultipartRequest(request, saveFolder, fileSize, "UTF-8",
				new DefaultFileRenamePolicy());
		// request : 일반적인 request 객체
		// saveDirectory 첨부파일이 저장될 경로
		// maxPostSize업로드할 첨부파일의 최대 사이즈
		// "UTF-8" 문자 인코딩 방식
		// new DefaultFileRenamePolicy() : 첨부 파일의 이름이 같은 경우 중복이 안되게 설정

		String input_temp_key = multi.getParameter("temp_key");
		String email_temp_key = multi.getParameter("email_key");
		System.out.println(input_temp_key);
		System.out.println(email_temp_key);
		PrintWriter out = response.getWriter();
		System.out.println(input_temp_key.equals(email_temp_key));
		if(!input_temp_key.equals(email_temp_key)) {
			out.println("<script>");
			out.println("alert('인증키 틀림! 다시 입력해 주세요~')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		String member_id = multi.getParameter("id");
		String member_pwd = multi.getParameter("pwd");
		String member_nickname = multi.getParameter("name");
		String member_email = multi.getParameter("email");
		String member_phone = multi.getParameter("phone");
		String prefer_lol = multi.getParameter("lol");
		String prefer_battle_ground = multi.getParameter("battle_ground");
		String prefer_overwatch = multi.getParameter("overwatch");
		String member_profile = multi.getParameter("profile_img");

		System.out.println("프로핋 이미지 조인액션" + member_profile);

		MemberDTO dto = new MemberDTO();
		dto.setMember_id(member_id);
		dto.setMember_pwd(member_pwd);
		dto.setMember_nickname(member_nickname);
		dto.setMember_email(member_email);
		dto.setPhone(member_phone);
		dto.setPrefer_lol(prefer_lol);
		dto.setPrefer_battle_ground(prefer_battle_ground);
		dto.setPrefer_overwatch(prefer_overwatch);
		dto.setMember_profile(member_profile);

		MemberDAO dao = MemberDAO.getInstance();
		

		
		// ActionForward forward = new ActionForward();
		int check = dao.memberInsert(dto);
		

		System.out.println("action" + check);
		if (check > 0) {
			out.println("<script>");
			out.println("alert('회원가입 성공!')");
			out.println("location.href='main.jsp'");
			out.println("</script>");
		} else {

			out.println("<script>");
			out.println("alert('회원가입 실패~')");
			out.println("history.back()");
			out.println("</script>");
		}
		return null;

	}

}
