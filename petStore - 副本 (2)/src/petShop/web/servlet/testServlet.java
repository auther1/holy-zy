package petShop.web.servlet;

import petShop.domain.Product;
import petShop.domain.Sequence;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class testServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Sequence sequence = new Sequence();
        System.out.println(sequence.getName());

        Product product = new Product();
        System.out.println(product.getName());
        req.getRequestDispatcher("/WEB-INF/jsp/catalog/main.jsp").forward(req,resp);
    }
}
