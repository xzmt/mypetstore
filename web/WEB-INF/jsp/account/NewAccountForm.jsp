<%@ include file="../common/IncludeTop.jsp"%>


<div id="Catalog">

	<form action="newAccountRegister" method="post" name="userInfo" id="userInfo">

		<h3>User Information</h3>

		<table>
			<tr>
				<td>User ID:</td>
				<td>
					<input type="text" name="username" id="username" onblur="usernameIsExist();"/>
					<div id="usernameInfo"></div>
				</td>
			</tr>
			<tr>
				<td>New password:</td>
				<td><input type="password" name="password" /></td>
			</tr>
			<tr>
				<td>Repeat password:</td>
				<td><input type="password" name="repeatedPassword"/></td>
			</tr>
		</table>

		<%@include file="IncludeAccountFields.jsp"%>

		<input type="submit" name="newAccount" value="Save Account Information"/>

	</form>
</div>


</div>

<%@ include file="../common/IncludeBottom.jsp"%>




