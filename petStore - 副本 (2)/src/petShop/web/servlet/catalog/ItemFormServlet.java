package petShop.web.servlet.catalog;

import petShop.domain.Account;
import petShop.domain.Item;
import petShop.service.CatalogService;
import petShop.service.LogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ItemFormServlet extends HttpServlet {
    private static final String ITEM_FORM = "/WEB-INF/jsp/catalog/item.jsp";

    CatalogService catalogService;

    public ItemFormServlet(){
        this.catalogService = new CatalogService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String itemID = req.getParameter("itemID");
        Item item = catalogService.getItem(itemID);
        HttpSession session = req.getSession();
        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("item",item);
        Account account = (Account)session.getAttribute("account");

        if(account != null){
            HttpServletRequest httpRequest= req;
            String strBackUrl = "http://" + req.getServerName() + ":" + req.getServerPort()
                    + httpRequest.getContextPath() + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());

            LogService logService = new LogService();
            String logInfo = logService.logInfo(" ") + strBackUrl + " View specific product " + item;
            logService.insertLogInfo(account.getUsername(), logInfo);
        }
        req.getRequestDispatcher(ITEM_FORM).forward(req,resp);
    }
}
