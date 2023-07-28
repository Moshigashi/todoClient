package com.todo.todoclient;


import com.todo.todoclient.data.User;
import com.todo.todoclient.utils.TodoDbUtil;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.sql.DataSource;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {

  TodoDbUtil todoDbUtil;
  DataSource dataSource;
  private DataSource getDataSource() throws NamingException, NamingException {
    String jndi="java:comp/env/jdbc/tododb" ;
    Context context = new InitialContext();
    DataSource dataSource = (DataSource) context.lookup(jndi);
    return dataSource;
  }

  @Override
  public void init() throws ServletException {
    super.init();
    try {
      dataSource= getDataSource();
      todoDbUtil= new TodoDbUtil(dataSource);
    } catch (NamingException e) {
      e.printStackTrace();
    }

  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Cookie [] cookies = request.getCookies();
    if(cookies!= null){
      for(Cookie cookie:cookies){
        if(cookie.getName().equals("username"))
          request.setAttribute("username", cookie.getValue());
      }
    }
    request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    User userToCheck = new User(request.getParameter("username"), request.getParameter("password"));
    Cookie cookie = new Cookie("username", userToCheck.getUserName());
    cookie.setMaxAge(60*60*24);
    response.addCookie(cookie);
    if(todoDbUtil.isValid(userToCheck)) {
      HttpSession session= request.getSession();
      session.setAttribute("username", userToCheck.getUserName());
      response.sendRedirect("TodoControllerServlet");
    } else {
      request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
    }

  }

}
