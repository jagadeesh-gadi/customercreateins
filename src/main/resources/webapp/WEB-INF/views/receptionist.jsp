<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Receptionist List</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/styles.css' />"/>
</head>
<body>
    <h1>Receptionist List</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Contact</th>
        </tr>
        <c:forEach var="receptionist" items="${receptionists}">
            <tr>
                <td>${receptionist.id}</td>
                <td>${receptionist.name}</td>
                <td>${receptionist.contact}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
