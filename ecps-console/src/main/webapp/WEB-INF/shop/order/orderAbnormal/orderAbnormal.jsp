<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/ecps/console/common/taglibs.jsp"%>
<head>
<title>异常单_<fmt:message key="OrderMgmtMenu.title"/></title>
<meta name="heading" content="<fmt:message key='mainMenu.heading'/>"/>
<meta name="menu" content="OrderMgmtMenu"/>
<script type="text/javascript" src="<c:url value='/ecps/console/res/js/jquery.form.js'/>"></script>
<script type="text/javascript" src="<c:url value='/ecps/console/res/js/order.js'/>"></script>
<script type="text/javascript" src="<c:url value='/ecps/console/res/plugins/My97DatePicker/WdatePicker.js'/>"></script>
<script language="javascript" type="text/javascript">
$(function(){	
	var orderState=$("#orderState").val();
	switch(orderState){
	 case "":$("#label1").attr("class","here");break;
	 case "12":$("#label2").attr("class","here");break;
// 	 case "23":$("#label4").attr("class","here");break;
	 case "9":$("#label5").attr("class","here");break;
	 case "18,10,11,13,17,19,20,29,14,24,26,2,3,4,5,6,7,8,21,22,15,34,23,32,25,31,33,38":$("#label3").attr("class","here");break;
	}
	
});
function exportOrder(s){
    var form = document.getElementById("form1");
    form.action="${path}/order/orderExport.do?s="+s+"&type=2&type2=1";
    subVerify4Title($("#orderNo"));
    subVerify4Title($("#phone"));
    subVerify4Title($("#shipName"));
    subVerify4Title($("#userName"));
    form.submit();
    form.action="${path}/order/orderAbnormal.do";
}
</script>
<jsp:include page="/ecps/console/common/jscript.jsp"/>
</head>
<body id="main">
<div class="frameL"><div class="menu icon">
    <jsp:include page="/${system}/common/ordermenu.jsp"/>
</div></div>

