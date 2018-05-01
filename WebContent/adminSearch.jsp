<%@page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>EZ Music</title>
		<style>
			body{
				background-image:url(./image/body_background.jpg);
				margin:0px;
			}
			.header{
				top:100px;
				position:absolute;
				margin-left:45%;
				color:white;
			}
		</style>
		<link rel="stylesheet" href="./css/component.css">
		<link rel="stylesheet" type="text/css" href="css/search/default.css">
		<link rel="stylesheet" type="text/css" href="css/search/search-form2.css">
	</head>
	<body>
		<div class="content">
    	<div id="large-header" class="large-header">
    		<canvas id="demo-canvas"></canvas>
    		<div class="innerContainer" >
		<!-- 头部 -->
			<div class="header"><h1>歌曲查找</h1></div>
				<form action="/MusicRecommendation/SearchResultServlet"  id="search-form"> 
					<div class="search-wrapper active">
						<div class="input-holder">
							<input type="text" name="search-input" class="search-input" placeholder="歌曲/歌手" />
							<input type="hidden" name="userID" value="<%=session.getAttribute("userID")%>">
							<button class="search-icon" ><span></span></button>
						</div>
						<a class="act-but submit" style="color: #FFFFFF" href="manage.jsp">返回</a>
					</div>
				</form>
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

		_html = "Yup yup! Your search text sounds like this: ";
		document.getElementById("search-form").submit();
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
				</div>
			</div>
		</div>
	<script src="js/TweenLite.min.js"></script>
	<script src="js/EasePack.min.js"></script>
	<script src="js/rAF.js"></script>
	<script src="js/demo-1.js"></script>
	</body>
</html>