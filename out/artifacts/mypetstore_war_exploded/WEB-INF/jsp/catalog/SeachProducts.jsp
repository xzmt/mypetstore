<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/10/13
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../common/IncludeTop.jsp"%>
    <div id="BackLink">
        <a href="main">Return to Main Menu</a>
    </div>

    <div id="Catalog">

        <table>
            <tr>
                <th>&nbsp;</th>
                <th>Product ID</th>
                <th>Name</th>
            </tr>
            <c:forEach var="product" items="${sessionScope.productList}">
                <tr>
                    <td>
                        <a href="viewProduct?productId=${product.productId}">${product.description}</a>
                    </td>
                    <td>
                        <b><a href="viewProduct?productId=${product.productId}">${product.productId}</a></b>
                    </td>
                    <td>
                        <b><a href="viewProduct?productId=${product.productId}">${product.name}</a></b>
                    </td>

                </tr>
            </c:forEach>

            <tr>
                <td>
                </td>
            </tr>
        </table>

    </div>

</div>

<%@include file="../common/IncludeBottom.jsp"%>





