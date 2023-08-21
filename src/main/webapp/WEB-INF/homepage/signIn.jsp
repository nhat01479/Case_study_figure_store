<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Login page | Velonic - Responsive Bootstrap 4 Admin Dashboard</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta content="Responsive bootstrap 4 admin template" name="description">
    <meta content="Coderthemes" name="author">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- App favicon -->
    <link rel="shortcut icon" href="/admin_frontend/assets\images\favicon.ico">

    <!-- App css -->
    <link href="/admin_frontend/assets\css\bootstrap.min.css" rel="stylesheet" type="text/css" id="bootstrap-stylesheet">
    <link href="/admin_frontend/assets\css\icons.min.css" rel="stylesheet" type="text/css">
    <link href="/admin_frontend/assets\css\app.min.css" rel="stylesheet" type="text/css" id="app-stylesheet">

</head>

<body class="authentication-page">

<div class="account-pages my-5">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8 col-lg-6 col-xl-5">
                <div class="card mt-4">
                    <div class="card-header p-4 bg-primary">
                        <h4 class="text-white text-center mb-0 mt-0">Figure Store</h4>
                    </div>
                    <div class="card-body">

                        <form class="p-2" method="post">
                            <c:if test="${requestScope.errors != null}">
                                <div class="alert alert-danger">
                                    <ul>
                                        <c:forEach items="${requestScope.errors}" var="e">
                                            <li>${e}</li>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </c:if>
                            <div class="form-group mb-3">
                                <label for="emailaddress">Email Address :</label>
                                <input name="email" class="form-control" type="email" value="${cookie.email.value}" id="emailaddress" required="" placeholder="john@deo.com">
                            </div>

                            <div class="form-group mb-3">
                                <label for="password">Password :</label>
                                <input name="password" class="form-control" type="password" value="${cookie.pass.value}" required="" id="password" placeholder="Enter your password">
                            </div>

                            <div class="form-group mb-4">
                                <div class="checkbox checkbox-success">
                                    <input ${(cookie.rem.value eq 'ON')?"checked":""} name="remember" value="ON" id="remember" type="checkbox">
                                    <label for="remember">
                                        Remember me
                                    </label>

                                </div>
                            </div>

                            <div class="form-group row text-center mt-4 mb-4">
                                <div class="col-12">
                                    <button class="btn btn-md btn-block btn-primary waves-effect waves-light" type="submit">Sign In</button>
                                </div>
                            </div>

                            <div class="form-group row mb-0">
                                <div class="col-sm-12 text-center">
                                    <p class="text-muted mb-0">Don't have an account? <a href="/user?action=signup" class="text-dark m-l-5"><b>Sign Up</b></a></p>
                                </div>
                            </div>
                        </form>

                    </div>
                    <!-- end card-body -->
                </div>
                <!-- end card -->

                <!-- end row -->

            </div>
            <!-- end col -->
        </div>
        <!-- end row -->

    </div>
</div>

<!-- Vendor js -->
<script src="/admin_frontend/assets\js\vendor.min.js"></script>

<!-- App js -->
<script src="/admin_frontend/assets\js\app.min.js"></script>

</body>

</html>