<div class="frameR"><div class="content">
    <div class="loc icon"><samp class="t12"></samp><fmt:message key='menu.current.loc'/>：<fmt:message key="OrderMgmtMenu.title"/>&nbsp;&raquo;&nbsp;<span class="gray" title="异常订单">异常单</span></div>
    
     <h2 class="h2_ch">
      <span id="tabs" class="l">
      	<ui:permTag src="/ecps/console/order/orderAbnormal.do?all=all">
        	<a id="label1" href="${path}/order/orderAbnormal.do?all=all"   title="全部" class="nor">全部</a>
        </ui:permTag>
        <ui:permTag src="/ecps/console/order/orderAbnormal.do?orderState=12">
        	<a id="label2" href="${path}/order/orderAbnormal.do?orderState=12"   title="配送失败待处理" class="nor">配送失败待处理</a> 
        </ui:permTag>
        <ui:permTag src="/ecps/console/order/orderAbnormal.do?orderState=9">
        	<a id="label5" href="${path}/order/orderAbnormal.do?orderState=9"   title="CRM异常待处理" class="nor">CRM异常待处理</a>   
        </ui:permTag>
        <ui:permTag src="/ecps/console/order/orderAbnormal.do?orderState=18,10,11,13,17,19,20,29,14,24,26,2,3,4,5,6,7,8,21,22,15,34,23,32,25,31,33,38">
        <a id="label3" href="${path}/order/orderAbnormal.do?orderState=18,10,11,13,17,19,20,29,14,24,26,2,3,4,5,6,7,8,21,22,15,34,23,32,25,31,33,38"   title="已处理" class="nor">已处理</a>
      	</ui:permTag>
      </span>
      <span class="r inb_a">
            <input id="button1" type="button" class="hand btn120x20" onclick="exportOrder(1)" value="导出当前页订单"/>&nbsp;&nbsp;<input id="button2" type="button" class="hand btn120x20" onclick="exportOrder(2)" value="导出全部订单"/>
        </span>
     </h2>
    <form action="${path}/order/orderAbnormal.do" id="form1" name="form1" method="post">
    <div class="sch"><p>查询：<jsp:include page="/ecps/console/common/search.jsp"/></div>

    <table cellspacing="0" summary="" class="tab" id="myTable">
    <tr>
    <th>归属地</th>
    <th>订单号</th>
    <th>收货人</th>
    <th>联系电话</th>
    <th>支付金额</th>
    <th>支付方式</th>
    <th>支付状态</th>
    <th>下单用户</th>
    <th>下单时间</th>
    <th>操作时间</th>
    <th>订单状态</th>
    <th>操作</th>
    </tr>
    <c:forEach items="${pagination.list}" var="p">
    <tr>
    <td>${p.areaName}</td>
    <td><a href="${path}/order/orderAbnormal/detail.do?orderId=${p.orderId}&taskId=${p.taskId}">${p.orderNum}</a></td>
    <td>${p.shipName}</td>
    <td>
		<c:choose>
			<c:when test="${not empty p.phone && not empty p.fixedPhone}">
				${p.phone}&nbsp;/&nbsp;${p.fixedPhone}
			</c:when>
			<c:when test="${not empty p.phone}">
				${p.phone}
			</c:when>
			<c:when test="${not empty p.fixedPhone}">
				${p.fixedPhone}
			</c:when>
		</c:choose>
    </td>
    <td><fmt:formatNumber value="${p.orderSum/100}" pattern="#0.00"/></td>
    <td><ui:orderState var="${p.payment}" type="2"/></td>
    <td><ui:orderState var="${p.isPaid}" type="3"/></td>
    <td class="nwp">${p.ptlUser.username}</td>
    <td class="nwp"><fmt:formatDate value="${p.orderTime}" pattern="yyyy-MM-dd"/><br /><fmt:formatDate value="${p.orderTime}" pattern="HH:mm:ss"/></td>
    <td class="nwp"><fmt:formatDate value="${p.updateTime}" pattern="yyyy-MM-dd"/><br /><fmt:formatDate value="${p.updateTime}" pattern="HH:mm:ss"/></td>
    <td class="nwp"><ui:orderModule var="${p.orderState}" type="1"/></td>
    <td><a href="${path}/order/orderAbnormal/detail.do?orderId=${p.orderId}&taskId=${p.taskId}">查看</a></td>
    </tr>
    </c:forEach>
    </table>
    <div class="page_c">
    	<span class="r page inb_a">
            共 <var class="red">${pagination.totalCount}</var> 条&nbsp;&nbsp;
            <var><c:out value="${pagination.pageNo}"/>/<c:out value="${pagination.totalPage}"/></var>
            <c:if test='${pagination.pageNo==1}'><span class="inb" title="上一页"><fmt:message key="tag.page.previous"/></span></c:if>
            <c:if test='${pagination.pageNo>1}'><a href="javascript: gotoPage('${pagination.prePage}');" title="上一页"><fmt:message key="tag.page.previous"/></a></c:if>
            <c:if test='${pagination.totalPage==pagination.pageNo}'><span class="inb" title="下一页"><fmt:message key="tag.page.next"/></span></c:if>
            <c:if test='${pagination.totalPage>pagination.pageNo}'><a href="#" onclick="gotoPage('${pagination.nextPage}')"><fmt:message key="tag.page.next"/></a></c:if>
            <input type="hidden" value="${pagination.totalPage}" id="paginationTotal" name="paginationTotal" />
            <input type="hidden" value="${pageNo}" id="pageNo" name="pageNo" />
            <input type="text" id="number1" name="number1" class="txts" size="3" />
	        <input type="button" onclick="gotoPage('jump')" value="<fmt:message key="tag.page.jump"/>" class="hand" />
    	</span>
	</div>
    </form>


</div></div>
</body>
