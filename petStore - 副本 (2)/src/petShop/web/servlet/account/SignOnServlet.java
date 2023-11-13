package petShop.web.servlet.account;

import petShop.domain.Account;
import petShop.domain.Cart;
import petShop.service.AccountService;
import petShop.service.LogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignOnServlet extends HttpServlet {
    private static final String MAIN_FORM = "/WEB-INF/jsp/catalog/main.jsp";
    private static final String SIGN_ON_FORM = "/WEB-INF/jsp/account/signOn.jsp";

    private AccountService accountService;

    public SignOnServlet(){
        this.accountService = new AccountService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String checkCode = (String) session.getAttribute("checkCode");
        String userName = req.getParameter("username");
        String password = req.getParameter("password");
        String checkcode= req.getParameter("checkCode");
        accountService.setUsername(userName);
        Account account = accountService.getAccount(userName,password);
        if(account != null){
            HttpServletRequest httpRequest= req;
            String strBackUrl = "http://" + req.getServerName() + ":" + req.getServerPort()
                    + httpRequest.getContextPath() + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());

            LogService logService = new LogService();
            String logInfo = logService.logInfo(" ") + strBackUrl + " User login";
            logService.insertLogInfo(account.getUsername(), logInfo);
        }
        if(userName==null||userName.equals("")){
            req.setAttribute("errorMsg","请输入用户名");
            req.getRequestDispatcher(SIGN_ON_FORM).forward(req,resp);
        }else if(password==null||password.equals("")){
            req.setAttribute("errorMsg","请输入密码");
            req.getRequestDispatcher(SIGN_ON_FORM).forward(req,resp);
        }else if(account == null){
            req.setAttribute("errorMsg","用户名或密码错误");
            req.getRequestDispatcher(SIGN_ON_FORM).forward(req,resp);
        }else if(!checkcode.equals(checkCode)){
            req.setAttribute("errorMsg","验证码错误");
            req.getRequestDispatcher(SIGN_ON_FORM).forward(req,resp);
        }else {
            Cart cart= (Cart) session.getAttribute("cart");
            if(cart==null){
                cart=new Cart();
            }
            session.setAttribute("username",userName);
            session.setAttribute("account",account);
            session.setAttribute("cart",cart);
            req.getRequestDispatcher(MAIN_FORM).forward(req,resp);
        }
    }
}
