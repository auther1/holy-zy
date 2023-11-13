package petShop.web.servlet.account;

import petShop.domain.Account;
import petShop.service.LogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyAccountFormServlet extends HttpServlet {
    private static final String MY_ACCOUNT_FORM = "/WEB-INF/jsp/account/editAccount.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = (Account) req.getSession().getAttribute("account");
        if(account != null){
            HttpServletRequest httpRequest= req;
            String strBackUrl = "http://" + req.getServerName() + ":" + req.getServerPort()
                    + httpRequest.getContextPath() + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());

            LogService logService = new LogService();
            String logInfo = logService.logInfo(" ") + strBackUrl + " Jump to the Edit Account Information page";
            logService.insertLogInfo(account.getUsername(), logInfo);
        }
        req.getRequestDispatcher(MY_ACCOUNT_FORM).forward(req,resp);
    }
}
