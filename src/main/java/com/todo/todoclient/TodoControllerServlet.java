package com.todo.todoclient;

import com.todo.todoclient.data.Todo;
import com.todo.todoclient.utils.TodoDbUtil;
import org.postgresql.jdbc.PreferQueryMode;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "TodoControllerServlet", value = "/TodoControllerServlet")
public class TodoControllerServlet extends HttpServlet {
    private TodoDbUtil todoDbUtil;
    private DataSource dataSource;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TodoControllerServlet() {
        super();
    }
    private DataSource getDataSource() throws NamingException {
        String jndi="java:comp/env/jdbc/tododb" ;
        Context context = new InitialContext();
        return (DataSource) context.lookup(jndi);
    }

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            dataSource= getDataSource();
            todoDbUtil = new TodoDbUtil(dataSource);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            listTodos(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void listTodos(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        List<Todo> todos = todoDbUtil.getTodosFiltered(username);
        request.setAttribute("TODO_LIST", todos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/list-todos.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
