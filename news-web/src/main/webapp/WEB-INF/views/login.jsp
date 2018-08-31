<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
	<div class="position-relative">
			<div id="login-box" class="login-box visible widget-box no-border">
				<div class="widget-body">
					<div class="widget-main">
						<h4 class="header blue lighter bigger">
							<i class="ace-icon fa fa-coffee green"></i>
							Login
						</h4>
						<c:if test="${param.incorrectAccount != null}">
							<div class="alert alert-block alert-danger">
							User or password wrong						
							</div>
						</c:if>
						<c:if test="${param.accessDenied != null}">
							<div class="alert alert-block alert-danger">
							Không có quyền truy cập
							</div>
						</c:if>
						<div class="space-6"></div>

						
						<form action="j_spring_security_check" method="POST">
							<fieldset>
								<label class="block clearfix">
									<span class="block input-icon input-icon-right">
										<input type="text" id="username" name="j_username" class="form-control" placeholder="Tên đăng nhập" required/>
										<i class="ace-icon fa fa-user"></i>
									</span>
								</label>

								<label class="block clearfix">
									<span class="block input-icon input-icon-right">
										<input type="password" class="form-control" id="password" name="j_password" placeholder="Mật khẩu" required>
										<i class="ace-icon fa fa-lock"></i>
									</span>
								</label>

								<div class="space"></div>

								<div class="clearfix">
									

									<button type="submit" class="width-35 pull-right btn btn-sm btn-primary">
										<i class="ace-icon fa fa-key"></i>
										<span class="bigger-110">Login</span>
									</button>
								</div>

								<div class="space-4"></div>
							</fieldset>
						</form>
																		
					</div><!-- /.widget-main -->

					<div class="toolbar clearfix">
						<div>
							<a href="#" data-target="#forgot-box" class="forgot-password-link">
								<i class="ace-icon fa fa-arrow-left"></i>
								I forgot my password
							</a>
						</div>

						<div>
							<a href="#" data-target="#signup-box" class="user-signup-link">
								I want to register
								<i class="ace-icon fa fa-arrow-right"></i>
							</a>
						</div>
					</div>
				</div><!-- /.widget-body -->
			</div><!-- /.login-box -->

			<div id="forgot-box" class="forgot-box widget-box no-border">
				<div class="widget-body">
					<div class="widget-main">
						<h4 class="header red lighter bigger">
							<i class="ace-icon fa fa-key"></i>
							Retrieve Password
						</h4>

						<div class="space-6"></div>
						<p>
							Enter your email and to receive instructions
						</p>

						<form>
							<fieldset>
								<label class="block clearfix">
									<span class="block input-icon input-icon-right">
										<input type="email" class="form-control" placeholder="Email" />
										<i class="ace-icon fa fa-envelope"></i>
									</span>
								</label>

								<div class="clearfix">
									<button type="button" class="width-35 pull-right btn btn-sm btn-danger">
										<i class="ace-icon fa fa-lightbulb-o"></i>
										<span class="bigger-110">Send Me!</span>
									</button>
								</div>
							</fieldset>
						</form>
					</div><!-- /.widget-main -->

					<div class="toolbar center">
						<a href="#" data-target="#login-box" class="back-to-login-link">
							Back to login
							<i class="ace-icon fa fa-arrow-right"></i>
						</a>
					</div>
				</div><!-- /.widget-body -->
			</div><!-- /.forgot-box -->

			<div id="signup-box" class="signup-box widget-box no-border">
				<div class="widget-body">
					<div class="widget-main">
						<h4 class="header green lighter bigger">
							<i class="ace-icon fa fa-users blue"></i>
							New User Registration
						</h4>

						<div class="space-6"></div>
						<p> Enter your details to begin: </p>

						<form>
							<fieldset>
								<label class="block clearfix">
									<span class="block input-icon input-icon-right">
										<input type="email" class="form-control" placeholder="Email" />
										<i class="ace-icon fa fa-envelope"></i>
									</span>
								</label>

								<label class="block clearfix">
									<span class="block input-icon input-icon-right">
										<input type="text" class="form-control" placeholder="Username" />
										<i class="ace-icon fa fa-user"></i>
									</span>
								</label>

								<label class="block clearfix">
									<span class="block input-icon input-icon-right">
										<input type="password" class="form-control" placeholder="Password" />
										<i class="ace-icon fa fa-lock"></i>
									</span>
								</label>

								<label class="block clearfix">
									<span class="block input-icon input-icon-right">
										<input type="password" class="form-control" placeholder="Repeat password" />
										<i class="ace-icon fa fa-retweet"></i>
									</span>
								</label>

								<label class="block">
									<input type="checkbox" class="ace" />
									<span class="lbl">
										I accept the
										<a href="#">User Agreement</a>
									</span>
								</label>

								<div class="space-24"></div>

								<div class="clearfix">
									<button type="reset" class="width-30 pull-left btn btn-sm">
										<i class="ace-icon fa fa-refresh"></i>
										<span class="bigger-110">Reset</span>
									</button>

									<button type="button" class="width-65 pull-right btn btn-sm btn-success">
										<span class="bigger-110">Register</span>

										<i class="ace-icon fa fa-arrow-right icon-on-right"></i>
									</button>
								</div>
							</fieldset>
						</form>
					</div>

					<div class="toolbar center">
						<a href="#" data-target="#login-box" class="back-to-login-link">
							<i class="ace-icon fa fa-arrow-left"></i>
							Back to login
						</a>
					</div>
				</div><!-- /.widget-body -->
			</div><!-- /.signup-box -->
	</div><!-- /.position-relative -->
</body>
</html>