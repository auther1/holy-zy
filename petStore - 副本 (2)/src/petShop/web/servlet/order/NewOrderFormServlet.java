package petShop.web.servlet.order;

import petShop.domain.Account;
import petShop.domain.Cart;
import petShop.domain.Order;
import petShop.service.CartService;
import petShop.service.LogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class NewOrderFormServlet extends HttpServlet {
    private static final String NEW_ORDER_FORM = "/WEB-INF/jsp/order/newOrder.jsp";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        Account account = (Account) session.getAttribute("account");

        Order order = new Order();
        order.initOrder(account,cart);
        session.setAttribute("order",order);
        if(account != null){
            HttpServletRequest httpRequest= req;
            String strBackUrl = "http://" + req.getServerName() + ":" + req.getServerPort()
                    + httpRequest.getContextPath() + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());

            LogService logService = new LogService();
            String logInfo = logService.logInfo(" ") + strBackUrl + " Jump to the New Order page";
            logService.insertLogInfo(account.getUsername(), logInfo);
        }

        req.getRequestDispatcher(NEW_ORDER_FORM).forward(req,resp);
    }
}
