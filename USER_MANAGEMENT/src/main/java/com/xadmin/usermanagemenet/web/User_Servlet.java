package com.xadmin.usermanagemenet.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xadmin.managemenet.dao.User_dao;
import com.xadmin.usermanagement.modal.User;

/**
 * Servlet implementation class User_Servlet
 */
@WebServlet("/")
public class User_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private   User_dao   User_dao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public void init() {
        User_dao=new User_dao();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		try  {
			switch(action) {
			case "/new" :
				showNewForm(request,response);
				break;
			case "/insert":
				insertUser(request,response);
				break;
			case "/delete":
				deleteUser(request,response);
				break;
			case "/edit":
				showEditUser(request,response);
				break;
			case "/update":
				updateUser(request,response);
				break;
				default:
					listUser(request,response);
					break;
			}
		} catch (SQLException ex) {
			throw new ServletException (ex);
		}
	}
	

	private void listUser(HttpServletRequest request, HttpServletResponse response) 
	throws SQLException,IOException ,ServletException{
		List<User>listUser =User_dao.selectAllUsers();
		request.setAttribute("listUser",listUser );
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
		dispatcher.forward(request,response);
	}
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException ,IOException {
    	RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		dispatcher.forward(request,response);
		
		
	}
    private void showEditUser(HttpServletRequest request, HttpServletResponse response) 
    		throws SQLException,IOException ,ServletException{
    	int id = Integer.parseInt(request.getParameter("id"));
    	User existingUser = User_dao.select1User(id);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		dispatcher.forward(request,response);
	
		
	}

	private void insertUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException,IOException{
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String country = request.getParameter("country");
		User newUser = new User(name,email,country);
		User_dao.insertUser(newUser );
		response.sendRedirect("list");
		
		
	}
	private void updateUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException,IOException{
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String country = request.getParameter("country");
		User book = new User(id,name,email,country);
		User_dao.insertUser(book );
		response.sendRedirect("list");
	}

	

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException,IOException{
		int id = Integer.parseInt(request.getParameter("id"));
		
		User_dao.deleteUser(id);
		response.sendRedirect("list");
	}

}

