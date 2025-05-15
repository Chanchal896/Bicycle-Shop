<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Order New Product</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
  <div class="container mt-5">
    <div class="card shadow-sm">
      <div class="card-body">
        <h2 class="card-title mb-4">Order a New Product</h2>
        <form action="orderProduct" method="post">
          <div class="mb-3">
            <label for="productId" class="form-label">Product ID:</label>
            <input type="number" name="productId" id="productId" required class="form-control"/>
          </div>
          <div class="mb-3">
            <label for="quantity" class="form-label">Quantity:</label>
            <input type="number" name="quantity" id="quantity" required class="form-control"/>
          </div>
          <div class="mb-3">
            <label for="price" class="form-label">Price per Unit:</label>
            <input type="text" name="price" id="price" required class="form-control"/>
          </div>
          <div class="mb-3">
            <input type="submit" value="Place Order" class="btn btn-primary"/>
          </div>
        </form>
        <% if(request.getAttribute("errorMessage") != null) { %>
            <p class="text-danger"><%= request.getAttribute("errorMessage") %></p>
        <% } %>
        <a href="dashboard.jsp" class="btn btn-link">Back to Dashboard</a>
      </div>
    </div>
  </div>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
