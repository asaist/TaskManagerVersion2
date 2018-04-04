package labs.taskmanger.server.mainServer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class PingHandler extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException{
        resp.setStatus(HttpServletResponse.SC_OK);
        try {
            PrintWriter out = resp.getWriter();
            out.println("<h1>"+ 123 + "</h1>");
        } catch (IOException e) {
            System.err.println(e);
        }


    }
}