<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Record a Return</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
  <div class="container mt-5">
    <div class="card shadow-sm">
      <div class="card-body">
        <h2 class="card-title mb-4">Record a Return</h2>
        <form action="recordReturn" method="post">
          <div class="mb-3">
            <label for="salesID" class="form-label">Sales ID:</label>
            <input type="number" name="salesID" id="salesID" required class="form-control"/>
          </div>
          <div class="mb-3">
            <label for="refAmount" class="form-label">Refund Amount:</label>
            <input type="text" name="refAmount" id="refAmount" required class="form-control"/>
          </div>
          <div class="mb-3">
            <input type="submit" value="Record Return" class="btn btn-primary"/>
          </div>
        </form>
        <% if (request.getAttribute("errorMessage") != null) { %>
            <p class="text-danger"><%= request.getAttribute("errorMessage") %></p>
        <% } %>
        <a href="dashboard.jsp" class="btn btn-link">Back to Dashboard</a>
      </div>
    </div>
  </div>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
