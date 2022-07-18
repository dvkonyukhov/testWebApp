package app.servlets;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CookiesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("text/html;charset=GB2312");

        ServletContext context = getServletContext();
        PrintWriter writer = resp.getWriter();
        Integer allVisitsCounts = (Integer) context.getAttribute("allVisitsCounts");
        if (allVisitsCounts == null) {
            allVisitsCounts = 1;
        } else {
            allVisitsCounts++;
        }
        printInfo(writer, allVisitsCounts, "всеми пользователями ");
        context.setAttribute("allVisitsCounts", allVisitsCounts);

        String cookieName = "cookiesVisitsCounts";

        System.out.println(allVisitsCounts);

        Cookie[] cookies = req.getCookies();
        int cookiesVisitsCounts = 1;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookieName.equals(cookie.getName())) {
                    cookiesVisitsCounts = Integer.parseInt(cookie.getValue());
                    //cookie.setValue(String.valueOf(++cookiesVisitsCounts));
                    cookiesVisitsCounts++;
                    setCookie(resp, cookiesVisitsCounts);
                    break;
                }
            }
        }

        if (cookiesVisitsCounts == 1) {
            setCookie(resp, cookiesVisitsCounts);
        }

        printInfo(writer, cookiesVisitsCounts, "вами ");

        writer.close();
    }

    private void setCookie(HttpServletResponse resp, int cookiesVisitsCounts) {
        Cookie userCookie = new Cookie("cookiesVisitsCounts", String.valueOf(cookiesVisitsCounts));
        userCookie.setMaxAge(60 * 60 * 24);
        resp.addCookie(userCookie);
    }

    private void printInfo(PrintWriter writer, Integer counts, String user) {
        writer.println("Текущая страница была посещена " + user + counts + " раз");
        writer.println("<br />");
    }
}
