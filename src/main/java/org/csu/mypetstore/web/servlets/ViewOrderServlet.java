package org.csu.mypetstore.web.servlets;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ViewOrderServlet extends HttpServlet {

    private static final String CONFIRM_ORDER = "/WEB-INF/jsp/order/ConfirmOrder.jsp";
    private static final String LIST_ORDERS = "/WEB-INF/jsp/order/ListOrders.jsp";
    private static final String NEW_ORDER = "/WEB-INF/jsp/order/NewOrderForm.jsp";
    private static final String SHIPPING = "/WEB-INF/jsp/order/ShippingForm.jsp";
    private static final String VIEW_ORDER = "/WEB-INF/jsp/order/ViewOrder.jsp";
    private static final String ERROR = "/WEB-INF/jsp/common/Error.jsp";
    private OrderService orderService;
    private Order order2;
    private Account account;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        orderService = new OrderService();
        Order order = (Order) session.getAttribute("order");
       order2 = orderService.getOrder(order.getOrderId());
        account = (Account) session.getAttribute("account");

        if (account.getUsername().equals(order.getUsername())) {
            request.getRequestDispatcher(VIEW_ORDER).forward(request,response);
        } else {
            order = null;
            session.setAttribute("message","You may only view your own orders.");
            request.getRequestDispatcher(ERROR).forward(request,response);
        }
    }
}
