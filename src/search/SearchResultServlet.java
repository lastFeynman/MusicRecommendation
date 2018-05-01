package search;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SearchResultServlet
 */
@WebServlet("/SearchResultServlet")
public class SearchResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchResultServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	this.doPost(request,response);
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		//得到搜索内容及用户id
		String searchContent;
		String userID,userName;
		searchContent = request.getParameter("search-input");
		userID = request.getParameter("userID");
		userName = request.getParameter("userName");
		//连接数据库
		String driverName = "oracle.jdbc.driver.OracleDriver";
		Driver dbDriver;
		Connection dbConnection;
		Statement dbStmt;
		ResultSet dbResults = null;
		
		if(searchContent.length() == 0){
			try{
				dbDriver = (Driver)Class.forName(driverName).newInstance();
				dbConnection = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:MusicTest","system","123456");
				dbStmt = dbConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				String sqlstr = "select * from Song";
				dbResults = dbStmt.executeQuery(sqlstr);

			}catch(Exception e){}
		}
		else{
			try{
				dbDriver = (Driver)Class.forName(driverName).newInstance();
				dbConnection = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:MusicTest","system","123456");
				dbStmt = dbConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				String sqlstr = "select * from Song where Song_name like '%"+searchContent+"%' or Song_singer like '%"+searchContent+"%'";
				dbResults = dbStmt.executeQuery(sqlstr);

			}catch(Exception e){}
		}
		
		request.setAttribute("userID", userID);
		request.setAttribute("userName", userName);
		session.setAttribute("searchResult", dbResults);
		request.setAttribute("askPage", 1);
		request.setAttribute("pageNow", 0);
		//将搜索结果交给分页Servlet处理
		request.getServletContext().getRequestDispatcher("/PageDivideServlet").forward(request, response);
	}

}
