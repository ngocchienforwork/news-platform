<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@include file="/common/taglib.jsp"%>
		<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
		<html>

		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
			<title>
				<spring:message code="Label.Home" text="Home" />
			</title>
		</head>
		<body>
			<div class="header_slide">
				<div class="header_bottom_left">
					<div class="categories">
						<ul>
							<h3>
								<spring:message code="Label.Caterogy" text="Caterogy" />
							</h3>
							<c:forEach var="category" items="${model.categories}">
								<li>
									<a href="<c:url value='/tin-tuc/${category.id}/${newstags:seoURL(category.code)}'/>">${category.name}</a>
								</li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<div class="header_bottom_right">
					<div class="slider">
						<div id="slider">
							<div id="mover">
								<!-- /////////////////TOP1///////////////////// -->
								<!-- <div class="slide"></div> -->
								<c:forEach var="view" items="${model.tops}" varStatus="loop">
									<c:set var="val" value="${loop.index}" />
									<c:if test="${val < 3}">
										<c:choose>
											<c:when test="${val == '0'}">
												<div id="slide-1" class="slide">
											</c:when>
											<c:otherwise>
												<div class="slide">
											</c:otherwise>
										</c:choose>
											<div class="slider-img">
												<a href="<c:url value='/tin-tuc/${view.categoryId}/${newstags:seoURL(view.categoryCode)}/${view.id}/${newstags:seoURL(view.title)}'/>">
													<img src="<c:url value='/repository/${view.thumbnail}'/>" alt="learn more" />
												</a>
											</div>
											<div class="slider-text">
												<h1>Clearance
													<br>
													<span>SALE</span>
												</h1>
												<h2>UPTo 20% OFF</h2>
												<div class="features_list">
													<h4>${view.title}</h4>
												</div>
												<a href="<c:url value='/tin-tuc/${view.categoryId}/${newstags:seoURL(view.categoryCode)}/${view.id}/${newstags:seoURL(view.title)}'/>"
													class="button">Shop Now</a>
											</div>
											<div class="clear"></div>
										<!-- close div in c:choose -->
										</div>
									</c:if>
								</c:forEach>
							</div>
							<div class="clear"></div>
						</div>
					</div>
				</div>
				<!--end bottom right-->
				<div class="clear"></div>
			</div>
			<div class="main">
				<div class="content">
					<div class="content_top">
						<div class="heading">
							<h3>
								<spring:message code="Label.TopView" text="TopView" />
							</h3>
						</div>
						<div class="see">
							<p>
								<a href="#">
									<spring:message code="Label.ShowAll" text="Show All" />
								</a>
							</p>
						</div>
						<div class="clear"></div>
					</div>

					<div class="section group">
						<c:forEach var="view" items="${model.tops}">
							<div class="grid_1_of_4 images_1_of_4">
								<a href="#">
									<img src="<c:url value='/repository/${view.thumbnail}'/>" width="300px"
									height="150px" alt="">
								</a>
								<h2>${fn:substring(view.title,0,20)}</h2>
								<div class="price-details">
									<div class="price-number">
									</div>
									<div class="add-cart">
										<h4>
											<a href="<c:url value='/tin-tuc/${view.categoryId}/${newstags:seoURL(view.categoryCode)}/${view.id}/${newstags:seoURL(view.title)}'/>">
												<spring:message code="Label.Detail" text="Detail" />
											</a>
										</h4>
									</div>
									<div class="clear"></div>
								</div>
							</div>
						</c:forEach>
					</div>
					<div class="content_top">
						<div class="heading">
							<h3>
								<spring:message code="Label.NewPost" text="New Post" />
							</h3>
						</div>
						<div class="see">
							<p>
								<a href="#">
									<spring:message code="Label.ShowAll" text="Show All" />
								</a>
							</p>
						</div>
						<div class="clear"></div>
					</div>
					<div class="section group">
						<c:forEach var="items" items="${model.createdNews}">
							<div class="grid_1_of_4 images_1_of_4">
								<a href="#">
									<img src="<c:url value='/repository/${items.thumbnail}'/>" width="300px"
									height="150px" alt="">
								</a>
								<h2>${fn:substring(items.title,0,20)}</h2>
								<div class="price-details">
									<div class="price-number">
									</div>
									<div class="add-cart">
										<h4>
											<a href="<c:url value='/tin-tuc/${items.categoryId}/${newstags:seoURL(items.categoryCode)}/${items.id}/${newstags:seoURL(items.title)}'/>">
												<spring:message code="Label.Detail" text="Detail" />
											</a>
										</h4>
									</div>
									<div class="clear"></div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</body>

		</html>