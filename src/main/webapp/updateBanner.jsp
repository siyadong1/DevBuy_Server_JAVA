<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    		<h2>对于banner表单的数据更新</h2>	
    		
		<form id="itemForm"
		action="http://localhost:8080/devbuy/java/updateBannerInfo.action"
		method="post" enctype="multipart/form-data">
			<tr>
				<td>banner图片id</td>
				<td >
					<input type="text" name="bn_id"/>
				</td>
			</tr>
			<br /><br />			
			<tr>
				<td>显示名称</td>
				<td >
					<input type="text" name="bn_name"/>
				</td>
			</tr>
			<br /><br />
			<tr>
				<td>关联商品的id</td>
				<td >
					<input type="text" name="items_id"/>
				</td>
			</tr>
			<br /><br />
			<tr>
				<td>banner图片</td>
				<td>
					<input type="file" name="bannerpic" />
				</td>
			</tr>
			<br /><br />			
			<tr>
				<td>更新</td>
				<td >
					<input type="submit" value="确定"/>
				</td>
			</tr>
		</form>
</body>
</html>