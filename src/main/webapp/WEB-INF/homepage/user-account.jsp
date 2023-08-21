<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!doctype html>
<html class="no-js" lang="zxx">
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Coron-my account</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Favicon -->
    <link rel="shortcut icon" type="image/x-icon" href="assets\img\favicon.png">

    <!-- all css here -->
    <jsp:include page="/WEB-INF/homepage/layout/css_header.jsp"></jsp:include>
</head>
<body>
<!-- Add your site or application content here -->

<!--pos page start-->
<div class="pos_page">
    <div class="container">
        <!--pos page inner-->
        <div class="pos_page_inner">
            <!--header area -->
            <jsp:include page="/WEB-INF/homepage/layout/head.jsp"></jsp:include>
            <!--header end -->

            <!--breadcrumbs area start-->
            <div class="breadcrumbs_area">
                <div class="row">
                    <div class="col-12">
                        <div class="breadcrumb_content">
                            <ul>
                                <li><a href="/">home</a></li>
                                <li><a href="/product">home</a></li>
                                <li><i class="fa fa-angle-right"></i></li>
                                <li>my account</li>
                            </ul>

                        </div>
                    </div>
                </div>
            </div>
            <!--breadcrumbs area end-->

            <!-- Start Maincontent  -->
            <section class="main_content_area">
                <div class="account_dashboard">
                    <div class="row">
                        <div class="col-sm-12 col-md-3 col-lg-3">
                            <!-- Nav tabs -->
                            <div class="dashboard_tab_button">
                                <ul role="tablist" class="nav flex-column dashboard-list">
                                    <li><a href="#dashboard" data-toggle="tab" class="nav-link active">Dashboard</a></li>
                                    <li> <a href="#orders" data-toggle="tab" class="nav-link">Orders</a></li>
                                    <li><a href="#address" data-toggle="tab" class="nav-link">Change password</a></li>
                                    <li><a href="#account-details" data-toggle="tab" class="nav-link">Account details</a></li>
                                    <li><a href="/user?action=logout" class="nav-link">logout</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-sm-12 col-md-9 col-lg-9">
                            <!-- Tab panes -->
                            <div class="tab-content dashboard_content">
                                <div class="tab-pane fade show active" id="dashboard">
                                    <h3>Dashboard </h3>
                                    <p>From your account dashboard. you can easily check &amp; view your <a href="#">recent orders</a>, manage your <a href="#">shipping and billing addresses</a> and <a href="#">Edit your password and account details.</a></p>
                                </div>
                                <div class="tab-pane fade" id="orders">
                                    <h3>Orders</h3>
                                    <div class="coron_table table-responsive">
                                        <table class="table">
                                            <thead>
                                            <tr>
                                                <th>Order</th>
                                                <th>Date</th>
                                                <th>Status</th>
                                                <th>Total</th>
                                                <th>Actions</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach var="o" items="${requestScope.orders}">
                                                <tr>
                                                    <td>${o.getId()}</td>

                                                    <td><fmt:formatDate value="${o.getCreateAt()}" pattern="yyyy-MM-dd"/></td>
                                                    <td><span class="success">
                                                        <c:choose>
                                                            <c:when test="${o.isPaid() == true}">
                                                                Completed
                                                            </c:when>
                                                            <c:otherwise>
                                                                Processing
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </span></td>
                                                    <td>$${o.getSubTotal()} </td>
                                                    <td>
                                                        <c:choose>
                                                            <c:when test="${o.isPaid() == false}">
                                                                <a href="/cart" class="view">view</a>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <form action="/cart?action=view" method="post">
                                                                    <input hidden="hidden" name="idOrder" value="${o.getId()}">
                                                                    <button class="btn btn-bordered-primary">View</button>
                                                                </form>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <div class="tab-pane" id="address">
                                    <form action="user?action=changePass" method="post" class="p-2">
                                        <c:if test="${sessionScope.errorsP != null}">
                                            <div class="alert alert-danger">
                                                <ul>
                                                    <c:forEach items="${sessionScope.errorsP}" var="e">
                                                        <li>${e}</li>
                                                    </c:forEach>
                                                </ul>
                                            </div>
                                        </c:if>

                                        <div class="form-group mb-3 col-md-4">
                                            <label for="password">Password :</label>
                                            <input name="password" class="form-control" type="password" required="" id="password" placeholder="Enter your password">
                                        </div>

                                        <div class="form-group mb-3 col-md-4">
                                            <label for="password">Confirm Password :</label>
                                            <input name="confirm-password" class="form-control" type="password" required="" id="confirm-password" placeholder="Enter your password">
                                        </div>

                                        <div class="form-group row text-center mt-4 mb-4">
                                            <div class="col-md-4">
                                                <button class="btn btn-md btn-block btn-primary waves-effect waves-light" type="submit">Change</button>
                                            </div>
                                        </div>
                                        <c:if test="${sessionScope.messageP != null}">
                                            <div class="alert alert-success">
                                                <strong>${sessionScope.messageP}</strong>
                                            </div>
                                        </c:if>

                                    </form>
                                </div>
                                <div class="tab-pane fade" id="account-details">
                                    <h3>Account details </h3>
                                    <div class="login">
                                        <div class="login_form_container">
                                            <div class="account_login_form">
                                                <form action="user?action=updateInfo" method="post">
                                                    <c:if test="${sessionScope.errors != null}">
                                                        <div class="alert alert-danger">
                                                            <ul>
                                                                <c:forEach items="${sessionScope.errors}" var="e">
                                                                    <li>${e}</li>
                                                                </c:forEach>
                                                            </ul>
                                                        </div>
                                                    </c:if>
                                                    <label class="font-weight-bold">Full Name</label>
                                                    <input type="text" name="name" value="${sessionScope.user.name}">
                                                    <label class="font-weight-bold">Address</label>
                                                    <input type="text" name="address" value="${sessionScope.user.address}">
                                                    <label class="font-weight-bold">Phone</label>
                                                    <input type="text" name="phone" value="${sessionScope.user.phone}">
                                                    <label class="font-weight-bold">Email</label>
                                                    <input type="text" name="email" value="${sessionScope.user.email}">
                                                    <label class="font-weight-bold">Birthdate</label>
                                                    <input type="text" placeholder="YYYY/MM/DD" name="birthday" value="<fmt:formatDate value="${sessionScope.user.dob}" pattern="yyyy-MM-dd"/>">
                                                    <div class="col-3">
                                                        <button class="btn btn-bordered-primary" type="submit">Save</button>
                                                    </div>
                                                </form>
                                                <c:if test="${sessionScope.message != null}">
                                                    <div class="alert alert-success col-md-5 float-md-right">
                                                        <strong>${sessionScope.message}</strong>
                                                    </div>
                                                </c:if>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <!-- End Maincontent  -->
        </div>
        <!--pos page inner end-->
    </div>
