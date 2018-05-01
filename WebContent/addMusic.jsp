<%@page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
<html>
	<head>
		<title>添加音乐</title>
		<link rel="stylesheet" href="./css/addMusic.css">
		<link rel="stylesheet" href="./css/component.css">
		<script>
			function filesSubmit(){
				if(document.getElementById("uploadFile1").value == ""){
					alert("请选择您需要上传的文件！"); 
				}
				else {
					document.getElementById("files").submit();
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
			function check(){
				var check = new Array();
				for(var i = 0;i < 10;i++){
					check[i] = 0;
				}
				if(strlen(document.getElementById("id").value) == 7){
					check[0] = 1;
				}
				else{
					alert('您的歌曲ID不符合长度为固定7位的要求！请修改后重试');
				}
				if(strlen(document.getElementById("name").value) < 100 && strlen(document.getElementById("name").value) > 0){
					check[1] = 1;
				}
				else{
					alert('您的歌曲名称不符合不为空且最大长度为100位的要求！请修改后重试');
				}
				if(strlen(document.getElementById("singer").value) < 100 && strlen(document.getElementById("singer").value) > 0){
					check[2] = 1;
				}
				else{
					alert('您的歌手名称不符合不为空且最大长度为100位的要求！请修改后重试');
				}
				if(strlen(document.getElementById("album").value) < 100){
					check[3] = 1;
				}
				else{
					alert('您的专辑名称不符合不为空且最大长度为100位的要求！请修改后重试');
				}
				if(document.getElementById("style").value < 7 && document.getElementById("style").value > 0){
					check[4] = 1;
				}
				else{
					alert('您的音乐风格编号不符合类型为1~6的整数！请修改后重试');
				}
				if(document.getElementById("category").value < 5 && document.getElementById("category").value > 0){
					check[5] = 1;
				}
				else{
					alert('您的音乐语种编号不符合类型为1~4的整数！请修改后重试');
				}
				if(document.getElementById("scene").value < 5 && document.getElementById("scene").value > 0){
					check[6] = 1;
				}
				else{
					alert('您的音乐场景编号不符合类型为1~4的整数！请修改后重试');
				}
				if(strlen(document.getElementById("url").value) < 492 && strlen(document.getElementById("url").value) > 0){
					check[7] = 1;
				}
				else{
					alert('您的歌曲文件名不符合不为空且最大长度为492位的要求！请修改后重试');
				}
				if(strlen(document.getElementById("cover").value) < 492){
					check[8] = 1;
				}
				else{
					alert('您的封面文件名不符合不为空且最大长度为492位的要求！请修改后重试');
				}
				if(strlen(document.getElementById("lrc").value) < 492){
					check[9] = 1;
				}
				else{
					alert('您的歌词文件名不符合不为空且最大长度为492位的要求！请修改后重试');
				}
				
				var submit = 1;
				for(var j = 0;j < 10;j++){
					if(check[j] != 1){
						submit = 0;
						break;
					}
				}
				
				if(submit == 1){
					document.getElementById("record").submit();
				}
				
			}
		</script>
	</head>
	
    <body>
    	<div class="content">
    	<div id="large-header" class="large-header">
    		<canvas id="demo-canvas"></canvas>
    	<div class="innerContainer">
		<form action="/MusicRecommendation/UploadServlet" method="post" enctype="multipart/form-data" id="files">
		
		<h1 align=center>请输入音乐信息:</h1>
		
		    <table align="center" width="500" border="0">
			<tr>
				<td> <div class="frameA">选择音乐文件:</div><br></td>
				<td><input type="file"  id="uploadFile1" name="uploadFile1" class="uploadFile1frame"><br></td>
			</tr>
			<tr>
				<td> <div class="frameB">选择封面文件:</div><br></td>
				<td><input type="file"  name="uploadFile2" class="uploadFile2frame"><br></td>
			</tr>
			<tr>
				<td> <div class="frameC">选择歌词文件:</div><br></td>
				<td><input type="file"  name="uploadFile3" class="uploadFile3frame"><br></td>
			</tr>
			<tr>
				<td><div class="submitframe1"><a class="act-but submit" style="color: #FFFFFF" href="javascript:filesSubmit()">上传</a></div></td>
			</tr>
		</table>
	
		</form>
		
		<form action="/MusicRecommendation/MusicAddServlet" method="post" id="record">
			<table align="center" width="1000" border="0">
			<tr>
				<td> <div class="frame1">音乐编号:</div><br></td>
				<td><input type="text" name="id" id="id"class="idframe"><br></td>
			</tr>
			<tr>
				<td> <div class="frame2">音乐名称:</div><br></td>
				<td><input type="text" name="name" id="name"class="nameframe"><br></td>
			</tr>
			<tr>
				<td> <div class="frame3">歌手名称:</div><br></td>
				<td><input type="text" name="singer" id="singer"class="singerframe"><br></td>
			</tr>
			<tr>
				<td> <div class="frame4">音乐专辑:</div><br></td>
				<td><input type="text" name="album" id="album"class="albumframe"><br></td>
			</tr>
			<tr>
				<td> <div class="frame5">音乐风格编号:</div><br></td>
				<td><input type="text" name="style" id="style"class="styleframe"><br></td>
			</tr>
			<tr>
				<td> <div class="frame6">音乐语种编号:</div><br></td>
				<td><input type="text" name="category" id="category"class="categoryframe"><br></td>
			</tr>
			<tr>
				<td> <div class="frame7">音乐场景编号:</div><br></td>
				<td><input type="text" name="scene" id="scene"class="sceneframe"><br></td>
			</tr>
			<tr>
				<td> <div class="frame8">音乐文件名:</div><br></td>
				<td><input type="text" name="url"  id="url" class="urlframe"><br></td>
			</tr>
			<tr>
				<td> <div class="frame9">封面文件名:</div><br></td>
				<td><input type="text" name="cover"  id="cover" class="coverframe"><br></td>
			</tr>
			<tr>
				<td> <div class="frame10">歌词文件名:</div><br></td>
				<td><input type="text" name="lrc"  id="lrc" class="lrcframe"><br></td>
			</tr>
			<tr>
			    <td><div class="buttonframe"><a class="act-but submit" style="color: #FFFFFF" href="manage.jsp">返回</a></div><br></td>
			</tr>
			<tr>
			     <td><div class="submitframe"><a class="act-but submit" style="color: #FFFFFF" href="javascript:check()">提交</a></div><br></td>
			</tr>
			</table>
			</form>
			</div>
			</div>
		</div>
		<script src="js/TweenLite.min.js"></script>
		<script src="js/EasePack.min.js"></script>
		<script src="js/rAF.js"></script>
		<script src="js/demo-1.js"></script>
		</body>
</html>


