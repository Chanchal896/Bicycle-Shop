<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Record Repair</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <div class="container mt-5">
        <div class="card shadow p-4 mx-auto" style="max-width: 600px;">
            <h2 class="text-center mb-4">Record Repair</h2>
            <form action="recordRepair" method="post">
                <div class="mb-3">
                    <label for="customerID" class="form-label">Customer ID</label>
                    <input type="number" class="form-control" id="customerID" name="customerID" required>
                </div>
                <div class="mb-3">
                    <label for="repairDetails" class="form-label">Repair Details</label>
                    <textarea class="form-control" id="repairDetails" name="repairDetails" rows="4" required></textarea>
                </div>
                <div class="mb-3">
                    <label for="totalCost" class="form-label">Total Cost</label>
                    <input type="number" class="form-control" id="totalCost" name="totalCost" step="0.01" required>
                </div>
                <div class="d-grid">
                    <button type="submit" class="btn btn-primary">Submit Repair</button>
                </div>
            </form>
            <%
                String error = (String) request.getAttribute("errorMessage");
                if (error != null) {
            %>
                <div class="alert alert-danger mt-3 text-center"><%= error %></div>
            <%
                }
            %>
            <div class="text-center mt-3">
                <a href="dashboard.jsp" class="text-decoration-none">Back to Dashboard</a>
            </div>
        </div>
    </div>
</body>
</html>
