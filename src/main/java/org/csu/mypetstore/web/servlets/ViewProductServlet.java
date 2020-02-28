package org.csu.mypetstore.web.servlets;

import org.csu.mypetstore.domain.*;
import org.csu.mypetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ViewProductServlet extends HttpServlet {
    private String productId;
    private static  final  String VIEW_PRODUCT= "/WEB-INF/jsp/catalog/Product.jsp";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        productId = request.getParameter("productId");
        CatalogService service = new CatalogService();
        Product product  = service.getProduct(productId);
        List<Item> itemList = service.getItemListByProduct(productId);

        HttpSession session  =request.getSession();
        session.setAttribute("product",product);
        session.setAttribute("itemList",itemList);
        //System.out.println(product.getCategoryId());
        request.getRequestDispatcher(VIEW_PRODUCT).forward(request, response);



    }
}
