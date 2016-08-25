<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    		<h2>对于items表单的数据录入</h2>	
    		
		<form id="itemForm"
		action="http://localhost:8080/devbuy/java/insertItems.action"
		method="post" enctype="multipart/form-data">
			<tr>
				<td>显示名称</td>
				<td >
					<input type="text" name="itemsname"/>
				</td>
			</tr>
			<br /><br />
			<tr>
				<td>描述</td>
				<td >
					<input type="text" name="description" />
				</td>
			</tr>
			<br /><br />
			<tr>
				<td>大类</td>
				<td >
					<input type="text" name="category" />
				</td>
			</tr>
			<br /><br />
			<tr>
				<td>子类型</td>
				<td >
					<input type="text" name="items_lb" />
				</td>
			</tr>						
			<br /><br />
			<tr>
				<td>价格</td>
				<td >
					<input type="text" name="price" />
				</td>
			</tr>			
			<br /><br />
			<tr>
				<td>商品图片</td>
				<td>
					<input type="file" name="itemspic" />
				</td>
			</tr>
			<br /><br />
			<tr>
				<td>库存量</td>
				<td>
					<input type="text" name="inventory" />
				</td>
			</tr>
			<br /><br />
			<tr>
				<td>销量</td>
				<td>
					<input type="text" name="sales_volume" />
				</td>
			</tr>
			<br /><br />
			<tr>
				<td>产地</td>
				<td>
					<input type="text" name="area" />
				</td>
			</tr>			
			<br /><br />
			<tr>
				<td>快递费</td>
				<td>
					<input type="text" name="express_fee" />
				</td>
			</tr>			
			<br /><br />			
			<tr>
				<td>提交</td>
				<td >
					<input type="submit" value="确定"/>
				</td>
			</tr>
		</form>
</body>
</html>