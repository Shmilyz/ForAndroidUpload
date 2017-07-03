package com.upload;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.SmartFile;
import com.jspsmart.upload.SmartFiles;
import com.jspsmart.upload.SmartRequest;
import com.jspsmart.upload.SmartUpload;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/upload.do")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String , String> map = new HashMap<String, String>() ;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("====");
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter() ;
		String savePath = "upload" ;
	
		SmartUpload su = new SmartUpload("utf-8") ;
		su.initialize(getServletConfig(), request, response);
		su.setAllowedFilesList("jpg,png,bmp");
//		su.setDeniedFilesList("html,htm.exe");

		String filename=null ;
		// �ϴ�
		try {
			su.upload();
			SmartRequest srequest = su.getRequest() ;
			String username = srequest.getParameter("username") ;
			String files = srequest.getParameter("file") ;
				if(files=="headimage"){
					File file = new File("C:/"+files) ;  
					if(! file.exists() || ! file.isDirectory()) {
						file.mkdirs() ;		
					}
					SmartFiles uploadFiles = su.getFiles() ;  
					for(int i = 0 ; i < uploadFiles.getCount() ;i++) {
						SmartFile currentFile = uploadFiles.getFile(i) ;  
						filename =username + ".jpg"  ;
						String aa="C:/upload" + "/"+ filename;
						System.out.println(aa);
						currentFile.saveAs("C:/"+files + "/"+ filename);
						map.clear();
						map.put("result","1") ;
						out.print(map);
					
				}
				
				}
				else{
					
					File file = new File("C:/"+files) ;  
					if(! file.exists() || ! file.isDirectory()) {
						file.mkdirs() ;		
					}
					SmartFiles uploadFiles = su.getFiles() ;  
					for(int i = 0 ; i < uploadFiles.getCount() ;i++) {
						SmartFile currentFile = uploadFiles.getFile(i) ;  
						filename =username + ".jpg"  ;
						String aa="C:/upload" + "/"+ filename;
						System.out.println(aa);
						
						currentFile.saveAs("C:/"+files + "/"+ filename);
						map.clear();
						map.put("result","1") ;
						out.print(map);
					
				}
				
					
				}
			

				
				
				
			
			
		} 
		catch (Exception e) {
			e.printStackTrace();
			// �ϴ�ʧ�ܣ�ҳ��ת���ϴ�ҳ��
//			response.sendRedirect("UploadImg.jsp?errm=error!");
			System.out.println("uploadfail");
			map.clear();
			map.put("result", "0") ;
			out.print(map);
				
		} 

		
		// ��ȡ�ϴ�����������
		/*SmartRequest srequest = su.getRequest() ;
		String username = srequest.getParameter("username") ;
		out.println("username:" + username);

		// �ϴ��ɹ���ҳ��ת�� UploadSuccess.jsp������ʾ�û�����ͷ��
		RequestDispatcher dispatcher = request.getRequestDispatcher("UploadSuccess.jsp") ;
		request.setAttribute("uname", username);
		request.setAttribute("filepath", "upload");
		request.setAttribute("filename", filename);
//		dispatcher.forward(request, response);
*/		System.out.println("uploadok");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private String getCurrent() {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) ;
	}
}
