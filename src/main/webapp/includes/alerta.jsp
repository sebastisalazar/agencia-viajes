<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    

<%@page import="controllers.Alerta"%>

<c:if test="${not empty alerta}">
 
	<div class="alert alert-${alerta.tipo} alert-dismissible fade show"  style="vertical-align: baseline;"  role="alert">
	 <p class="text-center"> ${alerta.texto} </p>
	 	<button type="button" class="close" data-dismiss="alert"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
	
	</div>
	
	<%session.setAttribute("alerta",null);%>
	
</c:if>











