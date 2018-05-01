<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %> 
<!DOCTYPE html>
<HTML>
	<HEAD>
		<TITLE>管理功能测试</TITLE>
		<link rel="stylesheet" href="./css/component.css">
		<style>
			.innerContainer{
				position:absolute;
				top:0px;
				height:200px;
				width:100%
			}
			body{
        		background-image:url(./image/body_background.jpg);
        	}
		</style>
	</HEAD>
    <body>
    <div class="content">
    	<div id="large-header" class="large-header">
    		<canvas id="demo-canvas"></canvas>
    		<div class="innerContainer" >
    		<center><div class="siteName"><font size = "40px" color="white"><br><b>管理功能</b></font></div></center>
    			<table align="center" width="700" border="0" >
    			<br><br><br><br>
    				<td align="center">
    					<a class="act-but submit" style="color: #FFFFFF" href="addMusic.jsp">添加新的音乐</a>
    				</td>
    				<td align="center">
    					<a class="act-but submit" style="color: #FFFFFF" href="adminSearch.jsp">管理已有音乐</a>
    				</td>
 			   </table>
 			 </div>
 		</div>
    </div>
    <script src="js/TweenLite.min.js"></script>
	<script src="js/EasePack.min.js"></script>
	<script src="js/rAF.js"></script>
	<script src="js/demo-1.js"></script>
	</body>
</HTML>