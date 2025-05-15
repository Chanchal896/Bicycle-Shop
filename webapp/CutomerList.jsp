<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Classes.Customer" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Customer List</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
  <div class="container my-5">
    <div class="card shadow">
      <div class="card-body">
        <h2 class="card-title mb-4">Customer List</h2>
        <%
          List<Customer> customers = (List<Customer>) request.getAttribute("customers");
          if (customers == null || customers.isEmpty()) {
        %>
          <p>No customers found.</p>
        <%
          } else {
        %>
          <div class="table-responsive">
            <table class="table table-bordered">
              <thead class="table-dark">
                <tr>
                  <th>Customer ID</th>
                  <th>Name</th>
                  <th>Email</th>
                </tr>
              </thead>
              <tbody>
                <%
                  for (Customer c : customers) {
                %>
                <tr>
                  <td><%= c.getId() %></td>
                  <td><%= c.getName() %></td>
                  <td><%= c.getEmail() %></td>
                </tr>
                <%
                  }
                %>
              </tbody>
            </table>
          </div>
        <%
          }
        %>
        <a href="dashboard.jsp" class="btn btn-secondary mt-3">Back to Dashboard</a>
      </div>
    </div>
  </div>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
