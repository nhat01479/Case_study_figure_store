<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>

<html>
<head>
    <meta charset="utf-8">
    <title>Order Management</title>
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
            <div class="container-fluid">

                <!-- start page title -->

                <!-- end page title -->


                <!-- end row -->
                <h1 class="text-center">Order List</h1>
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-body table-responsive">
<%--                                <h1 class="m-t-0 header-title mb-4 text-center">Order List</h1>--%>

                                <div id="datatable-buttons_wrapper" class="dataTables_wrapper dt-bootstrap4 no-footer">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <table id="datatable-buttons"
                                                   class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline"
                                                   style="border-collapse: collapse; border-spacing: 0px; width: 100%;"
                                                   role="grid" aria-describedby="datatable-buttons_info">

                                                <thead>
                                                <tr role="row">
                                                    <th style="width: 20px;">
                                                        Id
                                                    </th>
                                                    <th class="sorting_asc" tabindex="0"
                                                        aria-controls="datatable-buttons" rowspan="1" colspan="1"
                                                        style="width: 179.2px;" aria-sort="ascending"
                                                        aria-label="Name: activate to sort column descending">Full Name
                                                    </th>
                                                    <th class="sorting" tabindex="0" aria-controls="datatable-buttons"
                                                        rowspan="1" colspan="1" style="width: 272.2px;"
                                                        aria-label="Position: activate to sort column ascending">
                                                        Address
                                                    </th>
                                                    <th class="sorting" tabindex="0" aria-controls="datatable-buttons"
                                                        rowspan="1" colspan="1" style="width: 62.2px;"
                                                        aria-label="Age: activate to sort column ascending">Phone
                                                    </th>
                                                    <th class="sorting" tabindex="0" aria-controls="datatable-buttons"
                                                        rowspan="1" colspan="1" style="width: 121.2px;"
                                                        aria-label="Start date: activate to sort column ascending">
                                                        Create At
                                                    </th>
                                                    <th class="sorting" tabindex="0" aria-controls="datatable-buttons"
                                                        rowspan="1" colspan="1" style="width: 108px;"
                                                        aria-label="Salary: activate to sort column ascending">Subtotal
                                                    </th>
                                                    <th class="sorting" tabindex="0" aria-controls="datatable-buttons"
                                                        rowspan="1" colspan="1" style="width: 126.2px;"
                                                        aria-label="Office: activate to sort column ascending">Status
                                                    </th>
                                                    <th style="width: 50px;">
                                                        Action
                                                    </th>
                                                </tr>
                                                </thead>

                                                <tbody>


                                                <c:forEach var="o" items="${requestScope.orders}">
                                                    <c:forEach var="u" items="${requestScope.users}">
                                                        <c:if test="${o.getIdUser() == u.getId() && o.getSubTotal() != 0}">
                                                            <tr role="row">
                                                                <td>${o.getId()}</td>
                                                                <td tabindex="0" class="sorting_1">${u.getName()}</td>
                                                                <td>${u.getAddress()}</td>
                                                                <td>${u.getPhone()}</td>
                                                                <td><fmt:formatDate value="${u.getCreateAt()}"
                                                                                    pattern="yyyy-MM-dd"/></td>
                                                                <td>$${o.getSubTotal()}</td>
                                                                <c:choose>
                                                                    <c:when test="${o.isPaid() == true}">
                                                                        <td>Completed</td>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <td>Processing</td>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                                <td>
                                                                    <a href="/order?action=view&id=${o.getId()}" class="btn btn-primary btn-sm"><i
                                                                            class="bi bi-eye-fill"></i></a>
                                                                    <c:if test="${o.isPaid() == false}">
                                                                        <a href="/order?action=edit&id=${o.getId()}" class="btn btn-primary btn-sm"><i
                                                                                class="fa-solid fa-pen"></i></a>
                                                                    </c:if>
                                                                </td>
                                                            </tr>
                                                        </c:if>
                                                    </c:forEach>
                                                </c:forEach>

                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- end row -->


                <!-- end row-->


                <!-- end row-->

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
<grammarly-desktop-integration data-grammarly-shadow-root="true"></grammarly-desktop-integration>
</html>
