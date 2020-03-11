package com.ConnectYouth.servelete;

import java.io.IOException;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.ConnectYouth.Model.Message;
import com.ConnectYouth.Model.Post;
import com.ConnectYouth.Model.User;
import com.ConnectYouth.db.PostDbUtil;
import com.ConnectYouth.db.UserDbUtil;

/**
 * Servlet implementation class message
 */
@WebServlet("/message")
public class message extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public message() {
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
		User user = (User) session.getAttribute("user");
		
		try {
			user.userList=userdb.selectUserFriend(user.getEmail());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("userList", user.userList);
		session.setAttribute("user", user);
		
		String reciverMsgEmail=request.getParameter("reciverMsg");
		if(reciverMsgEmail != null) {
			System.out.println(reciverMsgEmail);
			System.out.println(user.getEmail());
			try {
				
				
				user.messageList=userdb.selectUserMsg(user.getEmail(),reciverMsgEmail);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		reciverMsgEmail=null;
		
		//Request Dispatcher
		request.setAttribute("userList", user.userList);
		request.setAttribute("messageList", user.messageList);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
		
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
