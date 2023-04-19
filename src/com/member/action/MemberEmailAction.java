package com.member.action;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
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

public class MemberEmailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, MessagingException, Exception {

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

		String member_id = multi.getParameter("id").trim();
		String member_pwd = multi.getParameter("pwd").trim();
		String member_nickname = multi.getParameter("name").trim();
		String member_email = multi.getParameter("email").trim();
		String member_phone = multi.getParameter("phone").trim();
		String prefer_lol = multi.getParameter("lol");
		String prefer_battle_ground = multi.getParameter("battle_ground");
		String prefer_overwatch = multi.getParameter("overwatch");
		String member_profile = multi.getFilesystemName("profile_img");

		System.out.println("프로핋 이미지 Email" + member_profile);

		request.setAttribute("ID", member_id);
		request.setAttribute("Member_Pwd", member_pwd);
		request.setAttribute("Member_Nickname", member_nickname);
		request.setAttribute("Member_Email", member_email);
		request.setAttribute("Member_Phone", member_phone);
		request.setAttribute("Prefer_lol", prefer_lol);
		request.setAttribute("Prefer_battle_ground", prefer_battle_ground);
		request.setAttribute("Prefer_overwatch", prefer_overwatch);
		request.setAttribute("Member_Profile", member_profile);

		// <%=request.getParameter("id").trim()%>

//		MemberDTO dto = new MemberDTO();

//		dto.setMember_id(member_id);
//		dto.setMember_pwd(member_pwd);
//		dto.setMember_nickname(member_nickname);
//		dto.setMember_email(member_email);
//		dto.setPhone(member_phone);
//		dto.setPrefer_lol(prefer_lol);
//		dto.setPrefer_battle_ground(prefer_battle_ground);
//		dto.setPrefer_overwatch(prefer_overwatch);
//		dto.setMember_profile(member_profile);
		
		// 임시키 메일 보내기
		String user = "gamemanchoo@gmail.com";
		String password = "xcuodnzxqegxyvkg";// 호스트 패스워드

		// 메일 받을 주소
		/* String to_email = m.getEmail(); */
		String to_email = member_email;


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
			msg.setSubject("오! 겜만추 회원가입 인증 메일이 도착했어요~");
			// 메일 내용
			msg.setText("인증 번호는 :" + temp);

			Transport.send(msg);
			System.out.println("이메일 전송");

		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
		HttpSession saveKey = request.getSession();
		saveKey.setAttribute("AuthenticationKey", AuthenticationKey);
		request.setAttribute("AuthenticationKey", AuthenticationKey);
		PrintWriter out = response.getWriter();
		
		System.out.println("이메일 인증키 >>>" +temp);
		System.out.println(AuthenticationKey);
		ActionForward forward = new ActionForward();
		if(AuthenticationKey != null) {
			forward.setRedirect(false);
			forward.setPath("./member/emailCheck.jsp");
		} else {
			out.println("<script>");
			out.println("alert('인증메일 발송 실패, 메일주소 확인해주세요~')");
			out.println("history.back()");
			out.println("</script>");
		}
		return forward;
	}

}
