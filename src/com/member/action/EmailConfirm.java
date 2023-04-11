package com.member.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
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

import com.project.controller.Action;
import com.project.controller.ActionForward;

public class EmailConfirm implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, MessagingException, Exception {
		// 
		String to_email = request.getParameter("email_confirm");

		String user = "gamemanchoo@gmail.com";
		String password = "xcuodnzxqegxyvkg";
		Properties prop = new Properties();

		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", 465);
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		
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
					
		String subject = "겜만추 이메일 인증 메일입니다.";
		String content = "다음 링크에 접속하여 이메일 인증을 진행하세요." + "<a href='" + temp + "'>이메일 인증하기</a>";
		Session session = Session.getDefaultInstance(prop, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password); // 보내는 사람 메일주소와 패스워드
			}
		});
		
		PrintWriter out = response.getWriter();
		
		try {
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(user, "겜만추")); // 보내는 사람
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to_email));
			// 메일 제목
			msg.setSubject(subject);
			// 메일 내용
			msg.setText(content, "text/html");

			Transport.send(msg);
			System.out.println("이메일 전송");
		    out.print("<script>");
		    out.print("alert('인증메일 발송 완료~! 받은메일함과 스팸메일함을 확인하세요!')");
		    out.print("</script>");

		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
			out.print("<script>");
		    out.print("alert('인증메일 발송 실패')");
		    out.print("</script>");
		}
		return null;
	}

}
