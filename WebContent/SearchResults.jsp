<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title>EZ Music SearchResult</title>
		<link rel="stylesheet" href="./css/UserIndex.css">
		<link rel="stylesheet" type="text/css" href="css/search/default.css">
		<link rel="stylesheet" type="text/css" href="css/search/search-form.css">
		<link rel="stylesheet" type="text/css" href="css/SearchResult.css">
	</head>
	<body>
		${noInputAlert}
		${noExistAlert}
		<%
			String userName = (String)request.getAttribute("userName");
			String userID = (String)request.getAttribute("userID");
			int managerFlag = (int)session.getAttribute("managerFlag");
			request.setAttribute("userName", userName);
			request.setAttribute("userID",userID);
		%>
		<div class="content">
    	<div id="large-header" class="large-header">
    		<canvas id="demo-canvas"></canvas>
		<!-- 头部 -->
		<header class="headerStyle">
			<div class="siteLogo"><img src="./image/siteLogo.png" width="130" height="100"></div>
			<div class="siteName"><h1>二组音乐推荐网站</h1></div>
			<div class="searchBar">
				<form method="get" action="/MusicRecommendation/SearchResultServlet"  id="search-form" onsubmit="submitFn(this, event);">
					<div class="search-wrapper">
						<div class="input-holder">
							<input type="text" name="search-input" class="search-input" placeholder="歌曲/歌手" />
							<input type="hidden" name="userID" value="${userID}" >
							<input type="hidden" name="userName" value="${userName}" >
							<button class="search-icon" onclick="searchToggle(this, event);"><span></span></button>
						</div>
						<span class="close" onclick="searchToggle(this, event);"></span>
						<div class="result-container">

						</div>
					</div>
				</form>
			</div>
			<div class="userName"><h3>${userName}</h3></div>
		</header>
		<script src="js/search/jquery-1.11.0.min.js" type="text/javascript"></script>
		<script type="text/javascript">
			function searchToggle(obj, evt){
				var container = $(obj).closest('.search-wrapper');

				if(!container.hasClass('active')){
			  		container.addClass('active');
			  		evt.preventDefault();
			  	}
				else if(container.hasClass('active') && $(obj).closest('.input-holder').length == 0){
			  		container.removeClass('active');
			  		// clear input
			  		container.find('.search-input').val('');

					//document.getElementById("search-form").submit();
			  		// clear and hide result container when we press close
			  		container.find('.result-container').fadeOut(100, function(){$(this).empty();});
				}
			}
			
			function submitFn(obj, evt){
				value = $(obj).find('.search-input').val().trim();

				document.getElementById("search-form").submit();
				_html = "Yup yup! Your search text sounds like this: ";
				if(!value.length){
					_html = "Yup yup! Add some text friend :D";
				}
				else{
					_html += "<b>" + value + "</b>";
				}

				$(obj).find('.result-container').html('<span>' + _html + '</span>');
				$(obj).find('.result-container').fadeIn(100);

				evt.preventDefault();
			}
		</script>
		
		<!-- 播放页面跳转script -->
		<script type="text/javascript">
			function playMusic(songID){
				var param = "?songID=" + songID;
				musicPlay = window.open('/MusicRecommendation/PlayMusicServlet' + param,'musicPlay');
				musicPlay.focus;
			}
		</script>

		<div class="resultTitle">
			<div class="titleSong"><h1>歌曲</h1></div>
			<div class="titleSinger"><h1>歌手</h1></div>
		</div>
		<div class="infoContainer">
			<p>共找到${rowAll}条结果，共${pageAll}页，当前第${pageNow}页</p>
		</div>
		<div class="resultContainer">
			<% 
				String songID[] = new String[9];
				for(int i=0;i<9;i++)
					songID[i] = (String)request.getAttribute("songID"+i);
			%>
			<div class="shadowResult">
				<table align="center">
				<tr>
					<td class="searchSongName"><h3>${songName0}</h3></td>
					<td class="searchSinger"><h3>${songSinger0}</h3></td>
					<% 
						if(songID[0] !=null){
							if(managerFlag == 1){
								out.write("<td class=\"searchUpdate\"><a  href=\"updateMusic.jsp?songID="+ songID[0] + "\">修改</a></td>");
								out.write("<td class=\"searchDelete\"><a  href=\"/MusicRecommendation/DeleteServlet?songID="+ songID[0] + "\">删除</a></td>");
							}
							out.write("<td><a style=\"cursor:pointer\" onclick=\"playMusic('" + songID[0] +"')\"><img class=\"playButton\"></a></td>");
						}
					%>
				</tr>
				</table>
			</div>
			<div class="darkResult">
				<table align="center">
				<tr>
					<td class="searchSongName"><h3>${songName1}</h3></td>
					<td class="searchSinger"><h3>${songSinger1}</h3></td>
					<% 
						if(songID[1] !=null){
							if(managerFlag == 1){
								out.write("<td class=\"searchUpdate\"><a  href=\"updateMusic.jsp?songID="+ songID[1] + "\">修改</a></td>");
								out.write("<td class=\"searchDelete\"><a  href=\"/MusicRecommendation/DeleteServlet?songID="+ songID[1] + "\">删除</a></td>");
							}
							out.write("<td><a style=\"cursor:pointer\" onclick=\"playMusic('" + songID[1] +"')\"><img class=\"playButton\"></a></td>");
						}
					%>
				</tr>
				</table>
			</div>
			<div class="shadowResult">
				<table align="center">
				<tr>
					<td class="searchSongName"><h3>${songName2}</h3></td>
					<td class="searchSinger"><h3>${songSinger2}</h3></td>
					<% 
						if(songID[2] !=null){
							if(managerFlag == 1){
								out.write("<td class=\"searchUpdate\"><a  href=\"updateMusic.jsp?songID="+ songID[2] + "\">修改</a></td>");
								out.write("<td class=\"searchDelete\"><a  href=\"/MusicRecommendation/DeleteServlet?songID="+ songID[2] + "\">删除</a></td>");
							}
							out.write("<td><a style=\"cursor:pointer\" onclick=\"playMusic('" + songID[2] +"')\"><img class=\"playButton\"></a></td>");
						}
							
					%>
				</tr>
				</table>
			</div>
			<div class="darkResult">
				<table align="center">
				<tr>
					<td class="searchSongName"><h3>${songName3}</h3></td>
					<td class="searchSinger"><h3>${songSinger3}</h3></td>
					<% 
						if(songID[3] !=null){
							if(managerFlag == 1){
								out.write("<td class=\"searchUpdate\"><a  href=\"updateMusic.jsp?songID="+ songID[3] + "\">修改</a></td>");
								out.write("<td class=\"searchDelete\"><a  href=\"/MusicRecommendation/DeleteServlet?songID="+ songID[3] + "\">删除</a></td>");
							}
							out.write("<td><a style=\"cursor:pointer\" onclick=\"playMusic('" + songID[3] +"')\"><img class=\"playButton\"></a></td>");
						}
					%>
				</tr>
				</table>
			</div>
			<div class="shadowResult">
				<table align="center">
				<tr>
					<td class="searchSongName"><h3>${songName4}</h3></td>
					<td class="searchSinger"><h3>${songSinger4}</h3></td>
					<% 
						if(songID[4] !=null){
							if(managerFlag == 1){
								out.write("<td class=\"searchUpdate\"><a  href=\"updateMusic.jsp?songID="+ songID[4] + "\">修改</a></td>");
								out.write("<td class=\"searchDelete\"><a  href=\"/MusicRecommendation/DeleteServlet?songID="+ songID[4] + "\">删除</a></td>");
							}
							out.write("<td><a style=\"cursor:pointer\" onclick=\"playMusic('" + songID[4] +"')\"><img class=\"playButton\"></a></td>");
						}
					%>
				</tr>
				</table>
			</div>
			<div class="darkResult">
				<table align="center">
				<tr>
					<td class="searchSongName"><h3>${songName5}</h3></td>
					<td class="searchSinger"><h3>${songSinger5}</h3></td>
					<% 
						if(songID[5] !=null){
							if(managerFlag == 1){
								out.write("<td class=\"searchUpdate\"><a  href=\"updateMusic.jsp?songID="+ songID[5] + "\">修改</a></td>");
								out.write("<td class=\"searchDelete\"><a  href=\"/MusicRecommendation/DeleteServlet?songID="+ songID[5] + "\">删除</a></td>");
							}
							out.write("<td><a style=\"cursor:pointer\" onclick=\"playMusic('" + songID[5] +"')\"><img class=\"playButton\"></a></td>");
						}
					%>
				</tr>
				</table>
			</div>
			<div class="shadowResult">
				<table align="center">
				<tr>
					<td class="searchSongName"><h3>${songName6}</h3></td>
					<td class="searchSinger"><h3>${songSinger6}</h3></td>
					<% 
						if(songID[6] !=null){
							if(managerFlag == 1){
								out.write("<td class=\"searchUpdate\"><a  href=\"updateMusic.jsp?songID="+ songID[6] + "\">修改</a></td>");
								out.write("<td class=\"searchDelete\"><a  href=\"/MusicRecommendation/DeleteServlet?songID="+ songID[6] + "\">删除</a></td>");
							}
							out.write("<td><a style=\"cursor:pointer\" onclick=\"playMusic('" + songID[6] +"')\"><img class=\"playButton\"></a></td>");
						}
					%>
				</tr>
				</table>
			</div>
			<div class="darkResult">
				<table align="center">
				<tr>
					<td class="searchSongName"><h3>${songName7}</h3></td>
					<td class="searchSinger"><h3>${songSinger7}</h3></td>
					<% 
						if(songID[7] !=null){
							if(managerFlag == 1){
								out.write("<td class=\"searchUpdate\"><a  href=\"updateMusic.jsp?songID="+ songID[7] + "\">修改</a></td>");
								out.write("<td class=\"searchDelete\"><a  href=\"/MusicRecommendation/DeleteServlet?songID="+ songID[7] + "\">删除</a></td>");
							}
							out.write("<td><a style=\"cursor:pointer\" onclick=\"playMusic('" + songID[7] +"')\"><img class=\"playButton\"></a></td>");
						}
					%>
				</tr>
				</table>
			</div>
			<div class="shadowResult">
				<table align="center">
				<tr>
					<td class="searchSongName"><h3>${songName8}</h3></td>
					<td class="searchSinger"><h3>${songSinger8}</h3></td>
					<% 
						if(songID[8] !=null){
							if(managerFlag == 1){
								out.write("<td class=\"searchUpdate\"><a  href=\"updateMusic.jsp?songID="+ songID[8] + "\">修改</a></td>");
								out.write("<td class=\"searchDelete\"><a  href=\"/MusicRecommendation/DeleteServlet?songID="+ songID[8] + "\">删除</a></td>");
							}
							out.write("<td><a style=\"cursor:pointer\" onclick=\"playMusic('" + songID[8] +"')\"><img class=\"playButton\"></a></td>");
						}
					%>
				</tr>
				</table>
			</div>
		</div>
		
		<div class="pageNav">
			<div class="prePage"><a href="/MusicRecommendation/PageDivideServlet?pageAsk=${prePage}&pageNow=${pageNow}"><h3 align="center">上一页</h3></a></div>
			<form action="/MusicRecommendation/PageDivideServlet?pageNow=${pageNow}&" method="get">
				<p><input type="text" name="pageAsk" onKeyUp="value=value.replace(/[^\d]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"></p> 
				<p class="pageText">页</p>
				<input type="hidden" name="pageNow" value="${pageNow}">
				<p><input type="submit" value="转到"></p>
			</form>
			<div class="nextPage"><a href="/MusicRecommendation/PageDivideServlet?pageAsk=${pageNext}&pageNow=${pageNow}"><h3 align="center">下一页</h3></a></div>
		</div>
		</div>
	</div>
	<script src="js/TweenLite.min.js"></script>
	<script src="js/EasePack.min.js"></script>
	<script src="js/rAF.js"></script>
	<script src="js/demo-1.js"></script>
	</body>
</html>