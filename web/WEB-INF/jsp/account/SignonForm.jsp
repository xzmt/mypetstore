<%@ include file="../common/IncludeTop.jsp"%>

<div id="Catalog">
	<form action="signOn" method="post">
		<p>Please enter your username and password.</p>
		</p>
		<p>
			Username:<input type="text" name="username" /><br />
			Password:<input type="password" name="password" /><br />
			checkword:  <input type="text" name="name" />
			    <a href="SignonForm.jsp"><img border="0" src="logCheck"></a> <br />
			<!--  Validation Code:<input type="text" name="validationCode"/>
    <img src="validation" id="imgservlet" onclick="reloadImage()"/>
    -->
			<!--  <input type="button" value="changeImage" onclick="reloadImage()" id="btn">-->
		</p>
		<input type="submit" name="signon" value="Login" />
	</form>

	Need a user name and password? <a href="newAccount">Register
	Now!</a>

</div>

<%@ include file="../common/IncludeBottom.jsp"%>


