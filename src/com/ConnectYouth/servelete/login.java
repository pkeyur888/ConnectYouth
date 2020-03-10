package com.ConnectYouth.servelete;

import java.io.IOException;

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
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
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
		String logout=request.getParameter("logout");
		if(logout != null) {
			session.removeAttribute("user");
			response.sendRedirect("login.jsp");
		}
		else
		{
		
		
		User tempUser = new User();
		tempUser.setEmail(request.getParameter("email"));
		tempUser.setPassword(request.getParameter("password"));
		User founduser=tempUser.Login(userdb);
		
		

			if(founduser!=null && tempUser.getPassword().equals(founduser.getPassword()))
			{		
				session.setAttribute("user",founduser);
				System.out.println("after Login");
				System.out.println(founduser.getFname());
				response.sendRedirect("profile");
			}
			else
			{
				response.sendRedirect("login.jsp");		
			}
			
			
						
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
