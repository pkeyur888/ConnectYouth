package com.ConnectYouth.servelete;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
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
		
		User tempUser=new User();
		try {
			tempUser.userList=dbUser.findRequestList(user.getEmail());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		request.setAttribute("requestList", tempUser.userList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("friendRquest.jsp");		
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
