<c:set value="${ORDER}" var="order" scope="request"/>
<c:set value="${order.orderHistoryList}" var="orderHistoryList" scope="request"/>
<c:set value="${ORDER_PRODUCT_LIST}" var="productOrderList" scope="request"/>
<html>
<head>

<title>Detalle del Pedido</title>
</head>
<body>
	<h3 align="center">Detalle del pedido</h3>
	<div id = "orderDetail">
		<label>Detalle del Pedido</label><br>
		<label>ID: <c:out value="${order.id}"/></label><br>
        <label>USER: <c:out value="${user.userName}"/></label><br>
        <label>STATUS: <c:out value="${order.status.name}"/></label><br>
        <label>CREATED AT: <c:out value="${order.createdAt}"/></label><br>
        <label>MODIFIED AT: <c:out value="${order.modifiedAt}"/></label><br>
	</div>
	<div id = "productDetail">
		<label>Productos del pedido</label><br>
		<c:forEach var="product" items="${productOrderList}">
       		<label>Nombre del producto: <c:out value="${product.product.name}"/></label><br>
			<label>Cantidad: <c:out value="${product.quantity}"/></label><br> 	
		</c:forEach>
	</div>
	<div id ="orderHistoryDetail">
		<label>Histórico de Estados</label><br>
		<c:forEach var="orderHistory" items="${order.orderHistoryList}">
       	 	<label>STATUS: <c:out value="${orderHistory.status.name}"/></label><br>
       	 	<label>DATE: <c:out value="${orderHistory.date}"/></label><br>
		</c:forEach>
	</div>
	
</body>
</html>