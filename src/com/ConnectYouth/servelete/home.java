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
import com.ConnectYouth.db.PostDbUtil;
import com.ConnectYouth.db.UserDbUtil;

/**
 * Servlet implementation class home
 */
@WebServlet("/home")
public class home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public home() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Resource(name="jdbc/connectyouth")
    private DataSource dataSource;
    private PostDbUtil postdb;
    private UserDbUtil userdb;
    private int requectCounter=0;
    
    
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		try {
			postdb = new PostDbUtil(dataSource);
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
		User user = (User) session.getAttribute("user");
		if(user == null)
		{
			 response.sendRedirect("login.jsp");
		}
		else {
		
	
		
		
		//Find RequestList
		try {
			user.setUserRequestList(userdb.findRequestList(user));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//Fetch all the post
		try {
			user.postList=postdb.selectAllPost();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 ArrayList<User> friendList=new ArrayList<>();
		//Fetch all the user for Search in home page
		try {
			user.userList=userdb.selectAllUser();
			friendList=userdb.friendList(user.getEmail());
			System.out.println(user.getEmail());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		session.setAttribute("requestCount", requectCounter);
		request.setAttribute("postList", user.postList);
		request.setAttribute("userList", user.userList);
		request.setAttribute("friendList", friendList);
		session.setAttribute("user", user);
		
		//Request Dispatcher
		RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
		
		dispatcher.forward(request, response);
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
