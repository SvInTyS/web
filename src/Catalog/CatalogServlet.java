package catalog;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;
import model.Item;

public class CatalogServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:sqlite:C:/Users/svint/Desktop/vuzik/apache-tomcat-9.0.105/webapps/web/WEB-INF/users.db";


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ArrayList<Item> items = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM items")) {

            while (rs.next()) {
                    Item item = new Item();
                    item.setId(rs.getInt("id"));
                    item.setName(rs.getString("name"));
                    item.setDescription(rs.getString("description"));
                    item.setPrice(rs.getDouble("price"));
                    item.setImage(rs.getString("image"));
                    items.add(item);
            }

        } catch (SQLException e) {
            throw new ServletException("Ошибка БД при загрузке каталога", e);
        }

        request.setAttribute("items", items);
        request.getRequestDispatcher("catalog.jsp").forward(request, response);
    }
}
