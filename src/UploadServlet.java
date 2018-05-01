
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Date;
import java.text.*;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import DB.DBUtil;
import java.sql.*;
 

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
    private static final long serialVersionUID = 2L;
     
    // �ϴ��ļ��洢Ŀ¼
    private static final String UPLOAD_DIRECTORY = "E:/apache-tomcat-9.0.0.M21/webapps/upload/upload";
 
    // �ϴ�����
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
    
    //�ϴ��ļ���flag(������)
    private static final int MUSIC_FILE = 0;
    private static final int COVER_FILE = 0;
    private static final int LRC_FILE = 0;
    
    /**
     * �ϴ����ݼ������ļ�
     */
    protected void doPost(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		// ����Ƿ�Ϊ��ý���ϴ�
		if (!ServletFileUpload.isMultipartContent(request)) {
		    // ���������ֹͣ
		    PrintWriter writer = response.getWriter();
		    writer.println("Error: ��������� enctype=multipart/form-data");
		    writer.flush();
		    return;
		}

        // �����ϴ�����
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // �����ڴ��ٽ�ֵ - �����󽫲�����ʱ�ļ����洢����ʱĿ¼��
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // ������ʱ�洢Ŀ¼
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
 
        ServletFileUpload upload = new ServletFileUpload(factory);
         
        // ��������ļ��ϴ�ֵ
        upload.setFileSizeMax(MAX_FILE_SIZE);
         
        // �����������ֵ (�����ļ��ͱ�����)
        upload.setSizeMax(MAX_REQUEST_SIZE);

        // ���Ĵ���
        upload.setHeaderEncoding("UTF-8"); 

        // ������ʱ·�����洢�ϴ����ļ�
        // ���·����Ե�ǰӦ�õ�Ŀ¼
        String uploadPath = request.getServletContext().getRealPath("./") + File.separator + UPLOAD_DIRECTORY;
       
         
        // ���Ŀ¼�������򴴽�
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String singer = request.getParameter("singer");
		String album = request.getParameter("album");
		String style = request.getParameter("style");
		String category = request.getParameter("category");
		String scene = request.getParameter("scene");
		/*
		int style = Integer.parseInt((String)request.getParameter("style"));
		int category = Integer.parseInt((String)request.getParameter("category"));
		int scene = Integer.parseInt((String)request.getParameter("scene"));
		*/
		String url = new String();
		String cover = new String();
		String lrc = new String();
		
	    Date today = new Date( );
	    SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
		String date = ft.format(today);
 
        try {
            // ���������������ȡ�ļ�����
            @SuppressWarnings("unchecked")
            
            
            List<FileItem> formItems = upload.parseRequest(request);
 
            if (formItems != null && formItems.size() > 0) {
                // ����������
            	int fileFlag = 0;
                for (FileItem item : formItems) {
                    // �����ڱ��е��ֶ�
                	
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        String filePath = UPLOAD_DIRECTORY  + "/" + fileName;
                        File storeFile = new File(filePath);
                        // �ڿ���̨����ļ����ϴ�·��
                        System.out.println(filePath);
                        fileFlag++;
                        if(fileFlag == MUSIC_FILE){
                        	url = filePath;
                        }
                        if(fileFlag == COVER_FILE){
                        	cover = filePath;
                        }
                        if(fileFlag == LRC_FILE){
                        	lrc = filePath;
                        }
                        // �����ļ���Ӳ��
                        item.write(storeFile);
                        
                        response.setContentType("text/html;charset=utf-8");
    					PrintWriter out = response.getWriter();
    					out.print("<script>alert('�ϴ��ɹ�'); window.location='addMusic.jsp'</script>");
    					out.flush();
    					out.close();
                    }
                }
            }
        } catch (Exception ex) {
            PrintWriter out = response.getWriter();
            out.print("<script>alert('" + ex.getMessage() + "'); window.location='addMusic.jsp'</script>");
        }
    System.out.println(id);
    System.out.println(name);
    System.out.println(singer);
    System.out.println(album);
    System.out.println(style);
    System.out.println(category);
    System.out.println(scene);
    System.out.println(url);
    System.out.println(cover);
    System.out.println(lrc);
    System.out.println(date);
    }
}