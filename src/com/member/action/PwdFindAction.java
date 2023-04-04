package com.member.action;

import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class PwdFindAction {
        String user = "이메일주소 입력";
        String password = "비밀번호 입력";
        
        public String sendEmail(String to) throws Exception{
        	String result = "fail";
        	Properties prop = new Properties();
        	prop.put("mail.stmp.host", "stmp.gmail.com");
        	prop.put("mail.stmp.port", 465);
        	prop.put("mail.stmp.auth", "true");
        	prop.put("mail.stmp.ssl.enable", "true");
        	prop.put("mail.stmp.ssl.trust", "stmp.gmail.com");
        	
        	Session session = Session.getDefaultInstance(prop, new Authenticator() {
        		@SuppressWarnings("unused")
				protected PasswordAuthentication getAuthentication() {
        			return new javax.mail.PasswordAuthentication(user, password);
        		}
			});
        	
        	Random ran = new Random();
        	
        	StringBuffer buffer = new StringBuffer();
        	
        	for(int i = 0; i < 6; i++) {
        		if (ran.nextBoolean()) {
        			buffer.append((int)(ran.nextInt(10)));        			
        		} else {
        			buffer.append((char)((int)(Math.random()*26)+65));
        		}
        	}
        	
        	MimeMessage message = new MimeMessage(session);
        	message.setFrom(new InternetAddress());
        	
        	message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        	
        	message.setSubject("겜만추 비밀번호 찾기 메일입니다.");
        	
        	message.setText("비밀번호 변경 인증번호는 [ "+ buffer +" ] 입니다.");
        	
        	
        	Transport.send(message);
        	System.out.println("메일 보내기 성공");
        	return result = buffer.toString();
        }
        
        
}
