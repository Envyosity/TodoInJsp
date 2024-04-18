package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;
import model.Todo;

public class TodoDAO {

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("org.sqlite.JDBC");
            //ALTERAR O CAMINHO DO ARQUIVO TEST.DB
            con = DriverManager.getConnection("jdbc:sqlite:C:/Users/leokr/OneDrive/Documentos/NetBeansProjects/projeto/projetoDB.db");

        } catch (Exception e) {
            System.out.println(e);

        }
        return con;
    }

    public static int create(Todo u) {
        int status = 0;
        try {

            Connection con = getConnection();
            Statement ps = con.createStatement();
            status = ps.executeUpdate("insert into TODO(name,isDone) values('" + u.getName() + "'," + 0 + ")");
        } catch (Exception e) {

            System.out.println(e);

        }
        return status;
    }

    public static int update(Todo u) {
        int status = 0;
        try {
            Connection con = getConnection();
            Statement ps = con.createStatement();
            status = ps.executeUpdate("UPDATE TODO SET name='" + u.getName() + "', isDone=" + u.getIsDone() + " WHERE id=" + u.getId());

        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }

    public static int delete(Todo u) {
        int status = 0;
        try {
            Connection con = getConnection();
            Statement ps = con.createStatement();
            status = ps.executeUpdate("delete from TODO where id=" + u.getId());

        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }

    public static List<Todo> readAll() {
        List<Todo> list = new ArrayList<Todo>();

        try {
            Connection con = getConnection();
            Statement ps = con.createStatement();

            ResultSet rs = ps.executeQuery("select * from TODO");
            while (rs.next()) {
                Todo u = new Todo();
                u.setId(rs.getInt("id"));
                u.setName(rs.getString("name"));
                u.setIsDone(rs.getInt("isDone"));
                list.add(u);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public static Todo getTodoById(int id) {
        Todo u = null;
        try {
            Connection con = getConnection();
            Statement ps = con.createStatement();

            ResultSet rs = ps.executeQuery("select * from TODO where id =" + id);

            while (rs.next()) {
                u = new Todo();
                u.setId(rs.getInt("id"));
                u.setName(rs.getString("name"));
                u.setIsDone(rs.getInt("isDone"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return u;
    }
}
