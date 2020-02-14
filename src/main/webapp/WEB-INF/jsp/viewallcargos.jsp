<%@ page import="ru.epam.javacore.homework20200210.cargo.domain.Cargo" %>
<%@ page import="java.util.List" %>
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://xmlns.jcp.org/jsf/html"
      xmlns:ui="http://xmlns.jcp.org/jsf/facelets"
      xmlns:f="http://xmlns.jcp.org/jsf/core">
<f:view>
    <h:outputLabel value="Hello, world"/>
</f:view>
<body>
<%
    List<Cargo> cargos = (List<Cargo>) request.getAttribute("allCargos");
    for (int i = 0; i < cargos.size(); i++) { %>


<h2><%=cargos.get(i).getName()%></h2>


<% } %>

</body>
</html>
