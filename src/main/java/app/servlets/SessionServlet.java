package app.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class SessionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        PrintWriter writer = resp.getWriter();
        writer.println("Method GET from TestServlet");

        HttpSession httpSession = req.getSession();

        writer.println("Session id: " + httpSession.getId());
        writer.println("Time creation: " + (new Date(httpSession.getCreationTime())));
        writer.close();

    }
}
