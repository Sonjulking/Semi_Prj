package com.member.action;

import java.util.Random;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.servlet.http.HttpSession;

import com.member.model.MemberDAO;
import com.project.controller.Action;
import com.project.controller.ActionForward;
import com.sun.net.httpserver.HttpsConfigurator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberFindPwdAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// String memberId = request.getParameter("memberId");

		// 먼저 아이디로 회원정보를 받아오고 가져온 데이터에서 email값을 비교하여 존재하지 않으면 인증메일 보내지 못함
		/*
		 * Member m = new MemberService().memberLogin(memberId); if(m==null ||
		 * !m.getEmail().equals(email)) { req.setAttribute("msg",
		 * "아이디나 이메일 정보가 맞지 않습니다"); req.setAttribute("loc", "/member/searchPw");
		 * req.getRequestDispatcher("/views/common/msg.jsp").forward(req, resp); return;
		 * }
		 */
		// mail server 설정
		String user = "gamemanchoo@gmail.com";
		String password = "xcuodnzxqegxyvkg";// 호스트 패스워드

		// 메일 받을 주소
		/* String to_email = m.getEmail(); */
		String to_email = request.getParameter("email_find");
		
		MemberDAO dao = MemberDAO.getInstance();
		

		// SMTP 서버 정보를 설정한다.
		Properties prop = new Properties();

		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", 465);
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		// 인증 번호 생성기
		StringBuffer temp = new StringBuffer();
		Random rnd = new Random();
		for (int i = 0; i < 10; i++) {
			int rIndex = rnd.nextInt(3);
			switch (rIndex) {
			case 0:
				// a-z
				temp.append((char) ((int) (rnd.nextInt(26)) + 97));
				break;
			case 1:
				// A-Z
				temp.append((char) ((int) (rnd.nextInt(26)) + 65));
				break;
			case 2:
				// 0-9
				temp.append((rnd.nextInt(10)));
				break;
			}
		}
		String AuthenticationKey = temp.toString(); // 임시 인증키
		System.out.println(AuthenticationKey);

		// SMTP 서버 정보와 사용자 정보를 기반으로 Session 클래스의 인스턴스를 생성합니다.
		// Properties에 저장디어있는 설정 값을 getDefaultInstance() 메소드로 설정값을 저장하여 세션 생성
		// getPasswordAuthentication() 메서드는 Authenticator 클래스의 메서드로, 이메일 클라이언트가 이메일 서버에
		// 인증할 때 사용되는 사용자 이름과 비밀번호를 반환합니다.

		// return new PasswordAuthentication(user, password); 구문은
		// getPasswordAuthentication() 메서드에서 PasswordAuthentication 객체를 생성하고, 이 객체를
		// 반환합니다.
		// PasswordAuthentication 클래스는 사용자 이름과 비밀번호를 포함하는 인증 정보를 나타내는 클래스입니다. 이 클래스의
		// 생성자에는 사용자 이름과 비밀번호가 인수로 전달되며, getUserName() 및 getPassword() 메서드를 사용하여 이 정보에
		// 접근할 수 있습니다.

		// prop은 위에서 설정한 정보.

		// 즉 위에서 설정한 정보를 바탕으로 메일주소와 비밀번호를 받아서 세션을 생성함.
		// 만들어진 세션으로 이메일 서버에 인증할 때 사용
		Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password); // 보내는 사람 메일주소와 패스워드
			}
		});

		// email 전송
		// MimeMessage 클래스는 JavaMail API에서 이메일 메시지를 나타내는 클래스입니다. MimeMessage 객체는 이메일의
		// 제목, 본문, 수신자, 발신자 등의 정보를 포함하며, JavaMail API를 사용하여 이메일을 작성하고 보내는 데 사용됩니다.
		// MimeMessage 객체를 생성하려면 Session 객체를 인수로 전달해야 합니다. Session 객체는 이메일 서버와의 연결 정보를
		// 포함하는 객체입니다.
		// 따라서 MimeMessage msg = new MimeMessage(session); 구문은 session 변수를 인수로 사용하여 새로운
		// MimeMessage 객체를 생성합니다. 이 객체는 이메일의 기본 정보를 포함하며, 이후에 setSubject(), setText(),
		// setRecipients() 등의 메서드를 사용하여 추가 정보를 설정할 수 있습니다.
		// 생성된 MimeMessage 객체는 JavaMail API를 사용하여 이메일을 작성하고 보내는 데 사용됩니다.

		/*
		 * TO (To): 이메일 메시지의 주요 수신자를 나타냅니다. 이메일 메시지를 작성할 때 반드시 지정해야 하는 필수 항목입니다.
		 * 
		 * CC (Carbon Copy): 이메일 메시지의 참조 수신자를 나타냅니다. 이메일 메시지를 작성할 때 선택적으로 지정할 수 있습니다.
		 * 
		 * BCC (Blind Carbon Copy): 이메일 메시지의 숨은 참조 수신자를 나타냅니다. CC와 마찬가지로 선택적으로 지정할 수
		 * 있지만, 수신자 목록에서 숨겨져 있어 다른 수신자들이 BCC로 지정된 수신자를 볼 수 없습니다.
		 */
		try {
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(user, "겜만추")); // 보내는 사람
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to_email)); // 받는 사람

			// 메일 제목
			msg.setSubject("오! 메일이 도착했어요~");
			// 메일 내용
			msg.setText("인증 번호는 :" + temp);

			Transport.send(msg);
			System.out.println("이메일 전송");

		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
		HttpSession saveKey = request.getSession();
		saveKey.setAttribute("AuthenticationKey", AuthenticationKey);
		// 패스워드 바꿀때 뭘 바꿀지 조건에 들어가는 id
		/*
		 * req.setAttribute("id", memberId);
		 * req.getRequestDispatcher("/views/login_myPage/searchPasswordEnd.jsp").forward
		 * (req, resp);
		 */
		String userId = request.getParameter("id");
		HttpSession session1 = request.getSession();
		session1.setAttribute("UserId", userId);
		request.setAttribute("TempPwd", temp);
		
		int check = dao.emailCheck(to_email);
		int id = dao.findId(userId, to_email);
		PrintWriter out = response.getWriter();
		if (check == 1 && id == 1) {
			out.println("<script>");
			out.println("alert('받은메일함과 스팸메일함에서 아이디를 확인하세요~')");
			out.println("location.href='member/changePwd.jsp'");
			out.println("</script>");
		} else if(id == 0) {
			out.println("<script>");
			out.println("alert('아이디 틀림')");
			out.println("history.back()");
			out.println("</script>");
			
		} else {
			out.println("<script>");
			out.println("alert('이메일 틀림')");
			out.println("history.back()");
			out.println("</script>");
		}
		return null;
	}

}