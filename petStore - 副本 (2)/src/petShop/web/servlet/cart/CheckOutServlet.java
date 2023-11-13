package petShop.web.servlet.cart;

import petShop.domain.Account;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CheckOutServlet extends HttpServlet {
    private static final String SIGN_ON_FORM = "/WEB-INF/jsp/account/signOn.jsp";
    private static final String NEW_ORDER = "check";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = (Account) req.getSession().getAttribute("account");
        if(account==null){
            req.setAttribute("errorMsg","You Have To Sign On Before You Check");
            req.getRequestDispatcher(SIGN_ON_FORM).forward(req,resp);
        }else {
            req.getRequestDispatcher(NEW_ORDER).forward(req,resp);
        }
    }
}
