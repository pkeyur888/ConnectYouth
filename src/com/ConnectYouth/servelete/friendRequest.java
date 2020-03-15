package com.ConnectYouth.servelete;

import java.io.IOException;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.ConnectYouth.Model.User;
import com.ConnectYouth.db.UserDbUtil;

/**
 * Servlet implementation class friendRequest
 */
@WebServlet("/friendRequest")
public class friendRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public friendRequest() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    @Resource(name="jdbc/connectyouth")
    private DataSource dataSource;
    private UserDbUtil userdb;
    
    @Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		try {
			userdb = new UserDbUtil(dataSource);
		}catch(Exception ex) {
			throw new ServletException(ex);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		UserDbUtil dbUser=new UserDbUtil(dataSource);
		User user = (User) session.getAttribute("user");
		
		try {
			user.userList=userdb.selectAllUser();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(request.getParameter("Accept") != null) {
			user.acceptRequest(userdb,request.getParameter("userEmail"));
		}else if(request.getParameter("Delete") != null) {
			user.deleteRequest(userdb,request.getParameter("userEmail"));
		}
		
		try {
			user.setUserRequestList(dbUser.findRequestList(user));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
		session.setAttribute("user", user);
		
	
		
		
		response.sendRedirect("friendRquest.jsp");
		
//		request.setAttribute("requestList", tempUser);
//		RequestDispatcher dispatcher = request.getRequestDispatcher("friendRquest.jsp");		
//		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
