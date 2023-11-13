package petShop.web.servlet.catalog;

import petShop.domain.Account;
import petShop.domain.Product;
import petShop.service.CatalogService;
import petShop.service.LogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SearchFormServlet extends HttpServlet {
    private static final String  SEARCH_FORM = "/WEB-INF/jsp/catalog/search.jsp";

    CatalogService catalogService;

    public  SearchFormServlet(){
        this.catalogService = new CatalogService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String keyword = req.getParameter("keyword");
        List<Product> productList  = catalogService.searchProductList(keyword);
        req.getSession().setAttribute("productList",productList);
        Account account = (Account)session.getAttribute("account");

        if(account != null){
            HttpServletRequest httpRequest= req;
            String strBackUrl = "http://" + req.getServerName() + ":" + req.getServerPort()
                    + httpRequest.getContextPath() + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());

            LogService logService = new LogService();
            String logInfo = logService.logInfo(" ") + strBackUrl + " Find a product" + "  " + productList;
            logService.insertLogInfo(account.getUsername(), logInfo);
        }
        req.getRequestDispatcher(SEARCH_FORM).forward(req,resp);
    }
}
