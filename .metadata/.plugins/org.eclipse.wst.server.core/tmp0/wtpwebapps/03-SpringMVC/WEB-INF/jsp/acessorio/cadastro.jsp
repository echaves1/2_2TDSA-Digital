<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<tags:template titulo="Cadastro de Acessorio">
	
	<jsp:body>
		<h1>Cadastro</h1>
		<c:url value="/acessorio/cadastro" var="cadastro"></c:url>
		<form:form action="cadastro" method="post" commandName="acessorio">
			<div class="form-group">
				<form:label path="nome">Nome</form:label>
				<form:input path="nome" cssClass="form-control"/>
			</div>
			<div class="form-group">
				<form:label path="nome">Preço</form:label>
				<form:input path="nome" cssClass="form-control"/>
			</div>
			<div class="form-group">
				<form:label path="nome">Descrição</form:label>
				<form:input path="nome" cssClass="form-control"/>
			</div>
			<div class="form-group">
				<form:label path="nome">Disponivel</form:label>
				<form:input path="nome" cssClass="form-control"/>
			</div>
			<input type="submit" value="Salvar" class="btn btn-primary">
		</form:form>
				
	</jsp:body>
</tags:template>