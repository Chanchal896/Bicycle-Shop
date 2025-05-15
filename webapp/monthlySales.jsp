<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="in.sp.backend.DAO.SalesDAO, Classes.Sales, java.util.List, java.sql.Date, java.util.Calendar" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Weekly Sales Report</title>
</head>
<body>
    <h2>Weekly Sales Report</h2>
    <%
        SalesDAO sdao = new SalesDAO();
        Calendar cal = Calendar.getInstance();
        Date endDate = new Date(cal.getTimeInMillis());
        cal.add(Calendar.DAY_OF_MONTH, -30);
        Date startDate = new Date(cal.getTimeInMillis());
        List<Sales> sales = sdao.getSalesByDateRange(startDate, endDate);
        if(sales == null || sales.isEmpty()){
    %>
        <p>No sales recorded in the last week.</p>
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
