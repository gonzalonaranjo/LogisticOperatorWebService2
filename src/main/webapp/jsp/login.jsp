<html>
<head>
	<script src="script/script.js" type="text/javascript"></script>
	<link href="css/style.css" type="text/css" rel="stylesheet"/>
	<title>Login as Logistic Operator</title>
</head>
<body>
	<h3 align="center">Login de usuario</h3>
    <form action="login" method="post" onsubmit="validateMandatoryFields(this)">
        <table>
            <tr>
                <td>UserName <b id="required">*</b>:</td>
                <td><input type="text" name="USERNAME" size="10" value="GONZALO" required/></td>
            </tr>
            <tr>
                <td>Password <b id="required">*</b>:</td>
                <td><input type="password" name="PASSWORD" size="30" value="LALALA" required/></td>
            </tr>
        </table>
        <p />
        <input type="submit" value="Login" />
    </form>
</body>
</html>