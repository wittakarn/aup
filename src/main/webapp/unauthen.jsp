<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Authentication</title>
    </head>
    <body>
        <form id="productForm" 
              class="form-inline"
              method="post" 
              target="_self"
              action="${pageContext.request.contextPath}/PrivilegeServlet">
            <label for="inputPassword">password</label>
            <input type="password" id="inputPassword" name="input_password"/>
            <button type="submit">submit</button>
        </form>
    </body>
</html>
