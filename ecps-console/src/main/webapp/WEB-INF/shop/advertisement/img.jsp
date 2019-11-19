<%@ include file="/ecps/console/common/taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
<title>${ad.adName}</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta http-equiv="content-language" content="utf-8" />
<meta name="robots" content="all" />
<meta name="author" content="<fmt:message key="company.name"/>" />
<meta name="copyright" content="<fmt:message key="company.url"/>" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes, minimum-scale=1.0"/>
<meta name="apple-mobile-web-app-capable" content="yes" />
<!--[if gt IE 7]>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<![endif]-->
<meta http-equiv="Cache-Control" content="no-store"/>
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-cache, must-revalidate" />
<meta http-equiv="Expires" content="0" />
<link rel="icon" href="<c:url value="/res/imgs/favicon.ico"/>" type="image/x-icon" />
<link rel="shortcut icon" href="<c:url value="/res/imgs/favicon.ico"/>" type="image/x-icon" />
<meta name="heading" content="<fmt:message key='mainMenu.heading'/>"/>
<meta name="menu" content="AdvertisementMenu"/>
</head>
<body id="main">
<img src="${rsImgUrlInternal}${ad.adImg}" style="<c:if test='${not empty width}'>width:${width}px;</c:if>
   <c:if test='${not empty height}'>height:${height}px;</c:if>">
</body>

