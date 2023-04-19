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

public class BoardModifyOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 수정 폼 페이지에서 넘어온 데이터들을 db에 전송하여 게시글을 수정하는 비지니스 로직
		
		BoardDTO dto = new BoardDTO();
		
		// 첨부파일이 저장될 위치(경로) 설정.
		//String saveFolder = "C:\\NCS\\workspace(jsp)\\project\\WebContent\\fileUpload";
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(request.getServletContext()
				.getRealPath("\\WEB-INF\\classes\\com\\project\\controller\\mapping.properties"));
		prop.load(new InputStreamReader(fis));
		fis.close();
		String saveFolder = prop.getProperty(System.getenv("USERPROFILE").substring(3));

		// 첨부파일 용량(크기) 제한 - 파일 업로드 최대 크기
		int fileSize = 10 * 1024 * 1024;  // 10MB
		
		// 이미지 파일 업로드를 위한 객체 생성
		MultipartRequest multi = new MultipartRequest(
				request, 
				saveFolder, 
				fileSize,
				"UTF-8",
				new DefaultFileRenamePolicy()
		);
		
		String board_type = multi.getParameter("type").trim();
		String board_heading = multi.getParameter("heading").trim();
		String board_title = multi.getParameter("title").trim();
		String board_cont = multi.getParameter("cont").trim();
		
		String old_type = multi.getParameter("old_type");
		System.out.println("old타입" + old_type);
		
		// type ="hidden"으로 넘어온 데이터들도 받아주어야 한다.
		int board_index = Integer.parseInt(multi.getParameter("num").trim());
		int nowPage = Integer.parseInt(multi.getParameter("page").trim());
		
		File upload_file = multi.getFile("new_file");

		if(upload_file != null) {
			String fileName = upload_file.getName();
			System.out.println("첨부파일 이름 >>> " + fileName);
			
			// 날짜 객체 생성
			Calendar cal = Calendar.getInstance();
			
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH) + 1;
			int day = cal.get(Calendar.DAY_OF_MONTH);
			
			// ....../fileUpload/2023-03-28
			String homedir = saveFolder+"/"+year+"-"+month+"-"+day;
			
			// 날짜 폴더를 만들어 보자
			File path1 = new File(homedir);  
			
			if(!path1.exists()) { // 폴더가 존재하지 않는 경우
				path1.mkdir();	//실제 폴더를 만들어주는 메서드
			}
			
			// 파일을 만들어 보자 ==> 예) 홍길동_파일명
			String reFileName = board_heading+"_"+fileName;
			
			// 파일 이름 변경
			upload_file.renameTo(new File(homedir+"/"+reFileName));
			
			// 실제로 DB에 저장되는 파일 이름
			// "/2023-03-28/홍길동_파일명)" 으로 저장할 예정
			String fileDBName = "/"+year+"-"+month+"-"+day+"/"+reFileName;
			
			dto.setUpload_file(fileDBName);
		}
		
		BoardDAO dao = BoardDAO.getInstance();
		
		dto.setBoard_type(board_type);
		dto.setBoard_heading(board_heading);
		dto.setBoard_title(board_title);
		dto.setBoard_cont(board_cont);
		dto.setBoard_index(board_index);
		
		int check = dao.updateBoard(dto, old_type);
		
		System.out.println("new타입2" + dto.getBoard_type());
		
		PrintWriter out = response.getWriter();
		
		if(check > 0) {
			if(!dto.getBoard_type().equals(old_type)) {
				BoardDTO cont = dao.boardContent(board_index, old_type);
				int maxCount = dao.maxCount(board_type) + 1;
				dao.deleteBoard(board_index, old_type);
				dao.updateSequence(board_index, old_type);
				dao.insertBoard(cont, board_type);
				
				System.out.println(maxCount);
				
				out.println("<script>");
				out.println("alert('게시물 수정 성공')");
				out.println("location.href='board_content.do?no="+maxCount+"&page="+nowPage+"&type="+board_type+"'");
				out.println("</script>");
			}else {
				out.println("<script>");
				out.println("alert('게시물 수정 성공')");
				out.println("location.href='board_content.do?no="+board_index+"&page="+nowPage+"&type="+board_type+"'");
				out.println("</script>");
			}
			
		}else {
			out.println("<script>");
			out.println("alert('게시물 수정 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
				
				
		return null;
	}

}
