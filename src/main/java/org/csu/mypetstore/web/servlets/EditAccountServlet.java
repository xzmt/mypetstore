package org.csu.mypetstore.web.servlets;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class EditAccountServlet extends HttpServlet {
    private static final String MAIN = "/WEB-INF/jsp/catalog/Main.jsp";
    private static final String ERROR= "/WEB-INF/jsp/common/Error.jsp";

    private String username;
    private String password;
    private String repeatPassword;
    private Account account;

    //是否需要调用业务逻辑
    private AccountService accountService;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        username = request.getParameter("username");
        password = request.getParameter("password");
        repeatPassword = request.getParameter("repeatedPassword");

        HttpSession session = request.getSession();
        account = (Account) session.getAttribute("account");

        if(password.equals(repeatPassword))
        {
            account.setUsername(request.getParameter("username"));
            account.setPassword(request.getParameter("password"));
            account.setFirstName(request.getParameter("account.firstName"));
            account.setLastName(request.getParameter("account.lastName"));
            account.setStatus(request.getParameter("account.status"));
            account.setAddress1(request.getParameter("account.address1"));
            account.setAddress2(request.getParameter("account.address2"));
            account.setCity(request.getParameter("account.city"));
            account.setState(request.getParameter("account.state"));
            account.setZip(request.getParameter("account.zip"));
            account.setCountry(request.getParameter("account.country"));
            account.setPhone(request.getParameter("account.phone"));
            account.setEmail(request.getParameter("account.email"));

            account.setLanguagePreference(request.getParameter("account.languagePreference"));
            account.setFavouriteCategoryId(request.getParameter("account.favouriteCategoryId"));
            account.setListOption(Boolean.parseBoolean(request.getParameter("account.listOption")));
            account.setBannerOption(Boolean.parseBoolean(request.getParameter("account.bannerOption")));
            accountService = new AccountService();
            accountService.updateAccount(account);
            account = accountService.getAccount(account.getUsername());
        }
        else
        {
            session.setAttribute("message","you need to reinput the password");
            request.getRequestDispatcher(ERROR).forward(request,response);
        }
        session.setAttribute("account",account);
        request.getRequestDispatcher(MAIN).forward(request,response);
    }
}
