package org.csu.mypetstore.web.servlets;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ViewNewOrderFormServlet extends HttpServlet {
    private static final String NEW_ORDER = "/WEB-INF/jsp/order/NewOrderForm.jsp";
    private static final String ERROR= "/WEB-INF/jsp/common/Error.jsp";

    private Account account;
    private Cart cart;
    private OrderService orderService = new OrderService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        account = (Account) session.getAttribute("account");
        cart = (Cart)session.getAttribute("cart");
        if (account == null || account.getUsername() == null) {
            session.setAttribute("message","You must sign on before attempting to check out.  Please sign on and try checking out again.");
            request.getRequestDispatcher(ERROR).forward(request,response);
        } else if (cart.getNumberOfItems() > 0) {
            Order order = (Order)session.getAttribute("order");

            if(order == null)
            {
                order = new Order();
            }
            order.initOrder(account, cart);
            session.setAttribute("order",order);
            try {
                orderService.insertOrder(order);
            } catch (Exception e) {
                e.printStackTrace();
            }
            request.getRequestDispatcher(NEW_ORDER).forward(request,response);
        } else {
            session.setAttribute("message","An order could not be created because a cart could not be found.");
            request.getRequestDispatcher(ERROR).forward(request,response);
        }
    }
}
