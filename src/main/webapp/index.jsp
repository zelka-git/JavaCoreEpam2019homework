<%--
  Created by IntelliJ IDEA.
  User: anzel
  Date: 11.02.2020
  Time: 22:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<% if (request.getAttribute("error")!=null) { %>
    <div class="alert">
    <h1><%=request.getAttribute("error")%><h1/>
    <img src="https://www.irishtimes.com/polopoly_fs/1.2943353.1484846599!/image/image.jpg_gen/derivatives/box_620_330/image.jpg">
    </div>    
    <%}%>
    <form action="<%=request.getContextPath() + "/selectstorage"%>" method="post">
        <table>
            <tr>
                <td colspan="2">Select storage type</td>
            </tr>

            <tr>
                <td>storage type</td>
                <td class="select">
                    <select name="storageType">
                        <option value="BULLSHIT"></option>
                        <option value="ARRAY">ARRAY</option>
                        <option value="COLLECTION">COLLECTION</option>
                        <option value="DB">DB</option>
                    </select>
                </td>
            </tr>
        </table>

        <input type="submit" value="SELECT"/>
    </form>
</body>
</html>
