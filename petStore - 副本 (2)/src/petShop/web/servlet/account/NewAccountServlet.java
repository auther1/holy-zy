package petShop.web.servlet.account;

import petShop.domain.Account;
import petShop.domain.Cart;
import petShop.service.AccountService;
import petShop.service.CartService;
import petShop.service.LogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class NewAccountServlet extends HttpServlet {
    private static final String MAIN_FORM = "/WEB-INF/jsp/catalog/main.jsp";
    private static final String NEW_ACCOUNT_FORM = "/WEB-INF/jsp/account/newAccount.jsp";
    AccountService accountService;


    public NewAccountServlet(){
        accountService = new AccountService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String checkCode = (String) session.getAttribute("checkCode");
        System.out.println(checkCode);

        String userName = req.getParameter("username");
        String password = req.getParameter("password");
        String checkcode= req.getParameter("checkCode");
        if(userName==null||userName.equals("")){
            req.setAttribute("errorMsg","Blank Username Detected");
            req.getRequestDispatcher(NEW_ACCOUNT_FORM).forward(req,resp);
        }else if(password==null||password.equals("")){
            req.setAttribute("errorMsg","Blank Password Detected");
            req.getRequestDispatcher(NEW_ACCOUNT_FORM).forward(req,resp);
        }else if(checkcode.equals(checkCode)){
            Account account = new Account();
            Cart cart=new Cart();
            cart.createNewCart(req.getParameter("username"));
            account.setUsername(req.getParameter("username"));
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

            accountService.insertAccount(account);

            req.getSession().setAttribute("account",account);
            if(account != null){
                HttpServletRequest httpRequest= req;
                String strBackUrl = "http://" + req.getServerName() + ":" + req.getServerPort()
                        + httpRequest.getContextPath() + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());

                LogService logService = new LogService();
                String logInfo = logService.logInfo(" ") + strBackUrl + " Register a new account";
                logService.insertLogInfo(account.getUsername(), logInfo);
            }

            req.getRequestDispatcher(MAIN_FORM).forward(req,resp);
        }
        else{
            req.setAttribute("errorMsg","Wrong CheckCode");
            req.getRequestDispatcher(NEW_ACCOUNT_FORM).forward(req,resp);
        }
    }
}
