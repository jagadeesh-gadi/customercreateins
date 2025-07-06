<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Doctor List</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/styles.css' />"/>
</head>
<body>
    <h1>Doctor List</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Specialty</th>
        </tr>
        <c:forEach var="doctor" items="${doctors}">
            <tr>
                <td>${doctor.id}</td>
                <td>${doctor.name}</td>
                <td>${doctor.specialty}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
