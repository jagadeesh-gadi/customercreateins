<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Patient List</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/styles.css' />"/>
</head>
<body>
    <h1>Patient List</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Ailment</th>
        </tr>
        <c:forEach var="patient" items="${patients}">
            <tr>
                <td>${patient.id}</td>
                <td>${patient.name}</td>
                <td>${patient.ailment}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
