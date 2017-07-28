<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title>Insert title here</title>

<script type="text/javascript">




</script>
</head>
<body>

		<table border="1" style="width: 100px; height: 100px;">
			<thead>
				<tr>
					<th>id</th>
					<th>name</th>
					<th colspan="2" >操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.list}" var="user">
					<tr>
						<td>${user.id }</td>
						<td>${user.name}</td>
						<td><a href="${pageContext.request.contextPath }/test/delete?id=${user.id }"  >删除</a></td>
						<td><a href="${pageContext.request.contextPath }/test/editData?id=${user.id }&name=${user.name}">修改</a></td>
					</tr>
				</c:forEach>			
			</tbody>
			<tfoot>
				<tr ><td colspan="4"><center><a href="${pageContext.request.contextPath }/test/addjsp">添加</a></center></td></tr>
			</tfoot>
		</table>
</body>
</html>