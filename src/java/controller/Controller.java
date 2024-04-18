package controller;

import dao.TodoDAO;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import model.Todo;

/**
 *
 * @author Vinicius
 */
@WebServlet(name = "Controller", urlPatterns = {"/Controller", "/addTODO", "/addTODOdb", "/listTODO", "/editTODO", "/editTODOdb", "/deleteTODO"})
public class Controller extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Controller</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Controller at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        String action = request.getServletPath();

        if (action.equals("/addTODO")) {
            response.sendRedirect("addTODO.jsp");
        }
        if (action.equals("/listTODO")) {

            List<Todo> lista = TodoDAO.readAll();
            request.setAttribute("lista", lista);
            RequestDispatcher dispatcher
                    = request.getRequestDispatcher("listTODO.jsp");
            dispatcher.forward(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        TodoDAO dao = new TodoDAO();
        Todo todo = new Todo();

        if (action.equals("/addTODOdb")) {

            todo.setName(request.getParameter("name"));
            todo.setIsDone(0);
            if (dao.create(todo) == 1) {
                response.sendRedirect("success.jsp");
            } else {
                response.sendRedirect("error.jsp");
            }
        }

        if (action.equals("/editTODO")) {
            todo = dao.getTodoById(Integer.parseInt(request.getParameter("id")));
            if (todo.getId() > 0) {
                request.setAttribute("todo", todo);
                RequestDispatcher dispatcher
                        = request.getRequestDispatcher("editTODO.jsp");
                dispatcher.forward(request, response);

            } else {
                response.sendRedirect("editTODO.jsp");
            }
        }

        if (action.equals("/editTODOdb")) {

            todo.setId(Integer.parseInt(request.getParameter("id")));
            todo.setName(request.getParameter("name"));
            todo.setIsDone(Integer.parseInt(request.getParameter("isDone") == null ? "0" : "1"));
            if (dao.update(todo) == 1) {
                response.sendRedirect("success.jsp");
            } else {
                response.sendRedirect("error.jsp");
            }
        }

        if (action.equals("/deleteTODO")) {
            todo = dao.getTodoById(Integer.parseInt(request.getParameter("id")));
            if (dao.delete(todo) == 1) {
                response.sendRedirect("success.jsp");
            } else {
                response.sendRedirect("error.jsp");
            }

        }
    }

}
