package auth;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        // Инициализация при старте (если нужно)
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);

        String uri = req.getRequestURI();
        String context = req.getContextPath();

        // Пути, которые разрешены без авторизации
        boolean isLoginPage = uri.equals(context + "/login.jsp");
        boolean isLoginServlet = uri.equals(context + "/login");
        boolean isRegisterPage = uri.equals(context + "/register.jsp");
        boolean isRegisterServlet = uri.equals(context + "/register");
        boolean isStaticResource = uri.startsWith(context + "/style.css")
                                  || uri.startsWith(context + "/images/")
                                  || uri.startsWith(context + "/js/")
                                  || uri.startsWith(context + "/favicon.ico");

        boolean isLoggedIn = session != null && session.getAttribute("username") != null;

        if (isLoggedIn || isLoginPage || isLoginServlet || isRegisterPage || isRegisterServlet || isStaticResource) {
            chain.doFilter(request, response); // Разрешаем доступ
        } else {
            res.sendRedirect(context + "/login.jsp"); // Редирект на логин
        }
    }

    @Override
    public void destroy() {
        // Очистка ресурсов (если нужно)
    }
}