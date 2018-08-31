<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Chi tiết bài viết</title>
</head>
<body>
<div class="main">
		<%-- <form:form action="${formUrl}/${model.categoryId}/${newstags:seoURL(model.categoryCode)}" commandName="model" id="formUrl" method="get"> --%>
			<div class="content">
				<div class="image group">
							<div class="grid images_3_of_1">
								<img src='<c:url value="/repository/${model.thumbnail}"/>' alt="" />
							</div>
							<div class="grid news_desc">
								<h3>${model.title}</h3>
								<h4>
									${model.createdDate}
								</h4>
								<p>${model.description}</p>			
							</div>
				</div>		
			</div>
		<%-- </form:form> --%>
</div>
</body>
</html>