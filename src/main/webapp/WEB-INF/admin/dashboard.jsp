<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Admin Dashboard</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta content="Responsive bootstrap 4 admin template" name="description">
    <meta content="Coderthemes" name="author">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <jsp:include page="/WEB-INF/admin/layout/header_css.jsp"></jsp:include>

</head>

<body>

<!-- Begin page -->
<div id="wrapper">


    <!-- Topbar Start -->
    <jsp:include page="/WEB-INF/admin/layout/head.jsp"></jsp:include>
    <!-- end Topbar --> <!-- ========== Left Sidebar Start ========== -->
    <jsp:include page="/WEB-INF/admin/layout/left.jsp"></jsp:include>
    <!-- Left Sidebar End -->

    <!-- ============================================================== -->
    <!-- Start Page Content here -->
    <!-- ============================================================== -->

    <div class="content-page">
        <div class="content">

            <!-- Start Content-->
            <div class="row">
                <div class="col-xl-3 col-sm-6">
                    <div class="card widget-style-1 bg-pink">
                        <div class="card-body">
                            <div class="my-4 text-white">
                                <i class="mdi mdi-comment-multiple"></i>
                                <h2 class="my-0 font-weight-bold text-white"><span data-plugin="counterup">${requestScope.orders.size()}</span></h2>
                                <div>Orders</div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-xl-3 col-sm-6">
                    <div class="card widget-style-1 bg-warning">
                        <div class="card-body">
                            <div class="my-4 text-white">
                                <i class="mdi mdi-cart"></i>
                                <h2 class="my-0 text-white"><span data-plugin="counterup">${requestScope.products.size()}</span></h2>
                                <div>Products</div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-xl-3 col-sm-6">
                    <div class="card widget-style-1 bg-info">
                        <div class="card-body">
                            <div class="my-4 text-white">
                                <i class="mdi mdi-cash"></i>
                                <h2 class="my-0 text-white"><span data-plugin="counterup">${requestScope.totalIncome}</span><span>$</span></h2>
                                <div>Income</div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-xl-3 col-sm-6">
                    <div class="card widget-style-1 bg-success">
                        <div class="card-body">
                            <div class="my-4 text-white">
                                <i class="mdi mdi-account-multiple"></i>
                                <h2 class="my-0 text-white"><span data-plugin="counterup">${requestScope.users.size()}</span></h2>
                                <div>Users</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- end container-fluid -->

        </div>
        <!-- end content -->



        <!-- Footer Start -->
        <jsp:include page="/WEB-INF/admin/layout/footer.jsp"></jsp:include>
        <!-- end Footer -->

    </div>

    <!-- ============================================================== -->
    <!-- End Page content -->
    <!-- ============================================================== -->

</div>
<!-- END wrapper -->


<!-- Right Sidebar -->
<jsp:include page="/WEB-INF/admin/layout/right.jsp"></jsp:include>

<jsp:include page="/WEB-INF/admin/layout/footer_js.jsp"></jsp:include>

</body>

</html>
