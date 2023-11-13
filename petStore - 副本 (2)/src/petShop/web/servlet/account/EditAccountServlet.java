package petShop.web.servlet.account;

import petShop.domain.Account;
import petShop.service.AccountService;
import petShop.service.LogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditAccountServlet extends HttpServlet {
    private static final String MY_ACCOUNT_FORM = "/WEB-INF/jsp/account/editAccount.jsp";

    AccountService accountService;

    public  EditAccountServlet(){
        this.accountService= new AccountService();
    }

    @Override

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(req.getParameter("password")==null||req.getParameter("password").equals("")){
            req.setAttribute("errorMsg","Blank Password Detected");
            req.getRequestDispatcher(MY_ACCOUNT_FORM).forward(req,resp);
        }else if(!req.getParameter("password").equals(req.getParameter("repeatedPassword"))){
            req.setAttribute("errorMsg","The Two Inputs Are Inconsistent");
            req.getRequestDispatcher(MY_ACCOUNT_FORM).forward(req,resp);
        }else {
            Account account = (Account) req.getSession().getAttribute("account");
            account.setPassword(req.getParameter("password"));
            account.setFirstName(req.getParameter("account.firstName"));
            account.setLastName(req.getParameter("account.lastName"));
            account.setEmail(req.getParameter("account.email"));
            account.setPhone(req.getParameter("account.phone"));
            account.setAddress1(req.getParameter("account.address1"));
            account.setAddress2(req.getParameter("account.address2"));
            account.setCity(req.getParameter("account.city"));
            account.setState(req.getParameter("account.state"));
            account.setZip(req.getParameter("account.zip"));
            account.setCountry(req.getParameter("account.country"));

            account.setLanguagePreference(req.getParameter("account.languagePreference"));
            account.setFavouriteCategoryId(req.getParameter("account.favouriteCategoryId"));

            if(req.getParameter("account.listOption")!=null){
                account.setListOption(true);
            }
            if(req.getParameter("account.bannerOption")!=null){
                account.setBannerOption(true);
            }

            accountService.updateAccount(account);
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
}
