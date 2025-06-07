package auth;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);
        String loginURI = req.getContextPath() + "/login.jsp";
        String registerURI = req.getContextPath() + "/register.jsp";
        String loginServlet = req.getContextPath() + "/login";
        String registerServlet = req.getContextPath() + "/register";

        boolean loggedIn = session != null && session.getAttribute("username") != null;
        boolean loginRequest = req.getRequestURI().equals(loginURI) || req.getRequestURI().equals(loginServlet);
        boolean registerRequest = req.getRequestURI().equals(registerURI) || req.getRequestURI().equals(registerServlet);
        boolean staticResource = req.getRequestURI().contains("/css") || req.getRequestURI().contains("/js") || req.getRequestURI().contains("/images");

        if (loggedIn || loginRequest || registerRequest || staticResource) {
            chain.doFilter(request, response);
        } else {
            res.sendRedirect(req.getContextPath() + "/login.jsp");
        }
    }

    @Override
    public void destroy() {}
}