<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set value = "${ORDER_LIST}" var = "orderList" scope = "request"/>
<c:set value = "${STATUS}" var = "status" scope = "request"/>
<c:set value = "${IS_FINAL_STATUS}" var = "isFinalStatus" scope = "request"/>
<html>
<head>
	<script src="script/script.js" type="text/javascript"></script>
	<link href="css/style.css" type="text/css" rel="stylesheet"/>
	<title>Listado de Solicitudes</title>
</head>
<body>
	
	<c:if test="${orderList.size() gt 0}">
		<h3 align="center">Listado de Solicitudes en estado: <c:out value="${status.name}"/></h3>
		<form id="mainForm" action="main" method="post">
		<input type="hidden" name="TASKCODE" id="TASK" value=""/>
		<input type="hidden" name="STATEID" id="STATEID" value=""/>
		<input type="hidden" name="ORDERID" ID="ORDERID" value=""/>
			<table border="1">
				<thead>
					<tr>
						<th>ID</th>		
						<th>ESTADO</th>
						<th>USUARIO</th>
						<th>FECHA CREACIÓN</th>
						<th>FECHA MODIFICACIÓN</th>
						<th>VER DETALLE</th>
						<c:if test = "${not isFinalStatus}">
							<th>TRAMITAR</th>
						</c:if>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="order" items="${orderList}">
						<tr>
							<td>${order.id}</td>
							<td>${order.status.name}</td>
							<td>${user.name}</td>
							<td>${order.createdAt}</td>
							<td>${order.modifiedAt}</td>
							<td><a class="custom_button" onclick="submitForm(document.getElementById('mainForm'), '00000001', '${order.status.id}', '${order.id}')">Ver Detalle</a></td>
							<c:if test = "${not isFinalStatus}">
								<td><a class="custom_button" onclick="submitForm(document.getElementById('mainForm'), '00000002', '${order.status.id}', '${order.id}')">Tramitar Pedido</a></td>
							</c:if>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>
	</c:if>
	<c:if test = "${orderList.size() eq 0}">
	<h3 align="center">No hay solicitudes en el estado: <c:out value="${status.name}"/> </h3>
	</c:if>
</body>
</html>
 