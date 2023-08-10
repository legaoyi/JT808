<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<script type="text/javascript" src="<c:url value='/js/jquery/jQuery-2.2.0.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery/jquery.json-2.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery/jquery.validate.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery/jquery.validate.ext.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/bootstrap/js/bootstrap.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/bootstrap/js/bootstrap-table.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/bootstrap/js/bootstrap-table-zh-CN.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/bootstrap/js/bootstrap-select.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/bootstrap/js/bootstrap-datetimepicker.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/bootstrap/js/bootstrap-datetimepicker.zh-CN.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/layer-v3.1.1/layer.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/common/jsMap.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/common/localStorage.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/common/cache.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/common/dateFormat.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/common/common.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/common/ajaxUtil.js?v=20200106'/>"></script>
<script type="text/javascript" src="<c:url value='/js/base.js?v=20200103'/>"></script>
<script>
function getContextPath(){
	return  "<%=request.getContextPath()%>"; 
}

function isCacheQuery(){
	return "${param.isCacheQuery}";
}

function getUserAccount(){
	return "${sessionScope.user.userAccount}";
}

function getUserName(){
	return "${sessionScope.user.userName}";
}

function isSuperAdmin(){
	return "${sessionScope.user.userType}" == "1";
}

function getUserEnterpriseId(){
	return "${sessionScope.user.enterpriseId}";
}

document.oncontextmenu = function(){
  return false;
}
</script>