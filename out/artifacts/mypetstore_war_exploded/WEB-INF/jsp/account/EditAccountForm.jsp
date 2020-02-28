<%@ include file="../common/IncludeTop.jsp"%>

<div id="Catalog">
	<form action="#" method="post">

	<h3>User Information</h3>

	<table>
		<tr>
			<td>User ID:</td>
			<td>${sessionScope.account.username}</td>
		</tr>
		<tr>
			<td>New password:</td>
			<td><input id="stripes--517137302" name="password"
					   type="text"><script type="text/javascript">
				setTimeout(
						function() {
							try {
								var z = document
										.getElementById('stripes--517137302');
								z.focus();
								z.select();
							} catch (e) {
							}
						}, 1);
			</script></td>
		</tr>
		<tr>
			<td>Repeat password:</td>
			<td><input name="repeatedPassword" type="text"></td>
		</tr>
	</table>
	<%@ include file="IncludeAccountFields.jsp"%>


		<input name="editAccount" value="Save Account Information"
			   type="submit">


</form>

	<a href="viewListOrders">My Orders</a>

<%@ include file="../common/IncludeBottom.jsp"%>
