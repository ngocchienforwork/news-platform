<%@ page import="vn.chienlengoc.utils.SecurityUtils" %>
	<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
		<%@include file="/common/taglib.jsp"%>
			<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
			<div class="header">
				<div class="headertop_desc">
					<div class="call">
						<p>
							<span>Need help?</span> call us
							<span class="number">070-4063-3202</span>
						</p>
					</div>
					<div class="account_desc">
						<ul>
							<security:authorize access="isAnonymous()">
								<li>
									<a href="#">
										<spring:message code="Label.Register" text="Register" />
									</a>
								</li>
								<li>
									<a href='<c:url value="/login"/>'>
										<spring:message code="Label.Login" text="Login" />
									</a>
								</li>
							</security:authorize>
							<security:authorize access="isAuthenticated()">
								<li>	
									<c:set var="val" value="<%=SecurityUtils.getPrincipal().getFullName()%>" />
									<c:if test="${val == 'admin' }">	
										<a href="<c:url value='/admin/home'/>">
											<spring:message code="Label.Welcome" text="Welcome" />,
											${val}
										</a>
									</c:if>
									<c:if test="${val != 'admin' }">
										<a href="#">
											<spring:message code="Label.Welcome" text="Welcome" />,
											${val}
										</a>
									</c:if>
								</li>
								</li>
								<li>
									<a href='<c:url value="/logout"/>'>
										<spring:message code="Label.Logout" text="Logout" />
									</a>
								</li>
							</security:authorize>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<div class="header_top">
					<div class="logo">
						<a href="#">
							<img src="<c:url value='/image/logo-header.png' />" alt="">
						</a>
					</div>
					<script type="text/javascript">
						function DropDown(el) {
							this.dd = el;
							this.initEvents();
						}
						DropDown.prototype = {
							initEvents: function () {
								var obj = this;

								obj.dd.on('click', function (event) {
									$(this).toggleClass('active');
									event.stopPropagation();
								});
							}
						}

						$(function () {

							var dd = new DropDown($('#dd'));

							$(document).click(function () {
								// all dropdowns
								$('.wrapper-dropdown-2').removeClass('active');
							});

						});
					</script>
					<div class="clear"></div>
				</div>
				<div class="header_bottom">
					<div class="search_box">
						<form>
							<input type="text" value="Search" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Search';}">
							<input type="submit" value="">
						</form>
					</div>
					<div class="menu">
						<ul>
							<li class="active">
								<a href='<c:url value="/home-page"/>'>
									<spring:message code="Label.Home" text="Home" />
								</a>
							</li>
							<li>
								<a href="#">
									<spring:message code="Label.AboutUs" text="About Us" />
								</a>
							</li>
							<li class="active">
								<a href='<c:url value="/contact-us"/>'>
									<spring:message code="Label.ContactUs" text="Contact Us" />
								</a>
							</li>
							<div class="clear"></div>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
			</div>