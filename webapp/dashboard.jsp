<%@page import="Classes.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title> Dashboard </title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    body {
      background-color: #f8f9fa;
    }
  </style>
</head>
<body>
    <%
        User user = (User)session.getAttribute("user");
        if(user == null) {
            response.sendRedirect("login.jsp");
            return;
        }
    %>
    <div class="container mt-5">
      <div class="card shadow">
        <div class="card-body">
          <h2 class="card-title">Welcome, <%= user.getUsername() %>!</h2>
          <p class="card-text">Your role is: <%= user.getRole() %></p>
          
          <h3 class="mt-4">Navigation</h3>
          <ul class="list-group mb-3">
            <li class="list-group-item"><a href="addCustomer.jsp">Add Customer</a></li>
            <li class="list-group-item"><a href="addProduct.jsp">Add Product</a></li>
            <li class="list-group-item"><a href="OrderProduct.jsp">Order Product</a></li>
            <li class="list-group-item"><a href="RecordSale.jsp">Record Sale</a></li>
            <li class="list-group-item"><a href="RecordReturn.jsp">Record Return</a></li>
            <li class="list-group-item"><a href="RecordRepair.jsp">Record Repair</a></li>
          </ul>
          
          <h3>Reports</h3>
          <ul class="list-group">
            <li class="list-group-item"><a href="customerList">Display Customer List</a></li>
            <li class="list-group-item">
              <a href="dailySales.jsp">Display Daily Sales</a> | 
              <a href="weeklySales.jsp">Display Weekly Sales</a> | 
              <a href="monthlySales.jsp">Display Monthly Sales</a>
            </li>
            <li class="list-group-item"><a href="productInventory.jsp">Display Inventory of a Certain Product</a></li>
            <li class="list-group-item"><a href="TotalValue.jsp">Display All Inventory Value On Hand</a></li>
            <li class="list-group-item"><a href="weeklyRepairs.jsp">Display Weekly Repairs</a></li>
            <li class="list-group-item"><a href="removeCustomer.jsp">Remove a Customer</a></li>
            <li class="list-group-item"><a href="removeProduct.jsp">Remove a Product</a></li>
            <li class="list-group-item"><a href="adjustInventory.jsp">Adjust Inventory</a></li>
          </ul>
        </div>
      </div>
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
