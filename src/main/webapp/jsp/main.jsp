<html>
<head>
	<script src="script/script.js" type="text/javascript"></script>
	<link href="css/style.css" type="text/css" rel="stylesheet"/>
	<title>Pantalla Principal</title>
</head>
<body>
	<form id="mainForm" action="main" method="post">
	<input type="hidden" name="TASKCODE" id="TASK" value=""/>
		<div id = "navegador">
			<ul>
		 		<li><a onclick="submitForm(document.getElementById('mainForm'), '00000000')">Pendiente</a></li>
		 		<li><a onclick="submitForm(document.getElementById('mainForm'), '00000100')">Procesado</a></li>
		 		<li><a onclick="submitForm(document.getElementById('mainForm'), '00010000')">Enviado</a></li>
		 		<li><a onclick="submitForm(document.getElementById('mainForm'), '01000000')">Entregado</a></li>
		 	</ul>
	 	</div>
	</form>
	
</body>
</html>