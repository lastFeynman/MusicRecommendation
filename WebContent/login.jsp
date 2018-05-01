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
				if(strlen(document.getElementById("userID").value) == 7){
					document.getElementById("login_form").submit();
				}
				else{
					alert('用户ID为7位，请检查您的ID');
				}
			}		
			
			function strlen(str){  
		         var len = 0;  
		         for(var i = 0;i < str.length;i++){  
		             if (str.charCodeAt(i) > 255) {  
		                 len += 2;  
		             }else {  
		                 len++;  
		             }  
		         }  
		         return len;  
		     }  
		</script>
	</HEAD>
		<BODY>
		<div class="container">
			<div class="content">
				<div id="large-header" class="large-header">
					<canvas id="demo-canvas"></canvas>
					<div class="logo_box">
						<h3>Welcome to EZ Music</h3>
			                <form action="/MusicRecommendation/LoginServlet" method="post" id="login_form">
               				<div class="input_outer">
								<span class="u_user"></span>
								<input name="userID" class="text" style="color: #FFFFFF !important" type="text" placeholder="请输入账户" id="userID">
							</div>
							<div class="input_outer">
								<span class="us_uer"></span>
								<input name="password" class="text" style="color: #FFFFFF !important; position:absolute; z-index:100;"value="" type="password" placeholder="请输入密码" >
							</div>
							<div class="mb2"><a class="act-but submit" href="javascript:void(0);" style="color: #FFFFFF"onclick="login()">登录</a></div>
							<div class="mb2"><a class="act-but submit" href="/MusicRecommendation/LoginServlet" style="color: #FFFFFF">游客登录</a></div>
                            </form>
                   </div>
			  </div>
		   </div>
	    </div><!-- /container -->
	    <script src="js/TweenLite.min.js"></script>
		<script src="js/EasePack.min.js"></script>
		<script src="js/rAF.js"></script>
		<script src="js/demo-1.js"></script>
	</BODY>
</HTML>