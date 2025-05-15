<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="in.sp.backend.DAO.ProductDAO" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Total Inventory Value</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
  <div class="container my-5">
    <div class="card shadow">
      <div class="card-body">
        <h2 class="card-title mb-4">Total Inventory Value On Hand</h2>
        <%
          ProductDAO pdao = new ProductDAO();
          double totalValue = pdao.getTotalInventoryValue();
        %>
        <p class="fs-5">The total inventory value is: $<%= totalValue %></p>
        <a href="dashboard.jsp" class="btn btn-secondary mt-3">Back to Dashboard</a>
      </div>
    </div>
  </div>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
