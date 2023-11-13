package petShop.web.servlet.cart;

import petShop.domain.Account;
import petShop.domain.Cart;
import petShop.domain.Item;
import petShop.service.AccountService;
import petShop.service.CatalogService;
import petShop.service.LogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddItemToCartServlet extends HttpServlet {
    private static final String CART_FORM = "cart";
    private CatalogService catalogService;

    public AddItemToCartServlet(){
        this.catalogService = new CatalogService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String workingItemId = (String) req.getParameter("workingItemId");
        Account account;
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        account = (Account)session.getAttribute("account");
        if(cart==null){
            cart = new Cart();
        }

        if (cart.containsItemId(workingItemId)) {
            //购物车里有这一项，那么数量增加
            cart.incrementQuantityByItemId(workingItemId);
            if(account != null){
                HttpServletRequest httpRequest= (HttpServletRequest) req;
                String strBackUrl = "http://" + req.getServerName() + ":" + req.getServerPort()
                        + httpRequest.getContextPath() + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());

                LogService logService = new LogService();
                Item item = catalogService.getItem(workingItemId);
                String logInfo = logService.logInfo(" ") + strBackUrl + " " + item + "count+1 ";
                logService.insertLogInfo(account.getUsername(), logInfo);
            }
        } else {
            // isInStock is a "real-time" property that must be updated
            // every time an item is added to the cart, even if other
            // item details are cached.
            //购物车里没有这一项，先检查是否有库存（有的）然后添加进购物车
            boolean isInStock = catalogService.isItemInStock(workingItemId);
            Item item = catalogService.getItem(workingItemId);
            cart.addItem(item, isInStock);
            session.setAttribute("cart",cart);
            if(account != null){
                HttpServletRequest httpRequest= req;
                String strBackUrl = "http://" + req.getServerName() + ":" + req.getServerPort()
                        + httpRequest.getContextPath() + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());

                LogService logService = new LogService();
                String logInfo = logService.logInfo(" ") + strBackUrl + " Add the product " + item + " to the cart";
                logService.insertLogInfo(account.getUsername(), logInfo);
            }
        }
        resp.sendRedirect(CART_FORM);
    }
}