</div>
<!--pos page end-->

<!--footer area start-->
<jsp:include page="/WEB-INF/homepage/layout/foot.jsp"></jsp:include>
<!--footer area end-->
<script>
    function getParameterByName(name, url) {
        if (!url) url = window.location.href;
        name = name.replace(/[[\]]/g, '\\$&');
        var regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'),
            results = regex.exec(url);
        if (!results) return null;
        if (!results[2]) return '';
        return decodeURIComponent(results[2].replace(/\+/g, ' '));
    }

    // Lắng nghe sự kiện tải trang
    window.onload = function() {
        var scrollToParam = getParameterByName('scrollTo');
        if (scrollToParam === 'address') {
            var accountDetailsLink = document.querySelector('a[href="#address"]');
            if (accountDetailsLink) {
                accountDetailsLink.click(); // Tự động click vào liên kết "Address"
                var accountDetailsElement = document.getElementById("address");
                if (accountDetailsElement) {
                    accountDetailsElement.scrollIntoView(); // Cuộn xuống phần tử "#address"
                }
            }
        } else if (scrollToParam === 'account-details') {
            var accountDetailsLink = document.querySelector('a[href="#account-details"]');
            if (accountDetailsLink) {
                accountDetailsLink.click(); // Tự động click vào liên kết "Account details"
                var accountDetailsElement = document.getElementById("account-details");
                if (accountDetailsElement) {
                    accountDetailsElement.scrollIntoView(); // Cuộn xuống phần tử "#account-details"
                }
            }
        }
    };
    // window.onload = function() {
    //     var scrollToParam = getParameterByName('scrollTo');
    //     if (scrollToParam === 'address') {
    //         var accountDetailsLink = document.querySelector('a[href="#address"]');
    //         if (accountDetailsLink) {
    //             accountDetailsLink.click();
    //             var accountDetailsElement = document.getElementById("address");
    //             if (accountDetailsElement) {
    //                 accountDetailsElement.scrollIntoView();
    //             }
    //         }
    //     }
    // };
    // window.onload = function() {
    //     var scrollToParam = getParameterByName('scrollTo');
    //     if (scrollToParam === 'account-details') {
    //         var accountDetailsLink = document.querySelector('a[href="#account-details"]');
    //         if (accountDetailsLink) {
    //             accountDetailsLink.click(); // Tự động click vào liên kết "Account details"
    //             var accountDetailsElement = document.getElementById("account-details");
    //             if (accountDetailsElement) {
    //                 accountDetailsElement.scrollIntoView(); // Cuộn xuống phần tử "#account-details"
    //             }
    //         }
    //     }
    // };
</script>



<!-- all js here -->
<jsp:include page="/WEB-INF/homepage/layout/js_footer.jsp"></jsp:include>
</body>
</html>
