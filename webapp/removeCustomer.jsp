<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Remove Customer</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
  <div class="container my-5">
    <div class="card shadow">
      <div class="card-body">
        <h2 class="card-title mb-4">Remove a Customer</h2>
        <form action="removeCustomer" method="post">
          <div class="mb-3">
            <label for="customerID" class="form-label">Customer ID:</label>
            <input type="number" name="customerID" id="customerID" required class="form-control"/>
          </div>
          <div class="mb-3">
            <input type="submit" value="Remove Customer" class="btn btn-danger"/>
          </div>
        </form>
        <% if(request.getAttribute("errorMessage") != null){ %>
          <p class="text-danger"><%= request.getAttribute("errorMessage") %></p>
        <% } %>
        <a href="dashboard.jsp" class="btn btn-secondary mt-3">Back to Dashboard</a>
      </div>
    </div>
  </div>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
