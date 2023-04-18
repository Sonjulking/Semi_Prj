package com.board.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.project.controller.Action;
import com.project.controller.ActionForward;

public class BoardWriteOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 자료실 폼 페이지에서 넘어온 데이터들을 DB(upload)에 저장하는 비지니스 로직

		BoardDTO dto = new BoardDTO();

		// 파일 업로드 시에는 설정해야 할 내용이 있음.
		// 1. 첨부파일 저장 경로 지정(보통은 서버에 지정함)
		
		//String saveFolder = "C:\\NCS\\workspace(jsp)\\project\\WebContent\\fileUpload";
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(request.getServletContext()
				.getRealPath("\\WEB-INF\\classes\\com\\project\\controller\\mapping.properties"));
		prop.load(new InputStreamReader(fis));
		fis.close();
		String saveFolder = prop.getProperty(System.getenv("USERPROFILE").substring(3));
	// String saveFolder =
		// prop.getProperty(System.getenv("USERPROFILE").substring(3))+"\\review";
		// 2. 첨부 파일 크기 지정(20MB)
		int fileSize = 20 * 1024 * 1024;


		// 3. MultipartRequest 객체 생성
		// ==> 파일 업로드를 진행하기 위한 객체 생성 (cos라이브러리에 있는 클래스임)
		MultipartRequest multi = new MultipartRequest(request, // 일반적인 request 객체
				saveFolder, // 첨부파일이 저장될 경로
				fileSize, // 업로드할 첨부파일의 최대 크기
				"UTF-8", // 문자 인코딩 방식
				new DefaultFileRenamePolicy()
		// 첨부 파일의 이름이 같은 경우 중복이 안되게 설정
		);

		// 자료실 폼 페이지에서 넘어온 데이터들을 받아 주자
		String board_type = multi.getParameter("board_type").trim();
		String board_heading = multi.getParameter("board_heading").trim();
		String board_title = multi.getParameter("board_title").trim();
		String board_cont = multi.getParameter("board_cont").trim();

		// hidden 받아주기
		String board_writer_id = multi.getParameter("board_writer_id").trim();
		String board_writer_nickname = multi.getParameter("board_writer_nickname").trim();

		// 자료실 폼 페이지에서 type="file" 속성으로 되어 있으면
		// getFile() 메서드로 받아 주어야 함
		File upload_file = multi.getFile("upload_file");

		if (upload_file != null) { // 첨부파일이 있다면

			// 우선은 첨부파일의 이름을 알아야 함
			// getName() 메서드를 이용하면 첨부파일의 이름을 알 수 있음
			String fileName = upload_file.getName();
			System.out.println("첨부파일 이름 >>> " + fileName);

			// 날짜 객체 생성
			Calendar cal = Calendar.getInstance();

			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH) + 1;
			int day = cal.get(Calendar.DAY_OF_MONTH);

			// ....../fileUpload/2023-03-28
			String homedir = saveFolder + "/" + year + "-" + month + "-" + day;

			// 날짜 폴더를 만들어 보자
			File path1 = new File(homedir);

			if (!path1.exists()) { // 폴더가 존재하지 않는 경우
				path1.mkdir(); // 실제 폴더를 만들어주는 메서드
			}

			// 파일을 만들어 보자 ==> 예) 홍길동_파일명
			String reFileName = board_heading + "_" + fileName;

			// 파일 이름 변경
			upload_file.renameTo(new File(homedir + "/" + reFileName));

			// 실제로 DB에 저장되는 파일 이름
			// "/2023-03-28/홍길동_파일명)" 으로 저장할 예정
			String fileDBName = "/" + year + "-" + month + "-" + day + "/" + reFileName;

			dto.setUpload_file(fileDBName);
		}

		dto.setBoard_type(board_type);
		dto.setBoard_heading(board_heading);
		dto.setBoard_title(board_title);
		dto.setBoard_cont(board_cont);
		dto.setBoard_writer_id(board_writer_id);
		dto.setBoard_writer_nickname(board_writer_nickname);

		BoardDAO dao = BoardDAO.getInstance();

		int check = dao.insertBoard(dto, board_type);

		PrintWriter out = response.getWriter();

		if (check > 0) {
			out.println("<script>");
			out.println("alert('게시글 추가 성공')");
			out.println("location.href='board_list.do?type="+board_type+"'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('게시글 추가 실패')");
			out.println("history.back()");
			out.println("</script>");
		}

		return null;
	}

}
