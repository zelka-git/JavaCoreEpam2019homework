<%@ page import="ru.epam.javacore.homework20200210.cargo.domain.Cargo" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/all.css">
    <link rel="stylesheet" href="css/style.css">

    <link href="https://fonts.googleapis.com/css?family=Roboto&display=swap" rel="stylesheet">
    <title>Title</title>
</head>
<body class="container">

<jsp:include page="head.jsp" flush="true"/>
<%
    List<Cargo> cargos = (List<Cargo>) request.getAttribute("allCargos");
    for (int i = 0; i < cargos.size(); i++) { %>


<h2><%=cargos.get(i).getName()%></h2>
<div>
    <span><%=cargos.get(i).getId()%></span>
    <span><%=cargos.get(i).getName()%></span>
    <span><%=cargos.get(i).getWeight()%></span>
    <span><%=cargos.get(i).getCargoType().toString()%></span>
</div>


<% } %>

</body>
</html>
