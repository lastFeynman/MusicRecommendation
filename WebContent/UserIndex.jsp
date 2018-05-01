<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title>EZ Music</title>
		<link rel="stylesheet" href="./css/UserIndex.css">
		<link rel="stylesheet" type="text/css" href="css/search/default.css">
		<link rel="stylesheet" type="text/css" href="css/search/search-form.css">
	</head>
	<body>
	<div class="container">
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
		
		<!-- 个性推荐 -->
		<div class="recommendHolder">
			<div class="recommendTitle"><h1>——&nbsp;&nbsp;&nbsp;&nbsp;个性推荐&nbsp;&nbsp;&nbsp;&nbsp;——</h1></div>
			<table align="center">
				<tr>
					<td width="250"><div class="albumPic"><a onclick="playMusic('${songID0000}')"&userID=${userID}"><img src="${albumCover0000}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
					<td width="250"><div class="albumPic"><a onclick="playMusic('${songID0001}')"&userID=${userID}"><img src="${albumCover0001}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
					<td width="250"><div class="albumPic"><a onclick="playMusic('${songID0002}')"&userID=${userID}"><img src="${albumCover0002}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
					<td width="250"><div class="albumPic"><a onclick="playMusic('${songID0003}')"&userID=${userID}"><img src="${albumCover0003}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
				</tr>
			</table>
			<table align="center">
				<tr>
					<td width="250"><div class="songName"><h2 align="center">${songName0000}</h2></div><td>
					<td width="250"><div class="songName"><h2 align="center">${songName0001}</h2></div><td>
					<td width="250"><div class="songName"><h2 align="center">${songName0002}</h2></div><td>
					<td width="250"><div class="songName"><h2 align="center">${songName0003}</h2></div><td>
				</tr>
				<tr>
					<td width="250"><div class="songName"><h2 align="center">${songSinger0000}</h2></div><td>
					<td width="250"><div class="songName"><h2 align="center">${songSinger0001}</h2></div><td>
					<td width="250"><div class="songName"><h2 align="center">${songSinger0002}</h2></div><td>
					<td width="250"><div class="songName"><h2 align="center">${songSinger0003}</h2></div><td>
				</tr>
			</table>
		</div>
		
		<!-- 歌曲分类 -->
		<div class="sortHolder">
			<div class="sortTitle"><h1>——&nbsp;&nbsp;&nbsp;&nbsp;歌曲分类&nbsp;&nbsp;&nbsp;&nbsp;——</h1></div>
			<!--曲风分类-->
			<div class="sortType"><h2>曲风分类</h2></div>
			
			<div class="tabBox">
				<ul class="tabNav">
					<li><a href="#t_1">摇滚</a></li>
					<li><a href="#t_2">爵士</a></li>
					<li><a href="#t_3">流行</a></li>
					<li><a href="#t_4">民谣</a></li>
					<li><a href="#t_5">乡村</a></li>
					<li><a href="#t_6">轻音乐</a></li>
				</ul>
			    <div class="tabContent">
					<div id="t_1">
					<table align="center">
						<tr>
							<td width="250"><div class="albumPic"><a onclick="playMusic('${songID1000}')"&userID=${userID}"><img src="${albumCover1000}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
							<td width="250"><div class="albumPic"><a onclick="playMusic('${songID1001}')"&userID=${userID}"><img src="${albumCover1001}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
							<td width="250"><div class="albumPic"><a onclick="playMusic('${songID1002}')"&userID=${userID}"><img src="${albumCover1002}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
							<td width="250"><div class="albumPic"><a onclick="playMusic('${songID1003}')"&userID=${userID}"><img src="${albumCover1003}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
						</tr>
					</table>
					<table align="center">
						<tr>
							<td width="250"><div class="songName"><h2 align="center">${songName1000}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songName1001}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songName1002}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songName1003}</h2></div><td>
						</tr>
						<tr>
							<td width="250"><div class="songName"><h2 align="center">${songSinger1000}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songSinger1001}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songSinger1002}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songSinger1003}</h2></div><td>
						</tr>
					</table>
					</div>
					<div id="t_2">
					<table align="center">
						<tr>
							<td width="250"><div class="albumPic"><a onclick="playMusic('${songID1010}')"&userID=${userID}"><img src="${albumCover1010}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
							<td width="250"><div class="albumPic"><a onclick="playMusic('${songID1011}')"&userID=${userID}"><img src="${albumCover1011}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
							<td width="250"><div class="albumPic"><a onclick="playMusic('${songID1012}')"&userID=${userID}"><img src="${albumCover1012}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
							<td width="250"><div class="albumPic"><a onclick="playMusic('${songID1013}')"&userID=${userID}"><img src="${albumCover1013}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
						</tr>
					</table>
					<table align="center">
						<tr>
							<td width="250"><div class="songName"><h2 align="center">${songName1010}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songName1011}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songName1012}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songName1013}</h2></div><td>
						</tr>
						<tr>
							<td width="250"><div class="songName"><h2 align="center">${songSinger1010}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songSinger1011}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songSinger1012}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songSinger1013}</h2></div><td>
						</tr>
					</table>
					</div>
					<div id="t_3">
					<table align="center">
						<tr>
							<td width="250"><div class="albumPic"><a onclick="playMusic('${songID1020}')"&userID=${userID}"><img src="${albumCover1020}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
							<td width="250"><div class="albumPic"><a onclick="playMusic('${songID1021}')"&userID=${userID}"><img src="${albumCover1021}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
							<td width="250"><div class="albumPic"><a onclick="playMusic('${songID1022}')"&userID=${userID}"><img src="${albumCover1022}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
							<td width="250"><div class="albumPic"><a onclick="playMusic('${songID1023}')"&userID=${userID}"><img src="${albumCover1023}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
						</tr>
					</table>
					<table align="center">
						<tr>
							<td width="250"><div class="songName"><h2 align="center">${songName1020}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songName1021}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songName1022}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songName1023}</h2></div><td>
						</tr>
						<tr>
							<td width="250"><div class="songName"><h2 align="center">${songSinger1020}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songSinger1021}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songSinger1022}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songSinger1023}</h2></div><td>
						</tr>
					</table>
					</div>
					<div id="t_4">
					<table align="center">
						<tr>
							<td width="250"><div class="albumPic"><a onclick="playMusic('${songID1030}')"&userID=${userID}"><img src="${albumCover1030}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
							<td width="250"><div class="albumPic"><a onclick="playMusic('${songID1031}')"&userID=${userID}"><img src="${albumCover1031}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
							<td width="250"><div class="albumPic"><a onclick="playMusic('${songID1032}')"&userID=${userID}"><img src="${albumCover1032}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
							<td width="250"><div class="albumPic"><a onclick="playMusic('${songID1033}')"&userID=${userID}"><img src="${albumCover1033}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
						</tr>
					</table>
					<table align="center">
						<tr>
							<td width="250"><div class="songName"><h2 align="center">${songName1030}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songName1031}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songName1032}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songName1033}</h2></div><td>
						</tr>
						<tr>
							<td width="250"><div class="songName"><h2 align="center">${songSinger1030}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songSinger1031}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songSinger1032}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songSinger1033}</h2></div><td>
						</tr>
					</table>
					</div>
					<div id="t_5">
					<table align="center">
						<tr>
							<td width="250"><div class="albumPic"><a onclick="playMusic('${songID1040}')"&userID=${userID}"><img src="${albumCover1040}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
							<td width="250"><div class="albumPic"><a onclick="playMusic('${songID1041}')"&userID=${userID}"><img src="${albumCover1041}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
							<td width="250"><div class="albumPic"><a onclick="playMusic('${songID1042}')"&userID=${userID}"><img src="${albumCover1042}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
							<td width="250"><div class="albumPic"><a onclick="playMusic('${songID1043}')"&userID=${userID}"><img src="${albumCover1043}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
						</tr>
					</table>
					<table align="center">
						<tr>
							<td width="250"><div class="songName"><h2 align="center">${songName1040}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songName1041}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songName1042}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songName1043}</h2></div><td>
						</tr>
						<tr>
							<td width="250"><div class="songName"><h2 align="center">${songSinger1040}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songSinger1041}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songSinger1042}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songSinger1043}</h2></div><td>
						</tr>
					</table>
					</div>
					<div id="t_6">
					<table align="center">
						<tr>
							<td width="250"><div class="albumPic"><a onclick="playMusic('${songID1050}')"&userID=${userID}"><img src="${albumCover1050}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
							<td width="250"><div class="albumPic"><a onclick="playMusic('${songID1051}')"&userID=${userID}"><img src="${albumCover1051}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
							<td width="250"><div class="albumPic"><a onclick="playMusic('${songID1052}')"&userID=${userID}"><img src="${albumCover1052}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
							<td width="250"><div class="albumPic"><a onclick="playMusic('${songID1053}')"&userID=${userID}"><img src="${albumCover1053}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
						</tr>
					</table>
					<table align="center">
						<tr>
							<td width="250"><div class="songName"><h2 align="center">${songName1050}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songName1051}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songName1052}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songName1053}</h2></div><td>
						</tr>
						<tr>
							<td width="250"><div class="songName"><h2 align="center">${songSinger1050}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songSinger1051}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songSinger1052}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songSinger1053}</h2></div><td>
						</tr>
					</table>
					</div>
				</div>
			</div>
			
			<!--语种分类-->
			<div class="sortType"><h2>语种分类</h2></div>
			
			<div class="tabBox">
				<ul class="tabNav4">
					<li><a href="#t_7">华语</a></li>
					<li><a href="#t_8">粤语</a></li>
					<li><a href="#t_9">英语</a></li>
					<li><a href="#t_10">其他语种</a></li>
				</ul>
			    <div class="tabContent">
					<div id="t_7">
					<table align="center">
						<tr>
							<td width="250"><div class="albumPic"><a onclick="playMusic('${songID1100}')"&userID=${userID}"><img src="${albumCover1100}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
							<td width="250"><div class="albumPic"><a onclick="playMusic('${songID1101}')"&userID=${userID}"><img src="${albumCover1101}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
							<td width="250"><div class="albumPic"><a onclick="playMusic('${songID1102}')"&userID=${userID}"><img src="${albumCover1102}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
							<td width="250"><div class="albumPic"><a onclick="playMusic('${songID1103}')"&userID=${userID}"><img src="${albumCover1103}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
						</tr>
					</table>
					<table align="center">
						<tr>
							<td width="250"><div class="songName"><h2 align="center">${songName1100}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songName1101}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songName1102}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songName1103}</h2></div><td>
						</tr>
						<tr>
							<td width="250"><div class="songName"><h2 align="center">${songSinger1100}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songSinger1101}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songSinger1102}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songSinger1103}</h2></div><td>
						</tr>
					</table>
					</div>
					<div id="t_8">
					<table align="center">
						<tr>
							<td width="250"><div class="albumPic"><a onclick="playMusic('${songID1110}')"&userID=${userID}"><img src="${albumCover1110}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
							<td width="250"><div class="albumPic"><a onclick="playMusic('${songID1111}')"&userID=${userID}"><img src="${albumCover1111}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
							<td width="250"><div class="albumPic"><a onclick="playMusic('${songID1112}')"&userID=${userID}"><img src="${albumCover1112}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
							<td width="250"><div class="albumPic"><a onclick="playMusic('${songID1113}')"&userID=${userID}"><img src="${albumCover1113}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
						</tr>
					</table>
					<table align="center">
						<tr>
							<td width="250"><div class="songName"><h2 align="center">${songName1110}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songName1111}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songName1112}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songName1113}</h2></div><td>
						</tr>
						<tr>
							<td width="250"><div class="songName"><h2 align="center">${songSinger1110}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songSinger1111}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songSinger1112}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songSinger1113}</h2></div><td>
						</tr>
					</table>
					</div>
					<div id="t_9">
					<table align="center">
						<tr>
							<td width="250"><div class="albumPic"><a onclick="playMusic('${songID1120}')"&userID=${userID}"><img src="${albumCover1120}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
							<td width="250"><div class="albumPic"><a onclick="playMusic('${songID1121}')"&userID=${userID}"><img src="${albumCover1121}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
							<td width="250"><div class="albumPic"><a onclick="playMusic('${songID1122}')"&userID=${userID}"><img src="${albumCover1122}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
							<td width="250"><div class="albumPic"><a onclick="playMusic('${songID1123}')"&userID=${userID}"><img src="${albumCover1123}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
						</tr>
					</table>
					<table align="center">
						<tr>
							<td width="250"><div class="songName"><h2 align="center">${songName1120}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songName1121}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songName1122}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songName1123}</h2></div><td>
						</tr>
						<tr>
							<td width="250"><div class="songName"><h2 align="center">${songSinger1120}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songSinger1121}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songSinger1122}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songSinger1123}</h2></div><td>
						</tr>
					</table>
					</div>
					<div id="t_10">
					<table align="center">
						<tr>
							<td width="250"><div class="albumPic"><a onclick="playMusic('${songID1130}')"&userID=${userID}"><img src="${albumCover1130}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
							<td width="250"><div class="albumPic"><a onclick="playMusic('${songID1131}')"&userID=${userID}"><img src="${albumCover1131}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
							<td width="250"><div class="albumPic"><a onclick="playMusic('${songID1132}')"&userID=${userID}"><img src="${albumCover1132}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
							<td width="250"><div class="albumPic"><a onclick="playMusic('${songID1133}')"&userID=${userID}"><img src="${albumCover1133}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
						</tr>
					</table>
					<table align="center">
						<tr>
							<td width="250"><div class="songName"><h2 align="center">${songName1130}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songName1131}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songName1132}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songName1133}</h2></div><td>
						</tr>
						<tr>
							<td width="250"><div class="songName"><h2 align="center">${songSinger1130}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songSinger1131}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songSinger1132}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songSinger1133}</h2></div><td>
						</tr>
					</table>
					</div>
				</div>
			</div>
			
			<!--场景分类-->
			<div class="sortType"><h2>场景分类</h2></div>
			
			<div class="tabBox">
				<ul class="tabNav4">
					<li><a href="#t_11">学习</a></li>
					<li><a href="#t_12">旅行</a></li>
					<li><a href="#t_13">运动</a></li>
					<li><a href="#t_14">车载</a></li>
				</ul>
			    <div class="tabContent">
					<div id="t_11">
					<table align="center">
						<tr>
							<td width="250"><div class="albumPic"><a onclick="playMusic('${songID1200}')"&userID=${userID}"><img src="${albumCover1200}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
							<td width="250"><div class="albumPic"><a onclick="playMusic('${songID1201}')"&userID=${userID}"><img src="${albumCover1201}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
							<td width="250"><div class="albumPic"><a onclick="playMusic('${songID1202}')"&userID=${userID}"><img src="${albumCover1202}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
							<td width="250"><div class="albumPic"><a onclick="playMusic('${songID1203}')"&userID=${userID}"><img src="${albumCover1203}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
						</tr>
					</table>
					<table align="center">
						<tr>
							<td width="250"><div class="songName"><h2 align="center">${songName1200}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songName1201}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songName1202}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songName1203}</h2></div><td>
						</tr>
						<tr>
							<td width="250"><div class="songName"><h2 align="center">${songSinger1200}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songSinger1201}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songSinger1202}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songSinger1203}</h2></div><td>
						</tr>
					</table>
					</div>
					<div id="t_12">
					<table align="center">
						<tr>
							<td width="250"><div class="albumPic"><a onclick="playMusic('${songID1210}')"&userID=${userID}"><img src="${albumCover1210}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
							<td width="250"><div class="albumPic"><a onclick="playMusic('${songID1211}')"&userID=${userID}"><img src="${albumCover1211}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
							<td width="250"><div class="albumPic"><a onclick="playMusic('${songID1212}')"&userID=${userID}"><img src="${albumCover1212}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
							<td width="250"><div class="albumPic"><a onclick="playMusic('${songID1213}')"&userID=${userID}"><img src="${albumCover1213}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
						</tr>
					</table>
					<table align="center">
						<tr>
							<td width="250"><div class="songName"><h2 align="center">${songName1210}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songName1211}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songName1212}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songName1213}</h2></div><td>
						</tr>
						<tr>
							<td width="250"><div class="songName"><h2 align="center">${songSinger1210}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songSinger1211}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songSinger1212}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songSinger1213}</h2></div><td>
						</tr>
					</table>
					</div>
					<div id="t_13">
					<table align="center">
						<tr>
							<td width="250"><div class="albumPic"><a onclick="playMusic('${songID1220}')"&userID=${userID}"><img src="${albumCover1220}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
							<td width="250"><div class="albumPic"><a onclick="playMusic('${songID1221}')"&userID=${userID}"><img src="${albumCover1221}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
							<td width="250"><div class="albumPic"><a onclick="playMusic('${songID1222}')"&userID=${userID}"><img src="${albumCover1222}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
							<td width="250"><div class="albumPic"><a onclick="playMusic('${songID1223}')"&userID=${userID}"><img src="${albumCover1223}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
						</tr>
					</table>
					<table align="center">
						<tr>
							<td width="250"><div class="songName"><h2 align="center">${songName1220}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songName1221}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songName1222}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songName1223}</h2></div><td>
						</tr>
						<tr>
							<td width="250"><div class="songName"><h2 align="center">${songSinger1220}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songSinger1221}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songSinger1222}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songSinger1223}</h2></div><td>
						</tr>
					</table>
					</div>
					<div id="t_14">
					<table align="center">
						<tr>
							<td width="250"><div class="albumPic"><a onclick="playMusic('${songID1230}')"&userID=${userID}"><img src="${albumCover1230}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
							<td width="250"><div class="albumPic"><a onclick="playMusic('${songID1231}')"&userID=${userID}"><img src="${albumCover1231}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
							<td width="250"><div class="albumPic"><a onclick="playMusic('${songID1232}')"&userID=${userID}"><img src="${albumCover1232}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
							<td width="250"><div class="albumPic"><a onclick="playMusic('${songID1233}')"&userID=${userID}"><img src="${albumCover1233}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
						</tr>
					</table>
					<table align="center">
						<tr>
							<td width="250"><div class="songName"><h2 align="center">${songName1230}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songName1231}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songName1232}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songName1233}</h2></div><td>
						</tr>
						<tr>
							<td width="250"><div class="songName"><h2 align="center">${songSinger1230}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songSinger1231}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songSinger1232}</h2></div><td>
							<td width="250"><div class="songName"><h2 align="center">${songSinger1233}</h2></div><td>
						</tr>
					</table>
					</div>
				</div>
			</div>
		</div>
		
		<!-- 热门歌曲 -->
		<div class="hotHolder">
			<div class="hotTitle"><h1>——&nbsp;&nbsp;&nbsp;&nbsp;热门歌曲&nbsp;&nbsp;&nbsp;&nbsp;——</h1></div>
			<table align="center">
				<tr>
					<td width="250"><div class="albumPic"><a onclick="playMusic('${songID2000}')"&userID=${userID}"><img src="${albumCover2000}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
					<td width="250"><div class="albumPic"><a onclick="playMusic('${songID2001}')"&userID=${userID}"><img src="${albumCover2001}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
					<td width="250"><div class="albumPic"><a onclick="playMusic('${songID2002}')"&userID=${userID}"><img src="${albumCover2002}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
					<td width="250"><div class="albumPic"><a onclick="playMusic('${songID2003}')"&userID=${userID}"><img src="${albumCover2003}" class="covers" height="200" width="200" style="border:25px solid rgba(0,0,0,.0);"></a></div></td>
				</tr>
			</table>
			<table align="center">
				<tr>
					<td width="250"><div class="songName"><h2 align="center">${songName2000}</h2></div><td>
					<td width="250"><div class="songName"><h2 align="center">${songName2001}</h2></div><td>
					<td width="250"><div class="songName"><h2 align="center">${songName2002}</h2></div><td>
					<td width="250"><div class="songName"><h2 align="center">${songName2003}</h2></div><td>
				</tr>
				<tr>
					<td width="250"><div class="songName"><h2 align="center">${songSinger2000}</h2></div><td>
					<td width="250"><div class="songName"><h2 align="center">${songSinger2001}</h2></div><td>
					<td width="250"><div class="songName"><h2 align="center">${songSinger2002}</h2></div><td>
					<td width="250"><div class="songName"><h2 align="center">${songSinger2003}</h2></div><td>
				</tr>
			</table>
		</div>
		</div>
	</div>
	<script src="js/TweenLite.min.js"></script>
	<script src="js/EasePack.min.js"></script>
	<script src="js/rAF.js"></script>
	<script src="js/demo-1.js"></script>
	</body>
	
	
</html>