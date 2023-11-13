package petShop.web.servlet.cart;

import petShop.domain.Account;
import petShop.domain.CartItem;
import petShop.domain.Cart;
import petShop.service.AccountService;
import petShop.service.LogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;

public class UpdateCartServlet extends HttpServlet {
    private static final String CART_FORM = "/WEB-INF/jsp/cart/cart.jsp";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if(cart==null){
            cart = new Cart();
        }
        //遍历购物车里的所有物品，修改数量，如果<1则移除
        Iterator<CartItem> cartItemIterator = cart.getCartItems();
        while (cartItemIterator.hasNext()) {
            CartItem cartItem = (CartItem) cartItemIterator.next();
            String itemId = cartItem.getItem().getItemId();
            try {
                String quantityStr = req.getParameter(itemId);
                int quantity = Integer.parseInt(quantityStr);
                cart.setQuantityByItemId(itemId, quantity);
                if (quantity < 1) {
                    cartItemIterator.remove();
                    cart.removeItemById(itemId);
                }
            } catch (Exception e) {
                //ignore parse exceptions on purpose
            }
        }
        Account account = (Account)session.getAttribute("account");

        if(account != null){
            HttpServletRequest httpRequest= req;
            String strBackUrl = "http://" + req.getServerName() + ":" + req.getServerPort()
                    + httpRequest.getContextPath() + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());

            LogService logService = new LogService();
            String logInfo = logService.logInfo(" ") + strBackUrl + " Update the number of items in the cart";
            logService.insertLogInfo(account.getUsername(), logInfo);
        }
        req.getRequestDispatcher(CART_FORM).forward(req,resp);
    }
}
