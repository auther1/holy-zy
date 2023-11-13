package petShop.web.servlet.cart;

import petShop.domain.Account;
import petShop.domain.Cart;
import petShop.domain.Item;
import petShop.service.LogService;
import petShop.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RemoveCartItemServlet extends HttpServlet {
    private static final String ERROR_FORM = "/WEB-INF/jsp/common/error.jsp";
    private static final String CART_FORM = "/WEB-INF/jsp/cart/cart.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart==null){
            cart = new Cart();
        }

        String workingItemId = req.getParameter("workingItemId");
        Item item = cart.removeItemById(workingItemId);

        if (item == null) {
            req.setAttribute("errorMsg","Attempted to remove null CartItem from Cart.");
            req.getRequestDispatcher(ERROR_FORM).forward(req,resp);
        } else {
            Account account = (Account)session.getAttribute("account");

            if(account != null){
                HttpServletRequest httpRequest= req;
                String strBackUrl = "http://" + req.getServerName() + ":" + req.getServerPort()
                        + httpRequest.getContextPath() + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());

                LogService logService = new LogService();
                String logInfo = logService.logInfo(" ") + strBackUrl + " " + item + " Removed from cart";
                logService.insertLogInfo(account.getUsername(), logInfo);
            }
            req.getRequestDispatcher(CART_FORM).forward(req,resp);
        }
    }
}
