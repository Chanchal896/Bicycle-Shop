<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="in.sp.backend.DAO.SalesDAO, Classes.Sales, java.util.List, java.sql.Date" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Daily Sales Report</title>
</head>
<body>
    <h2>Daily Sales Report</h2>
    <%
        SalesDAO sdao = new SalesDAO();
        Date today = new Date(System.currentTimeMillis());
        List<Sales> sales = sdao.getSalesByDateRange(today, today);
        if(sales == null || sales.isEmpty()){
    %>
        <p>No sales recorded for today.</p>
    <%
        } else {
    %>
    <table border="1" cellspacing="0" cellpadding="5">
        <tr>
            <th>Sales ID</th>
            <th>Customer ID</th>
            <th>Sales Date</th>
            <th>Total Amount</th>
        </tr>
    <%
            for(Sales sale : sales){
    %>
        <tr>
            <td><%= sale.getSalesID() %></td>
            <td><%= sale.getCustomerID() %></td>
            <td><%= sale.getSalesDate() %></td>
            <td><%= sale.getTotalAmount() %></td>
        </tr>
    <%
            }
    %>
    </table>
    <%
        }
    %>
    <br/>
    <a href="dashboard.jsp">Back to Dashboard</a>
</body>
</html>
