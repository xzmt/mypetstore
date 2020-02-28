package org.csu.mypetstore.web.servlets;

import com.google.gson.Gson;
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

public class AjaxSearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        CatalogService service = new CatalogService();
        response.setContentType("text/html;charset=utf-8");//解决乱码
        String username = request.getParameter("term");//默认传过来的参数名成为term，封装了文本框中输入的值
        System.out.println(username);
        StringBuffer sb = new StringBuffer("[");
        try {
            List<String> names = service.searchAllProductList(username);
            for(int i=0;i<names.size();i++){
                if(i==names.size()-1){
                    //注意在拼接的时候，要用双引号，单引号我试过，不起作用好像是,至少key必须是双引号，最好都写成双引号
                    /*
                     * 其中label属性用于显示在autocomplete弹出菜单，而value属性则是选中后给文本框赋的值。如果没有指定其中一个属性
                     * 则用另一个属性替代(即value和label值一样)，如果label和value都没有指定，则无法用于autocomplete的提示。
                     */
                    sb.append("{\"lable\":\""+names.get(i)+"\",\"value\":\""+names.get(i)+"\"}]");
                }else{
                    sb.append("{\"lable\":\""+names.get(i)+"\",\"value\":\""+names.get(i)+"\"},");
                }
            }

            System.out.println(names.size());
            response.getWriter().write(sb.toString());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
}
