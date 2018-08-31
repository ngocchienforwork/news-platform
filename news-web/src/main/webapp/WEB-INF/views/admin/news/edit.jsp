<%@ page contentType="text/html;charset=UTF-8" language="java"%>
	<%@include file="/common/taglib.jsp"%>
		<c:url var="formUrl" value="/ajax/news" />
		<html>
		<head>
			<title>
				<spring:message code="Label.Title.News.Edit" text="Edit Post" />
			</title>
			<style>
				.error {
					color: red;
				}
			</style>
		</head>

		<body>
			<div class="main-content">
				<div class="main-content-inner">
					<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try {
								ace.settings.check('breadcrumbs', 'fixed')
							} catch (e) {}
						</script>
						<ul class="breadcrumb">
							<li>
								<i class="ace-icon fa fa-home home-icon"></i>
								<a href='<c:url value="/admin/home"/>'>
									<spring:message code="Label.Home" text="Home" />
								</a>
							</li>
							<li class="active">
								<spring:message code="Label.News.Edit" text="Edit Post" />
							</li>
						</ul>
						<!-- /.breadcrumb -->
					</div>
					<div class="page-content">
						<div class="row">
							<div class="col-xs-12">
								<c:if test="${not empty messageResponse}">
									<div class="alert alert-block alert-${alert}">
										<button type="button" class="close" data-dismiss="alert">
											<i class="ace-icon fa fa-times"></i>
										</button>
										${messageResponse}
									</div>
								</c:if>
								<form:form id="formEdit" commandName="model">
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right">
											<spring:message code="Label.Catelogy" text="Catelogy" />
										</label>
										<div class="col-sm-9">
											<form:select path="categoryCode" id="category">
												<form:option value="" label="--- Select ---" />
												<form:options items="${model.categories}" />
											</form:select>
											<!-- <input type="button" class="btn btn-white btn-warning btn-bold" value="Thêm thể loại"/> -->
										</div>
									</div>
									<br />
									<br />
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right">Chọn top </label>
										<div class="col-sm-9">
											<form:select path="top" id="top" class="required">
												<form:option value="1" label="Top 1" />
												<form:option value="2" label="Top 2" />
												<form:option value="3" label="Top 3" />
											</form:select>
										</div>
									</div>
									<br />
									<br />
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right">
											<spring:message code="Label.Title" text="Title" />
										</label>
										<div class="col-sm-9">
											<!-- <input type="text" name="title" id="title" value=""/> -->
											<form:input path="title" id="title" name="title" />
										</div>
									</div>
									<br />
									<br />
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right">
											<spring:message code="Label.Thumbnail.Post" text="Label.Thumbnail.PostThumbnail" />
										</label>
										<div class="col-sm-9">
											<input type="file" name="file" id="uploadImage" />
										</div>
									</div>
									<br />
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right">
											<spring:message code="Label.Review" text="Review" />
										</label>
										<div class="col-sm-9">
											<c:if test="${not empty model.thumbnail}">
												<c:set var="image" value="/repository/${model.thumbnail}" />
												<img src="<c:url value='${image}'/>" id="viewImage" width="150px" height="150px">
											</c:if>
											<c:if test="${empty model.thumbnail}">
												<img src="<c:url value='/image/no-image.png'/>" id="viewImage" width="150px" height="150px">
											</c:if>
										</div>
									</div>
									<br />
									<br />
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right">
											<spring:message code="Label.Description" text="Description" />
										</label>
									</div>
									<div class="form-group">
										<div class="col-sm-12">
											<form:textarea path="description" name="description" cols="80" rows="10" id="description" />
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-12">
											<c:if test="${not empty model.id}">
												<input type="submit" class="btn btn-white btn-warning btn-bold" value='<spring:message code="Label.New.Edit" text="Add new Post"/>'
												    id="btnAddOrUpdateNews" />
											</c:if>
											<c:if test="${empty model.id}">
												<input type="submit" class="btn btn-white btn-warning btn-bold" value='<spring:message code="Label.New.Add" text="Update Post"/>'
												    id="btnAddOrUpdateNews" />
											</c:if>
										</div>
									</div>
									<form:hidden path="id" id="newsId" />
								</form:form>
							</div>
						</div>
					</div>
				</div>
			</div>
			<script type="text/javascript">
				var editor = '';
				$(document).ready(
					function () {		
						editor = CKEDITOR.replace('description');
						CKFinder.setupCKEditor(editor,
							'${pageContext.request.contextPath}/ckfinder/');
						$('#uploadImage').change(function () {
							readURL(this, "viewImage");
						});
						validateData();
						
				});
				function validateData() {
					$("#formEdit").validate({
						ignore: [],
						rules: [],
						messages: [],
						success: function () {},
						submitHandler: function (form) {
							event.preventDefault();
							submitAjaxHander();
						}
					});
					$("#title").rules("add", {
						required: true,
						messages: {
							required: "title required"
						}
					});
					if ($('#newsId').val() == "") {
						$("#uploadImage").rules("add", {
							required: true,
							messages: {
								required: "Image required"
							}
						});
					}
					$("#description").rules("add", {
						required: function(textarea) {
							CKEDITOR.instances[textarea.id].updateElement();
							var editorcontent = textarea.value;
							return editorcontent.length === 0;
						},
						messages: {
							required: "description required"
						}
					});
					$("#category").rules("add", {
						required: true,
						messages: {
							required: "category required"
						}
					});
				}
				function readURL(input, imageId) {
					if (input.files && input.files[0]) {
						var reader = new FileReader();
						reader.onload = function (e) {
							$('#' + imageId).attr('src', reader.result);
						}
						reader.readAsDataURL(input.files[0]);
					}
				}
				function submitAjaxHander() {
					var dataArray = {};
					dataArray["categoryCode"] = $('#category').val();
					dataArray["title"] = $('#title').val();
					dataArray["description"] = editor.getData();
					dataArray["top"] = $('#top').val();
					var files = $('#uploadImage')[0].files[0];
					if (files == undefined) {
						dataArray["imageName"] = "";
						if ($('#newsId').val() != "") {
							updateNews(dataArray, $('#newsId').val());
						}
					} else {
						dataArray["imageName"] = files.name;
						var reader = new FileReader();
						reader.onload = function (e) {
							dataArray["thumbnailBase64"] = e.target.result;
							if ($('#newsId').val() == "") {
								addNews(dataArray);
							} else {
								updateNews(dataArray, $('#newsId').val());
							}
						};
						reader.readAsDataURL(files);
					}
				}
				function addNews(data) {
					$
						.ajax({
							url: '${formUrl}',
							type: 'POST',
							dataType: 'json',
							contentType: 'application/json',
							data: JSON.stringify(data),
							success: function (res) {
								window.location.href = "<c:url value='/admin/news/list'/>";
							},
							error: function(e) {
								console.log("ERROR LOAD API Insert Admin News: ", e);
								window.location.href = "<c:url value='/admin/news/list'/>";								
							},
							done : function(e) {
									console.log("DONE");
							}
						});
				}
				function updateNews(data, id) {
					$
						.ajax({
							url: '${formUrl}/' + id,
							type: 'PUT',
							dataType: 'json',
							contentType: 'application/json',
							data: JSON.stringify(data),
							success: function (res) {
								// window.location.href = "<c:url value='/admin/news/" + res.id + "'/>";
								window.location.href = "<c:url value='/admin/news/list'/>";
							},
							error: function(e) {
								console.log("ERROR LOAD API Update Admin News: ", e);
								window.location.href = "<c:url value='/admin/news/list'/>";								
							},
							done : function(e) {
									console.log("DONE");
							}

						});
				}
			</script>
		</body>
		</html>