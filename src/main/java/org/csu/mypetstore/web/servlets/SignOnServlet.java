package org.csu.mypetstore.web.servlets;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class SignOnServlet extends HttpServlet {
    private static final String SIGNON = "/WEB-INF/jsp/account/SignonForm.jsp";

    private static final String MAIN = "/WEB-INF/jsp/catalog/Main.jsp";
    private static final String ERROR= "/WEB-INF/jsp/common/Error.jsp";
    private String username;
    private String password;
    private Account account2;
    private AccountService accountService;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        username = request.getParameter("username");
        password = request.getParameter("password");
        accountService = new AccountService();
        HttpSession session = request.getSession();

                String info=null;
                 /*获取输入的值*/
                String value1=request.getParameter("name");

                 /*获取图片的值*/
                 String value2=(String)session.getAttribute("checkcode");

                 /*对比两个值（字母不区分大小写）*/

               // request.getRequestDispatcher("/login.jsp").forward(request, response);
        account2 = accountService.getAccount(username, password);
//        if(!value2.equalsIgnoreCase(value1)){
//            info="验证码输入错误";
//            session.setAttribute("message",info);
//            request.getRequestDispatcher(ERROR).forward(request,response);
//        }
//        else
        if (account2 == null) {
            String value = "Invalid username or password.  Signon failed.";
            session.setAttribute("message",value);
            request.getRequestDispatcher(ERROR).forward(request,response);
        } else
            {
            session.setAttribute("account",account2);
            request.getRequestDispatcher(MAIN).forward(request,response);
       }
    }

}
