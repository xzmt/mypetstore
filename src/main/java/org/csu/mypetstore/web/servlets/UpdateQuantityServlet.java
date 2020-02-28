package org.csu.mypetstore.web.servlets;

import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.CartItem;
import org.csu.mypetstore.domain.Item;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class UpdateQuantityServlet extends HttpServlet {

    private static final String VIEW_CART = "/WEB-INF/jsp/cart/Cart.jsp";
    private static final String ERROR = "/WEB-INF/jsp/common/Error.jsp";
    private String workingItemId;
    private Cart cart;
    private Map<String, CartItem> itemMap = null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpServletRequest request = context.getRequest();
        HttpSession session = request.getSession();
        cart = (Cart) session.getAttribute("cart");
        Iterator<CartItem> cartItems = cart.getAllCartItems();
        while (cartItems.hasNext()) {
            CartItem cartItem = (CartItem) cartItems.next();
            String itemId = cartItem.getItem().getItemId();
            try {
                int quantity = Integer.parseInt((String) request.getParameter(itemId));
                cart.setQuantityByItemId(itemId, quantity);
                if (quantity < 1) {
                    cartItems.remove();
                }
            } catch (Exception e) {
                //ignore parse exceptions on purpose
            }
        }
        request.getRequestDispatcher(VIEW_CART).forward(request,response);
    }
}