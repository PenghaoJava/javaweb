<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/11/22 0022
  Time: 11:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Information</title>
    <style>
        th, tr, td,table {
            border: 1px solid black;
        }
    </style>
</head>
<body>
<table>
    <tr>
        <th>FirstName</th>
        <th>SurName</th>
        <th>StreetAddress</th>
    </tr>
    <c:forEach items="${info_list}" var="usr" varStatus="idx">
        <tr>
            <td>${usr.fname}</td><td>${usr.sname}</td><td>${usr.address}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
