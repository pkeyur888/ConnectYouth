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
 * Servlet implementation class addFriend
 */
@WebServlet("/addFriend")
public class addFriend extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addFriend() {
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
//		System.out.println(request.getParameter("addfriend"));
//		System.out.println(request.getParameter("friendEmail"));
		if(request.getParameter("addfriend") != null) {
			HttpSession session=request.getSession();
			User currentUser= (User) session.getAttribute("user");
			User tempUser=new User();
			
			tempUser.setEmail(currentUser.getEmail());
			tempUser.setRequestReciverID(request.getParameter("friendEmail"));
			tempUser.setFriendStatus("0");			
			if(tempUser.addFriend(userdb)) {
				response.sendRedirect("home");
			}
				
		}
		else {
			response.sendRedirect("home");
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
