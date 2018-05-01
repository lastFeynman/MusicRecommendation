<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title>EZ Music NoResult</title>
		<link rel="stylesheet" href="./css/UserIndex.css">
		<link rel="stylesheet" type="text/css" href="css/search/default.css">
		<link rel="stylesheet" type="text/css" href="css/search/search-form.css">
		<link rel="stylesheet" type="text/css" href="css/SearchResult.css">
	</head>
	<body>
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

		<h1 class="noResultInfo">抱歉，没有找到相关音乐！！</h1>
		</div>
	</div>
	<script src="js/TweenLite.min.js"></script>
	<script src="js/EasePack.min.js"></script>
	<script src="js/rAF.js"></script>
	<script src="js/demo-1.js"></script>
	</body>
	
	
</html>