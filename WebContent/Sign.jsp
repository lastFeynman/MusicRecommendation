<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page session="false"%>
<!DOCTYPE html>
<HTML>
	<HEAD>
		<meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge"> 
        <meta name="viewport" content="width=device-width, initial-scale=1"> 
        <title>EZ Music</title>
        <!--必要样式-->
        <link rel="stylesheet" type="text/css" href="./css/component.css" />
        <style>
        	body{
        		background-image:url(./image/body_background.jpg);
        	}
        </style>
        <!--[if IE]>
        <script src="js/html5.js"></script>
        <![endif]-->
		<script type="text/javascript">
			function login(){		
					document.getElementById("SignIn_form").submit();	
			}

		</script>
	</HEAD>
		<BODY>
		<div class="container">
			<div class="content">
				<div id="large-header" class="large-header">
					<canvas id="demo-canvas"></canvas>
					<div class="logo_box">
						<h3>用户注册</h3>
			                <form action="SignInServlet" method="post" id="SignIn_form">
               				<div class="input_outer">
								<span class="u_user"></span>
								<input name="username" class="text" style="color: #FFFFFF !important" type="text" placeholder="用户名不超过10位" id="username">
							</div>
							<div class="input_outer">
								<span class="us_uer"></span>
								<input name="password" class="text" style="color: #FFFFFF !important; position:absolute; z-index:100;"value="" type="password" placeholder="密码长度不超过20位" id="password" >
							</div>
							<div class="input_outer">
								<span class="us_uer"></span>
								<input name="password1" class="text" style="color: #FFFFFF !important; position:absolute; z-index:100;"value="" type="password" placeholder="请确认密码"  id="password1">
							</div>
							<div class="mb2"><a class="act-but submit" href="javascript:void(0);" style="color: #FFFFFF"onclick="login()">注册</a></div>
							<div class="mb2"><a class="act-but submit" href="/MusicRecommendation/LoginServlet" style="color: #FFFFFF">返回</a></div>
                            </form>
                   </div>
			  </div>
		   </div>
	    </div><!-- /container -->
	    <script src="js/TweenLite.min.js"></script>
		<script src="js/EasePack.min.js"></script>
		<script src="js/rAF.js"></script>
		<script src="js/demo-1.js"></script>
<script>
  var errori ='<%=request.getParameter("pserror")%>';
  if(errori=='no'){
   alert("请输入信息");
  }
  </script>
  <script> 
  var errori ='<%=request.getParameter("pserror")%>';
  if(errori=='no'){
   alert("请输入信息");
  }
</script>
  
${tishi}
	</BODY>
</HTML>