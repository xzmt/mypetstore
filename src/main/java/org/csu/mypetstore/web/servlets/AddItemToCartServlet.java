package org.csu.mypetstore.web.servlets;

import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddItemToCartServlet extends HttpServlet {

    //处理后跳转页面
    private static final String VIEW_CART = "/WEB-INF/jsp/cart/Cart.jsp";

    //定义处理该请求数据
    private String workingItemId;
    private Cart cart;

    //是否需要调用业务逻辑
    private CatalogService catalogService;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        workingItemId = request.getParameter("workingItemId");

        HttpSession session = request.getSession();
        cart = (Cart)session.getAttribute("cart");

        if(cart == null)
        {
            cart = new Cart();
        }
        if(cart.containsItemId(workingItemId))
        {
            cart.incrementQuantityByItemId(workingItemId);

        }
        else {
            catalogService = new CatalogService();
            boolean isInStock = catalogService.isItemInStock(workingItemId);
            Item item = catalogService.getItem(workingItemId);
            cart.addItem(item,isInStock);
        }
        session.setAttribute("cart",cart);
        request.getRequestDispatcher(VIEW_CART).forward(request,response);
    }
}
