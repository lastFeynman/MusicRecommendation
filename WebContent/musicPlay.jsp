<%@page language="java" pageEncoding="utf-8"%>
<%@page import="Entity.Song"%>
<%@page import="java.util.ArrayList"%>
<%String playList =(String)request.getAttribute("varPlayList");%>
<%int currentIndex = (int)request.getAttribute("currentIndex");%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="stylesheet" href="./js/player/smusic.min.css">
		<meta charset="UTF-8">
		<title>EZ Music Player</title>

	</head>
	<body>
	<!-- <a href="/MusicRecommendation/PlayMusicServlet">test</a> -->
	<div id="my-music"></div>
	<script src="./js/player/smusic.min.js"></script>
	 <script>
	 	var playList = <%=playList%>;
	</script>
	<script>
	var smusic = SMusic(playList, {
		container : document.getElementById('my-music')
	});
	smusic.init();
	for(var i = 0;i < <%=currentIndex%>;i++){
		smusic.next();
	}
</script>
	</body>
	
	
</html>