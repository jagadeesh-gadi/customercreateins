<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Appointment List</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/styles.css' />"/>
</head>
<body>
    <h1>Appointment List</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Patient ID</th>
            <th>Doctor ID</th>
            <th>Date & Time</th>
        </tr>
        <c:forEach var="appointment" items="${appointments}">
            <tr>
                <td>${appointment.id}</td>
                <td>${appointment.patientId}</td>
                <td>${appointment.doctorId}</td>
                <td>${appointment.dateTime}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
