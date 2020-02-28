package org.csu.mypetstore.web.servlets;

import org.apache.catalina.filters.ExpiresFilter;
import org.csu.mypetstore.domain.Category;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class AjaxWindowServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryId = request.getParameter("description");
        CatalogService service = new CatalogService();
        Category category = service.getCategory(categoryId);
        List<Product> productList = service.getProductListByCategory(categoryId);

        HttpSession session = request.getSession();
        response.setContentType("text/html;charset=utf-8");//解决乱码
        PrintWriter sb =response.getWriter();
        try {
            for(int i=0;i<productList.size();i++){
                if(i==productList.size()-1){

                   sb.println("<b>productId: " + productList.get(i).getProductId() +" description: " + productList.get(i).getDescription() + " </b>" + "<br>");
                }else{
                    sb.println("<b>productId: " + productList.get(i).getProductId() +" description: " + productList.get(i).getDescription() + " </b>" + "<br>");
                }
            }

            sb.flush();
            sb.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
