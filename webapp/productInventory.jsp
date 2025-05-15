<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Classes.Product, in.sp.backend.DAO.ProductDAO" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Product Inventory</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
  <div class="container my-5">
    <div class="card shadow">
      <div class="card-body">
        <h2 class="card-title mb-4">Product Inventory</h2>
        <form method="post" action="productInventory.jsp" class="mb-4">
          <div class="mb-3">
            <label for="productId" class="form-label">Enter Product ID:</label>
            <input type="number" name="productId" id="productId" required class="form-control" />
          </div>
          <button type="submit" class="btn btn-primary">View Inventory</button>
        </form>
        <%
          String productIdStr = request.getParameter("productId");
          if(productIdStr != null) {
              int productId = Integer.parseInt(productIdStr);
              ProductDAO pdao = new ProductDAO();
              Product product = pdao.getProductById(productId);
              if(product != null){
        %>
                  <h3 class="mt-4">Product Details:</h3>
                  <ul class="list-group">
                    <li class="list-group-item">Product ID: <%= product.getProductId() %></li>
                    <li class="list-group-item">Product Name: <%= product.getProductName() %></li>
                    <li class="list-group-item">Price: <%= product.getPrice() %></li>
                    <li class="list-group-item">Stock Quantity: <%= product.getStockQuantity() %></li>
                  </ul>
        <%
              } else {
        %>
                  <div class="alert alert-warning mt-4" role="alert">
                    No product found with that ID.
                  </div>
        <%
              }
          }
        %>
        <a href="dashboard.jsp" class="btn btn-secondary mt-4">Back to Dashboard</a>
      </div>
    </div>
  </div>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
