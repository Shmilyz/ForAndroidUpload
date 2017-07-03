<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>文件上传</h3>
<!-- 文件上传：表单所有内容以字节流方式提交 -->
<form method="post" enctype="multipart/form-data" action="<%=request.getContextPath()%>/upload.do">
文件夹 <input type="text" name="file" /> <br/>
	姓名 <input type="text" name="username" /> <br/>
	头像 <input type="file" name="userphoto"/> <br/>
	<input type="submit" value="提交">
	
</form>
</body>
</html>