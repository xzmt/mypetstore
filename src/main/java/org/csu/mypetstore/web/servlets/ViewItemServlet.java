package org.csu.mypetstore.web.servlets;

import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.*;
import org.csu.mypetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ViewItemServlet extends HttpServlet {
    private String itemId;
    private static  final  String VIEW_ITEM= "/WEB-INF/jsp/catalog/Item.jsp";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        itemId = request.getParameter("itemId");
        CatalogService service = new CatalogService();
        Item item  = service.getItem(itemId);

        HttpSession session  =request.getSession();
        session.setAttribute("item",item);
        //System.out.println(product.getCategoryId());
        request.getRequestDispatcher(VIEW_ITEM).forward(request, response);
    }
}
