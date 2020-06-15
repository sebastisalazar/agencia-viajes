<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="alert alert-danger alert-dismissible fade show" role="alert">
	<!-- lee los mensajes del array y los va escribiendo cada uno en etiqueta p -->
	<c:forEach items="${requeridos}" var="r">
		<ul>
			<li>${r}</li>
		</ul>
	</c:forEach>

	<button type="button" class="close" data-dismiss="alert"
		aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
	
</div>

