package com.todo.todoclient.utils;

import com.todo.todoclient.data.Todo;
import com.todo.todoclient.data.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TodoDbUtil {
    private DataSource dataSource;

    public TodoDbUtil(DataSource DataSource) {
        dataSource = DataSource;
    }

    public List<Todo> getTodos() throws Exception {
        List<Todo> todos= new ArrayList<Todo>();
        Connection myConn=null;
        Statement myStmt = null;
        ResultSet myRs= null;
        try {
            myConn = dataSource.getConnection();
            myStmt= myConn.createStatement();
            String sql= "select * from todo where status = True order by id";
            myRs = myStmt.executeQuery(sql);
            while(myRs.next()){
                int id = myRs.getInt("id");
                String description=myRs.getString("description");
                int assignment=myRs.getInt("assignment");
                boolean status = myRs.getBoolean("status");
                Todo tempTodo= new Todo(id, description, status, assignment);
                todos.add(tempTodo);
            }
            return todos;
        } finally {
            close(myConn,myStmt,myRs);
        }
    }
    public List<Todo> getTodosFiltered(String username) throws Exception {
        List<Todo> todos= new ArrayList<Todo>();
        Connection myConn=null;
        Statement myStmt = null;
        ResultSet myRs= null;
        try {
            myConn = dataSource.getConnection();
            myStmt= myConn.createStatement();
            String sql= "select todo.id, todo.description, todo.status, todo.assignment" +
                    " from todo join usertable on assignment = usertable.userid " +
                    "where status = True and usertable.username= \'"+username+"\' order by id";
            myRs = myStmt.executeQuery(sql);
            while(myRs.next()){
                int id = myRs.getInt("id");
                String description=myRs.getString("description");
                int assignment=myRs.getInt("assignment");
                boolean status = myRs.getBoolean("status");
                Todo tempTodo= new Todo(id, description, status, assignment);
                todos.add(tempTodo);
            }
            return todos;
        } finally {
            close(myConn,myStmt,myRs);
        }
    }

    public void achieveTodo(int todoId) {
        Connection myConn=null;
        PreparedStatement myStmt = null;
        try {
            myConn = dataSource.getConnection();
            String sql = "update todo set status = false where id=?";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setInt(1,todoId);
            myStmt.execute();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            close(myConn,myStmt,null);
        }
    }
    public boolean isValid(User userToCheck) {
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        boolean isValid = false;
        try {
            myConn = dataSource.getConnection();
            myStmt= myConn.createStatement();
            String sql= "select * from usertable where username = \'"+userToCheck.getUserName()+"\'";
            myRs = myStmt.executeQuery(sql);
            while (myRs.next()) {
                isValid = (userToCheck.getPassword().equals(myRs.getString("password")));
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
        } finally{
            close(myConn,myStmt,myRs);
        }
        return isValid;
    }
    private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
        try{
            if(myStmt!=null)
                myStmt.close();
            if(myRs!=null)
                myRs.close();
            if(myConn!=null)
                myConn.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
