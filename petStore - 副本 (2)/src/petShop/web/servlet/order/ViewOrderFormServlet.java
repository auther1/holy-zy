package petShop.web.servlet.order;

import petShop.domain.Account;
import petShop.domain.Cart;
import petShop.domain.Order;
import petShop.service.LogService;
import petShop.service.OrderService;
import petShop.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ViewOrderFormServlet extends HttpServlet {
    private static final String VIEW_ORDER = "/WEB-INF/jsp/order/viewOrder.jsp";

    private OrderService orderService;

    public ViewOrderFormServlet(){
        this.orderService = new OrderService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Order order;

        String orderId = req.getParameter("orderId");
        if(orderId==null||orderId.equals("")){//如果是从确认来的，则从session中取出order，入数据库
            order = (Order) session.getAttribute("order");
            orderService.insertOrder(order);
            Cart cart = new Cart();
            session.setAttribute("cart", cart);
            Account account = (Account)session.getAttribute("account");
            if(account != null){
                HttpServletRequest httpRequest= req;
                String strBackUrl = "http://" + req.getServerName() + ":" + req.getServerPort()
                        + httpRequest.getContextPath() + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());

                LogService logService = new LogService();
                String logInfo = logService.logInfo(" ") + strBackUrl + " View the order " + order;
                logService.insertLogInfo(account.getUsername(), logInfo);
            }
            req.getRequestDispatcher(VIEW_ORDER).forward(req,resp);
        }else {//如果是从Orders来的，则从数据库中取出order，入session
            order = orderService.getOrder(Integer.parseInt(orderId));
            session.setAttribute("order",order);
            Account account = (Account)session.getAttribute("account");
            if(account != null){
                HttpServletRequest httpRequest= req;
                String strBackUrl = "http://" + req.getServerName() + ":" + req.getServerPort()
                        + httpRequest.getContextPath() + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());

                LogService logService = new LogService();
                String logInfo = logService.logInfo(" ") + strBackUrl + " View the order " + order;
                logService.insertLogInfo(account.getUsername(), logInfo);
            }
            req.getRequestDispatcher(VIEW_ORDER).forward(req,resp);
        }
    }
}
