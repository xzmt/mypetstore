<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/10/3
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <link rel="StyleSheet" href="css/jpetstore.css" type="text/css" media="screen" />

    <meta name="generator"
          content="HTML Tidy for Linux/x86 (vers 1st November 2002), see www.w3.org" />
    <title>MyPetStore </title>
    <meta content="text/html; charset=windows-1252"
          http-equiv="Content-Type" />
    <meta http-equiv="Cache-Control" content="max-age=0" />
    <meta http-equiv="Cache-Control" content="no-cache" />
    <meta http-equiv="expires" content="0" />
    <meta http-equiv="Expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
         <!--
19     <link rel="stylesheet" type="text/css" href="styles.css">
20     -->
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script>

        $( function() {

            $( "#searchKey" ).autocomplete({
                source: "<c:url value="/ajaxSearch"/>"
            })
        } );


    </script>
    <script>

        function fun(data1) {
            $.ajax({
                url : "ajaxUpdate",
                type: "post",
                data:
                    {
                      count:data1.value,
                    },
                success : function(data) {   //如果请求成功，返回数据。
                    var newContent = '';
                    var newCount = '$' + parseFloat(data1.parentNode.nextSibling.nextSibling.innerText.substr(1)) * parseInt(data1.value) +'0';
                    newContent += newCount;
                    data1.parentNode.nextSibling.nextSibling.nextSibling.nextSibling.innerHTML= newContent;
                }});

            <%--$("input[name=\"${cartItem.item.itemId}\"]").parent().next().next().value = $("input[name=\"${cartItem.item.itemId}\"]").value * ${cartItem.item.listPrice};--%>

        }
    </script>
    <script language="javascript" type="text/javascript">
        function showDiv(open,descrip,event)
        {
            $.ajax({
                url : "ajaxWindow",
                type: "post",
                data:
                    {
                         description:descrip,
                    },
                success : function(data) {   //如果请求成功，返回数据。
                    var e = event || window.event;
                    var showDiv = document.getElementById('showDiv');
                    var x = (e.pageX - 800) + 'px';
                    var y = (e.pageY - 400) + 'px';
                    console.log(x);
                    showDiv.style.left = x;
                    showDiv.style.top = y;
                    showDiv.style.border = '1px solid #F90';
                    showDiv.style.background = '#E3EDCD';
                    showDiv.style.position = 'absolute';
                    showDiv.style.width = '40%';
                    showDiv.style.zIndex = '9999';
                    showDiv.style.display = 'block';
                    showDiv.innerHTML = data;
                }});


            // document.getElementById("area").style.display=open?"":"none";
        }
        function hideDiv()
        {            document.getElementById("showDiv").style.display="none";
        }

        function mouseMove(e)
        {

        var o = document.getElementById("area");
        o.style.left = mousePos(e).x-170;
        o.style.top = mousePos(e).y;
        }

        function mousePos(e)
        {
            var x,y;
            var e = e||window.event;
            return
            {
                // x:e.clientX+document.body.scrollLeft+document.documentElement.scrollLeft,
                //     y:e.clientY+document.body.scrollTop+document.documentElement.scrollTop
            };
        };
    </script>


</head>

<body>

<div id="showDiv"></div>

<div id="Header">

    <div id="Logo">
        <div id="LogoContent">
            <a href="main"><img src="images/logo-topbar.gif" /></a>
        </div>
    </div>

    <div id="Menu">
        <div id="MenuContent">
            <a href="viewCart"><img align="middle" name="img_cart"
                                    src="images/cart.gif" /></a> <img align="middle"
                                                                         src="images/separator.gif" /> <a
                href="signOnForm">Sign In</a> <a href="#">Sign
            Out</a> <img align="middle" src="images/separator.gif" /> <a
                href="viewEditAccount">My Account</a> <img align="middle"
                                             src="images/separator.gif" /> <a href="../help.html">?</a>
        </div>
    </div>

    <div id="Search">
        <div id="SearchContent">
            <form action="searchProduct" method="post">
                <input type="text" id="searchKey" name="keyword" size="14" /> <input type="submit"
                                                                      name="searchProducts" value="Search" />
            </form>
        </div>
    </div>

    <div id="QuickLinks">
        <a href="viewCategory?categoryId=FISH"><img
                src="images/sm_fish.gif" /></a> <img src="images/separator.gif" />
        <a href="viewCategory?categoryId=DOGS"><img
                src="images/sm_dogs.gif" /></a> <img src="images/separator.gif" />
        <a href="viewCategory?categoryId=REPTILES"><img
                src="images/sm_reptiles.gif" /></a> <img src="images/separator.gif" />
        <a href="viewCategory?categoryId=CATS"><img src="images/sm_cats.gif" /></a> <img src="images/separator.gif" />
        <a href="viewCategory?categoryId=BIRDS"><img src="images/sm_birds.gif" /></a>
    </div>

</div>

<div id="Content">
