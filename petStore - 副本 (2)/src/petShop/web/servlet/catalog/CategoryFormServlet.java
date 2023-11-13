package petShop.web.servlet.catalog;

import petShop.domain.Account;
import petShop.domain.Category;
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

public class CategoryFormServlet extends HttpServlet {
    private static final String CATEGORY_FORM = "/WEB-INF/jsp/catalog/category.jsp";
    /*里面要有category名字和category里的product列表*/

    private CatalogService catalogService;

    public CategoryFormServlet(){
        this.catalogService = new CatalogService();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*categoryId获取到main.jsp页面带的参数*/
        String categoryId = req.getParameter("categoryId");
        /*从数据库中获取categoryId对应的category数据*/
        Category category = catalogService.getCategory(categoryId);
        /*System.out.println(categoryId);*/
        /*获取category里product的数据*/
        List<Product> productList = catalogService.getProductListByCategory(categoryId);
        /*System.out.println(productList);*/

        /*会话跟踪*/
        HttpSession session = req.getSession();
        /*数据存储*/
        session.setAttribute("category",category);
        session.setAttribute("productList", productList);
        Account account = (Account)session.getAttribute("account");

        if(account != null){
            HttpServletRequest httpRequest= req;
            String strBackUrl = "http://" + req.getServerName() + ":" + req.getServerPort()
                    + httpRequest.getContextPath() + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());

            LogService logService = new LogService();
            String logInfo = logService.logInfo(" ") + strBackUrl + " Jump to product categories " + category;
            logService.insertLogInfo(account.getUsername(), logInfo);
        }
        req.getRequestDispatcher(CATEGORY_FORM).forward(req,resp);
    }
}
