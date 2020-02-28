package org.csu.mypetstore.web.servlets;

import org.csu.mypetstore.domain.Category;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SearchProductServlet extends HttpServlet {
    private static  final  String VIEW_SEARCH = "/WEB-INF/jsp/catalog/SeachProducts.jsp";
    private String keyword;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        keyword = request.getParameter("keyword");
        CatalogService service = new CatalogService();
        List<Product> productList = service.searchProductList(keyword);

        HttpSession session = request.getSession();
        session.setAttribute("productList",productList);

        request.getRequestDispatcher(VIEW_SEARCH).forward(request,response);
    }
}
