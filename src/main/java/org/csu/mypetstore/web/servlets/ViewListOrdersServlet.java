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
import java.util.List;

public class ViewListOrdersServlet extends HttpServlet {

    private static final String CONFIRM_ORDER = "/WEB-INF/jsp/order/ConfirmOrder.jsp";
    private static final String LIST_ORDERS = "/WEB-INF/jsp/order/ListOrders.jsp";
    private static final String NEW_ORDER = "/WEB-INF/jsp/order/NewOrderForm.jsp";
    private static final String SHIPPING = "/WEB-INF/jsp/order/ShippingForm.jsp";
    private static final String VIEW_ORDER = "/WEB-INF/jsp/order/ViewOrder.jsp";
    private List<Order> orderList;
    private OrderService orderService = new OrderService();
    private Account account;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        account = (Account) session.getAttribute("account");
        orderList = orderService.getOrdersByUsername(account.getUsername());
        session.setAttribute("orderList",orderList);
        request.getRequestDispatcher(LIST_ORDERS).forward(request,response);
    }
}
