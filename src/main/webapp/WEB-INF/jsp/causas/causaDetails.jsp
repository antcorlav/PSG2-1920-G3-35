<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="causas">

    <h2>Causa Information</h2>


    <table class="table table-striped">
       <tr>
            <th>Descripcion</th>
            <td><c:out value="${causa.description}"/></td>
        </tr>
        <tr>
            <th>ONG</th>
            <td><c:out value="${causa.ong}"/></td>
        </tr>
        <tr>
            <th>Cantidad a alcanzar</th>
            <td><c:out value="${causa.objetivo}"/></td>
        </tr>
        <tr>
            <th>Cantidad recaudada</th>
            <td><c:out value="${causa.dineroRecaudado}"/></td>
        </tr>
         <tr>
            <th>Validez</th>
            <td><c:out value="${causa.valido}"/></td>
        </tr>
    </table>
		<c:choose>
              <c:when test="${causa.valido}">
              <spring:url value="/donacion/{ownerId}/{causaId}/new" var="causaUrl">
        	<spring:param name="causaId" value="${causa.id}"/>
        	<spring:param name="ownerId" value="${owner.id}"/>
   		</spring:url>
   		<a href="${fn:escapeXml(causaUrl)}" class="btn btn-default">Hacer Donacion</a>
              </c:when>
         <c:otherwise>
         </c:otherwise>
        </c:choose>
         <br/>
    <br/>
     <h2>Donaciones</h2>
     <table class="table table-striped">
     	<thead>
     	<tr>
           <th>Nombre</th>
           <th>Cantidad</th>
           <th>Fecha</th>
       </tr>
       </thead>
        <c:forEach var="donacion" items="${causa.donaciones}">
        	<tr>
	            <td><c:out value="${donacion.owner.firstName} ${donacion.owner.lastName}"/></td>
	            <td><c:out value="${donacion.cantidad}"/></td>
	            <td><c:out value="${donacion.fecha}"/></td>
	        </tr>
        </c:forEach>
     </table>

    <br/>
    <br/>
    <br/>
   

</petclinic:layout>