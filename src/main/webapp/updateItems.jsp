<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    		<h2>对于items表单的数据更新</h2>	
    		
		<form id="itemForm"
		action="http://www.dev4free.com/devbuy/java/updateItemsByItemsId.action"
		method="post" enctype="multipart/form-data">
			<tr>
				<td>商品的id</td>
				<td >
					<input type="text" name="items_id"/>
				</td>
			</tr>
			<br /><br />
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
				<td>原价</td>
				<td >
					<input type="text" name="price" />
				</td>
			</tr>			
			<br /><br />
			<tr>
				<td>现价</td>
				<td >
					<input type="text" name="current_price" />
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
				<td>更新</td>
				<td >
					<input type="submit" value="更新"/>
				</td>
			</tr>
		</form>
</body>
</html>