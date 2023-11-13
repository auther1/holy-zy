package petShop.web.servlet.order;

import petShop.domain.Account;
import petShop.domain.Order;
import petShop.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListOrdersFormServlet extends HttpServlet {
    private static final String VIEW_ORDER = "/WEB-INF/jsp/order/listOrders.jsp";

    OrderService orderService;

    public ListOrdersFormServlet(){
        this.orderService = new OrderService();
    }
  private   List<Order> orderList = new ArrayList<>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        String username = account.getUsername();
        List<Order> orderList = new ArrayList<>();

        orderList = orderService.getOrdersByUsername(username);
        session.setAttribute("orderList",orderList);

        req.getRequestDispatcher(VIEW_ORDER).forward(req,resp);
    }
}
