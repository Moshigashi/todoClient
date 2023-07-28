package com.todo.todoclient;

import com.todo.todoclient.data.Todo;
import com.todo.todoclient.utils.TodoDbUtil;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.sql.DataSource;
import java.io.IOException;

@WebServlet(name = "AchieveTodoServlet", value = "/AchieveTodoServlet")
public class AchieveTodoServlet extends HttpServlet {
    private TodoDbUtil todoDbUtil;
    private DataSource dataSource;

    private DataSource getDataSource() throws NamingException {
        String jndi="java:comp/env/jdbc/tododb" ;
        Context context = new InitialContext();
        return (DataSource) context.lookup(jndi);
    }
    @Override
    public void init() throws ServletException {
        super.init();
        try {
            dataSource = getDataSource();
            todoDbUtil = new TodoDbUtil(dataSource);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public AchieveTodoServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        todoDbUtil.achieveTodo(Integer.parseInt(request.getParameter("todoId")));
        response.sendRedirect("TodoControllerServlet");}
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
    }

}
