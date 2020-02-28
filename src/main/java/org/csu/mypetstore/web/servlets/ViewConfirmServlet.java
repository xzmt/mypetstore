package org.csu.mypetstore.web.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ViewConfirmServlet extends HttpServlet {

    private static final String CONFIRM_ORDER = "/WEB-INF/jsp/order/ConfirmOrder.jsp";
    private static final String LIST_ORDERS = "/WEB-INF/jsp/order/ListOrders.jsp";
    private static final String NEW_ORDER = "/WEB-INF/jsp/order/NewOrderForm.jsp";
    private static final String SHIPPING = "/WEB-INF/jsp/order/ShippingForm.jsp";
    private static final String VIEW_ORDER = "/WEB-INF/jsp/order/ViewOrder.jsp";
    boolean shippingAddressRequired;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        shippingAddressRequired =Boolean.parseBoolean(request.getParameter("shippingAddressRequired"));
        if (shippingAddressRequired) {
            shippingAddressRequired = false;
            session.setAttribute("shippingAddressRequired",shippingAddressRequired);
            request.getRequestDispatcher(SHIPPING).forward(request,response);
        } else
        {
            request.getRequestDispatcher(CONFIRM_ORDER).forward(request,response);
        }
    }
}
