package auth;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false); // true не нужно, если нет — не создаём
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("login.jsp");
    }
}