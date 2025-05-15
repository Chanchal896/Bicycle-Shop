<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="in.sp.backend.DAO.RepairDAO, Classes.Repair, java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Weekly Repairs</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
  <div class="container my-5">
    <div class="card shadow">
      <div class="card-body">
        <h2 class="card-title mb-4">Weekly Repairs Report</h2>
        <%
          RepairDAO rdao = new RepairDAO();
          List<Repair> repairs = rdao.getWeeklyRepairs();
          if(repairs == null || repairs.isEmpty()){
        %>
          <p>No repairs recorded in the last week.</p>
        <%
          } else {
        %>
          <div class="table-responsive">
            <table class="table table-bordered">
              <thead class="table-dark">
                <tr>
                  <th>Repair ID</th>
                  <th>Customer ID</th>
                  <th>Repair Details</th>
                  <th>Total Cost</th>
                  <th>Repair Date</th>
                </tr>
              </thead>
              <tbody>
                <%
                  for(Repair rep : repairs){
                %>
                <tr>
                  <td><%= rep.getRepairID() %></td>
                  <td><%= rep.getCustomerID() %></td>
                  <td><%= rep.getRepairDetails() %></td>
                  <td><%= rep.getTotalCost() %></td>
                  <td><%= rep.getRepairDate() %></td>
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
