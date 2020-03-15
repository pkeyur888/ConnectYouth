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
import com.ConnectYouth.db.PostDbUtil;

/**
 * Servlet implementation class oprationsPost
 */
@WebServlet("/oprationsPost")
public class oprationsPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public oprationsPost() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Resource(name="jdbc/connectyouth")
    private DataSource dataSource;
    private PostDbUtil postdb;
    
   
    @Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		try {
			postdb = new PostDbUtil(dataSource);
			
		}catch(Exception ex) {
			throw new ServletException(ex);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
System.out.println(request.getParameter("postId"));
		
HttpSession session=request.getSession();
		//PostDbUtil dbUser=new PostDbUtil(dataSource);
		User user = (User) session.getAttribute("user");
	if(request.getParameter("delete") != null) {
	user.deletePost(postdb,request.getParameter("postId"));
	
		response.sendRedirect("profile");
	}	
	if(request.getParameter("save") != null) {
		System.out.println("This is save");
		
		user.editPost(postdb,request.getParameter("postId"),request.getParameter("postContent"));
		response.sendRedirect("profile");
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
