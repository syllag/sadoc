<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/png" href="img/logo.png" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/style.css" type="text/css" rel="stylesheet" />
<title>SADoc</title>
</head>
<body>
	<div  class="wrap">

	<c:if test="${ not empty mail }">
		<c:import url="../layouts/Menu.jsp"/>
	</c:if>
	<c:if test="${ empty mail }">
		<c:import url="../layouts/MenuNotConnected.jsp"/>
	</c:if>

	<div class="wrap_body">

	<div id="wrapper">