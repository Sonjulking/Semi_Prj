package com.project.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;	
		
		protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// 한글 처리 작업 진행
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			
			String uri = request.getRequestURI();
			
			String path = request.getContextPath();
			
			String command = uri.substring(path.length() + 1);
					
			Action action = null;
			ActionForward forward = null;
			Properties prop = new Properties();
			
			/*
			 * java.util.Properties 클래스
			 * - Properties 클래스는 HashTable의 하위(자식) 클래스
			 * - 보통은 환경변수 및 속성 값을 Properties 파일에 저장하여 쉽게 접근할 수 있는 장점이 있음.
			 * - Properties 파일은 일련의 키(key) - 값(value);
			 *   한 쌍으로 이루어져 있음.
			 * - 보통은 파일에 저장을 하여 사용을 함. 파일 이름을 *.properties 라는 이름으로 끝나게 함.
			 * - FileInputStream 클래스에 Properties 파일을 인자로 넣어서 그 스트림으로부터 파일을 가져올 때 많이 사용을 함.
			 *   인자로 들어온 Properties 파일을 읽게 됨.
			 * - 읽어 들일 때 사용하는 메서드는 load() 라는 메서드를 이용하여 파일을 읽어 들이게 됨.
			 */
			/*
			 * File path1 = new File("."); System.out.println(path1.getAbsolutePath());
			 */
			
			String path2 = FrontController.class.getResource("").getPath();
			FileInputStream fis = new FileInputStream(path2 + "mapping.properties");
			System.out.println(command);
			prop.load(fis);
			String value = prop.getProperty(command);
			
			if(value.substring(0, 7).equals("execute")) {
				StringTokenizer st = new StringTokenizer(value, "|");
				String url1 = st.nextToken();	// "execute"
				String url2 = st.nextToken();	// "패키지명.클래스명"
				
				
				try {
					Class url = Class.forName(url2);
					//action = (Action)url.newInstance();
					Constructor constructor = url.getConstructor();
					action = (Action)constructor.newInstance();
					forward = action.execute(request, response);
						
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			} else {	// value 에 들어온 값이 "execute가 아닌 경우
				// view page로 이동
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath(value);
			} 
			
			if(forward!=null) {
				if (forward.isRedirect()) { // true 인 경우
					response.sendRedirect(forward.getPath());
				} else { // false 인 경우 view page로 이동
					RequestDispatcher rd = request.getRequestDispatcher(forward.getPath());
					rd.forward(request, response);
				}
			}
			
		}
}

