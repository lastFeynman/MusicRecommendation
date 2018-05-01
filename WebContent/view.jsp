<%@page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Entity.Song"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
<html>
	<head>
	    <meta charset="UTF-8">
		<title>查找结果</title>
		<style>
			body{
				color:white;
				background-image:url(./image/body_background.jpg);
				background-attachment:fixed;
			}
			.innerContainer{
				position:absolute;
				top:0;
				width:100%;
			}
			table{
				width:60%;
				margin-left:25%;
				margin-top:7%;
			}
			td{
				align:center;
				width:15%;			
			}
			tr{
				height:30%;
			}
		</style>
		<link rel="stylesheet" href="./css/component.css">
	</head>
	<body>
		<%ArrayList<Song> songList = (ArrayList<Song>) session.getAttribute("songList"); %>
		<%String songInfo = (String)request.getAttribute("songInfo");%>
    	<div id="large-header" class="large-header">
    		<canvas id="demo-canvas"></canvas>
    	<div class="innerContainer">
         <div class="siteName"><h1 align="center">查找结果，请选择修改、删除</h1></div>
			  <table>
		<tr>
			<td><div class ="SongId_title">歌曲id</div></td>
			<td><div class ="SongName_title">歌曲名</div></td>
			<td><div class ="SongerName_title">歌手名</div></td>
			<td><div class ="Album_title">专辑名</div></td>
			<td></td>
			<td></td>
		</tr>
		<c:forEach items="<%=songList %>" var="song" varStatus="vs">
			<tr>
				<td ><div class ="SongId_style">${song.id}</div></td>
				<td><div class ="SongName_style">${song.name}</div></td>
				<td><div class ="SongSinger_style">${song.singer}</div></td>
				<td><div class ="Album_style">${song.album}</div></td>
				<td>
					<form action="updateMusic.jsp" method="get">
					<div class="Alter_style">
						<input type="hidden" name="id1" value="${song.id}">
						<input type="submit" value="修改" ></div></form></td>
				<td>
					<form action="/MusicRecommendation/DeleteServlet" method="post">
					<div class="Delete_style">
						<input type="hidden" name="id2" value="${song.id}">
						<input type="hidden" name="songInfo" value="<%=songInfo%>">
						<input type="submit" value="删除" ></div></form></td>
			</tr>
		</c:forEach>
	</table>
	</div>
	</div>
	<script src="js/TweenLite.min.js"></script>
	<script src="js/EasePack.min.js"></script>
	<script src="js/rAF.js"></script>
	<script src="js/demo-1.js"></script>
	</body>
</html>
