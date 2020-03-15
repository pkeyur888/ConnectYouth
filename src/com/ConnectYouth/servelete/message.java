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
import com.ConnectYouth.db.MessageDbUtil;
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
    private MessageDbUtil messagedb;
    
   
    @Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		try {
			userdb = new UserDbUtil(dataSource);
			messagedb=new MessageDbUtil(dataSource);
			
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
		String reciverFname=null;
		reciverFname=request.getParameter("submit");
		
		String reciverMsgEmail=request.getParameter("reciverMsg");
		String submitMsg=request.getParameter("send");
		
		
		if(submitMsg != null) {
			 reciverFname=request.getParameter("reciverFname");
			 reciverMsgEmail=request.getParameter("reciverMsg");
			String msg=request.getParameter("writeMsg");
			user.sendMsg(reciverMsgEmail,msg,messagedb);
		}
		
		
		try {
			user.userList=userdb.selectUserFriend(user.getEmail());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("userList", user.userList);
		session.setAttribute("user", user);
		
		
		if(reciverMsgEmail != null) {
			try {	
				user.messageList=messagedb.selectUserMsg(user.getEmail(),reciverMsgEmail);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
		
		for(Message m:user.messageList) {
			if(((reciverMsgEmail.trim()).equals(m.getFromUser()))) {
				System.out.println(m.getMessage());
			}
			else {
				System.out.println("123");
			}
		}
		
		//Request Dispatcher
		System.out.println("----------");
		System.out.println(reciverMsgEmail);
		session.setAttribute("reciverEmail", reciverMsgEmail);
		//request.setAttribute("reciverEmail",reciverMsgEmail);
		request.setAttribute("reciverFname",reciverFname );
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
