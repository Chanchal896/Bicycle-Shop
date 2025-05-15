<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Register</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
  <div class="container d-flex align-items-center justify-content-center" style="min-height: 100vh;">
    <div class="card shadow-sm" style="width: 400px;">
      <div class="card-body">
        <h3 class="card-title text-center mb-4">Register</h3>
        <form action="reg" method="Post">
          <div class="mb-3">
            <label for="name" class="form-label">Name:</label>
            <input type="text" class="form-control" id="name" name="name" required>
          </div>
          <div class="mb-3">
            <label for="pass" class="form-label">Password:</label>
            <input type="password" class="form-control" id="pass" name="pass" required>
          </div>
          <div class="mb-3">
            <label for="confirmpass" class="form-label">Confirm Password:</label>
            <input type="password" class="form-control" id="confirmpass" name="confirmpass" required>
          </div>
          <p class="mb-1">Role:</p>
          <div class="form-check">
            <input class="form-check-input" type="radio" name="role" id="role_admin" value="database_administrators" required>
            <label class="form-check-label" for="role_admin">
              Database Administrators
            </label>
          </div>
          <div class="form-check">
            <input class="form-check-input" type="radio" name="role" id="role_owner" value="owner" required>
            <label class="form-check-label" for="role_owner">
              Owner
            </label>
          </div>
          <div class="form-check mb-3">
            <input class="form-check-input" type="radio" name="role" id="role_clerks" value="clerks" required>
            <label class="form-check-label" for="role_clerks">
              Clerk
            </label>
          </div>
          <div class="d-grid">
            <button type="submit" class="btn btn-primary">Register</button>
          </div>
        </form>
        <div class="mt-3 d-grid">
          <button type="button" class="btn btn-secondary" onclick="window.location.href='login.jsp';">
            Login
          </button>
        </div>
      </div>
    </div>
  </div>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
