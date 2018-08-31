<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="formUrl" value="/tin-tuc"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Danh sách bài viết theo chủ đề</title>
</head>
<body>
<div class="main">
		<form:form action="${formUrl}/${model.categoryId}/${newstags:seoURL(model.categoryCode)}" commandName="model" id="formUrl" method="get">
			<div class="content">
                    <div class="col span_2_of_3" >
                        <div class="input-group">
                            <form:input path="title" class="form-control no-border" value="Search" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Search';}"/>
                            <div class="input-group-btn">
                                <button class="btn btn-default" type="submit">
                                <i class="glyphicon glyphicon-search"></i>
                                </button>
                            </div>
                        </div>
                    <!-- <div class="form-group">
                        <span>
                            <form:input path="title" value="Search" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Search';}"/>
                            <input type="submit" value="search">
                        </span>
                    </div> -->
                </div>
				<c:forEach var="items" items="${model.listResult}">
						<div class="image group">
							<div class="grid images_3_of_1">
								<img src='<c:url value="/repository/${items.thumbnail}"/>' alt="" />
							</div>
							<div class="grid news_desc">
								<h3><a href="<c:url value="/tin-tuc/${items.categoryId}/${newstags:seoURL(items.categoryCode)}/${items.id}/${newstags:seoURL(items.title)}"/>">${items.title}</a></h3>
								<h4>
									${items.createdDate}
								</h4>
								<p>${fn:substring(items.description,0,200)}</p>			
							</div>
						</div>		
				</c:forEach>
				<ul id="pagination-demo" class="pagination-sm"></ul>
				<form:hidden path="page" id="page"/>
			</div>
		</form:form>
</div>
<script type="text/javascript">
    var totalPages = ${model.totalPages};
    var startPage = ${model.page};
    var visiblePages = ${model.maxPageItems};
    $(document).ready(function () {
    	$('#btnSearch').click(function () {
            $('#formUrl').submit();
        });
    });
    $(function () {
        var obj = $('#pagination-demo').twbsPagination({
            totalPages: totalPages,
            startPage: startPage,
            visiblePages: visiblePages,
            onPageClick: function (event, page) {
                if (page != startPage) {
                    $('#page').val(page);
                    $('#formUrl').submit();
                }
            }
        });
    });
</script>		
</body>
</html>