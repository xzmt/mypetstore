package org.csu.mypetstore.web.servlets;

import org.csu.mypetstore.domain.Account;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ViewEditAccountServlet extends HttpServlet {
    private static final String EDIT_ACCOUNT = "/WEB-INF/jsp/account/EditAccountForm.jsp";
    private static final String MAIN = "/WEB-INF/jsp/catalog/Main.jsp";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        if(account == null)
        {
            request.getRequestDispatcher(MAIN).forward(request,response);
        }
        else
        {
            request.getRequestDispatcher(EDIT_ACCOUNT).forward(request,response);
        }

    }
}
