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
 * Servlet implementation class signup
 */
@WebServlet("/signup")
public class signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public signup() {
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
		User tempUser = new User();
		tempUser.setEmail(request.getParameter("fname"));
		tempUser.setEmail(request.getParameter("lname"));
		tempUser.setEmail(request.getParameter("email"));
		tempUser.setPassword(request.getParameter("password"));
		;

		if(tempUser.Register(userdb)) {
			session.setAttribute("user",tempUser);
			response.sendRedirect("home.jsp");			
		}
		else
		{
			response.sendRedirect("index.jsp");		
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
