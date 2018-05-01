package search;

import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class PageDivideServlet
 */
@WebServlet("/PageDivideServlet")
public class PageDivideServlet extends HttpServlet {
	
	private static final int ROW_MAX = 9;
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PageDivideServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: 1234").append(request.getContextPath());
		
		HttpSession session = request.getSession();
		//�����û���Ϣ
		String userName;
		String userID;
		userName = (String)request.getAttribute("userName");
		userID = (String)request.getAttribute("userID");
		request.setAttribute("userName", userName);
		request.setAttribute("userID", userID);
		//Ҫ������һ��ҳ�����Ϣ
		String[] songID = new String[9];
		String[] songName = new String[9];
		String[] songSinger = new String[9];
		for(int i=0;i<9;i++){
			songID[i] = null;
			songName[i] = null;
			songSinger[i] = null;
		}
		int pageNowNext;
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//�õ����������ǰҳ������ҳ
		Object pageNowObj,pageAskObj;
		ResultSet rs = null;
		int pageNow = 1,pageAsk = 1;
		rs = (ResultSet)session.getAttribute("searchResult");
		pageNowObj = request.getAttribute("pageNow");
		pageAskObj = request.getAttribute("askPage");
		
		if(pageNowObj == null || pageAskObj == null){
			pageNowObj = request.getParameter("pageNow");
			pageAskObj = request.getParameter("pageAsk");
			if(pageNowObj == null || pageAskObj == null){
				//�������󴰿ڲ�������ҳ
				request.setAttribute("nullAlert","<script>alert('�����ˣ�������ҳ����')</script>");
				request.setAttribute("userID", userID);
				request.getServletContext().getRequestDispatcher("/UserIndex.jsp");
				return;
			}else if(((String)pageAskObj).length()==0){
				//��������ҳ�벢���ص�ǰҳ��
				request.setAttribute("noInputAlert", "<script>alert('��������ȷ��ҳ�룡��')</script>");
				pageAsk = Integer.parseInt((String)pageNowObj);
			}
			else{
				pageNow = Integer.parseInt((String)pageNowObj);
				pageAsk = Integer.parseInt((String)pageAskObj);
			}
		}else{
			pageNow = (int)pageNowObj;
			pageAsk = (int)pageAskObj;
		}

		//��������������ҳ��,���������ָ���Ƶ���һ��
		int rowAll=0,pageAll=0,rowAsk=1;
		try {
			if(rs != null){
				rs.last();
				rowAll = rs.getRow();
				rs.first();
			}
			//û�н��ת���޽��ҳ��
			else{
				request.getServletContext().getRequestDispatcher("/NoResult.jsp").forward(request, response);
				return;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(rowAll == 0){
			request.getServletContext().getRequestDispatcher("/NoResult.jsp").forward(request, response);
			return;
		}

		if(rowAll%ROW_MAX == 0)
			pageAll = rowAll/ROW_MAX;
		else
			pageAll = rowAll/ROW_MAX+1;
		
		if(pageAsk<=0 || pageAsk>pageAll){
			//���������ҳ�治���ڲ����ص�ǰҳ
			request.setAttribute("noExistAlert","<script>alert('�����ҳ�治���ڣ���')</script>");
			pageAsk = pageNow;
		}
		//�������ָ������ҳ��һ��
		for(int i=0;i<(pageAsk-1)*ROW_MAX;i++){
			try {
				rs.next();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for(int i=0;i<ROW_MAX;i++){
			try {
				songID[i] = (String)rs.getString("Song_Id");
				request.setAttribute("songID"+i, songID[i]);
				songName[i] = (String)rs.getString("Song_name");
				request.setAttribute("songName"+i, songName[i]);
				songSinger[i] = (String)rs.getString("Song_singer");
				request.setAttribute("songSinger"+i, songSinger[i]);
				if(!rs.next())
					break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		request.setAttribute("rowAll", rowAll);
		request.setAttribute("pageAll", pageAll);
		request.setAttribute("prePage", pageAsk-1);
		request.setAttribute("pageNow", pageAsk);
		request.setAttribute("pageNext", pageAsk+1);
		
		request.getServletContext().getRequestDispatcher("/SearchResults.jsp").forward(request, response);
		
	}

}
