<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>

<html>
<head>
    <meta charset="utf-8">
    <title>User Management</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta content="Responsive bootstrap 4 admin template" name="description">
    <meta content="Coderthemes" name="author">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
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
                <h1 class="text-center">User Management</h1>

                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-body table-responsive">
                                <a href="/admin?action=create" class="btn btn-primary font-weight-bold font-18"><i
                                        class="ion ion-md-add"></i>  New User</a>
                                <div id="datatable-buttons_wrapper" class="dataTables_wrapper dt-bootstrap4 no-footer">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <table id="datatable-buttons"
                                                   class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline"
                                                   style="border-collapse: collapse; border-spacing: 0px; width: 100%;"
                                                   role="grid" aria-describedby="datatable-buttons_info">

                                                <thead>
                                                <tr role="row">
                                                    <th class="sorting_asc" tabindex="0"
                                                        aria-controls="datatable-buttons" rowspan="1" colspan="1"
                                                        style="width: 179.2px;" aria-sort="ascending"
                                                        aria-label="Name: activate to sort column descending">Full Name
                                                    </th>
                                                    <th class="sorting_asc" tabindex="0"
                                                        aria-controls="datatable-buttons" rowspan="1" colspan="1"
                                                        style="width: 179.2px;" aria-sort="ascending"
                                                        aria-label="Name: activate to sort column descending">DoB
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
                                                        rowspan="1" colspan="1" style="width: 62.2px;"
                                                        aria-label="Age: activate to sort column ascending">Email
                                                    </th>
                                                    <th class="sorting" tabindex="0" aria-controls="datatable-buttons"
                                                        rowspan="1" colspan="1" style="width: 121.2px;"
                                                        aria-label="Start date: activate to sort column ascending">Create At

                                                    </th>
                                                    <th class="sorting" tabindex="0" aria-controls="datatable-buttons"
                                                        rowspan="1" colspan="1" style="width: 108px;"
                                                        aria-label="Salary: activate to sort column ascending">Role
                                                    </th>
                                                    <th>
                                                        Action
                                                    </th>
                                                </tr>
                                                </thead>

                                                <tbody>


<%--                                                <tr role="row">--%>
<%--                                                    <td tabindex="0" class="sorting_1">Airi--%>
<%--                                                        <Sa></Sa>--%>
<%--                                                        tou--%>
<%--                                                    </td>--%>
<%--                                                    <td>Accountant</td>--%>
<%--                                                    <td>33</td>--%>
<%--                                                    <td>2008/11/28</td>--%>
<%--                                                    <td>$162,700</td>--%>
<%--                                                    <td>Completed</td>--%>
<%--                                                    <td>--%>
<%--                                                        <a class="far fa-eye" href="/order?action=view&id="></a>--%>
<%--                                                        <a class="fas fa-edit" href="#"></a>--%>
<%--                                                    </td>--%>
<%--                                                </tr>--%>

                                                <c:forEach var="u" items="${users}">
                                                    <tr role="row">
                                                        <td tabindex="0" class="sorting_1">${u.name}</td>
                                                        <td>${u.dob}</td>
                                                        <td>${u.address}</td>
                                                        <td>${u.phone}</td>
                                                        <td>${u.email}</td>
                                                        <td>${u.createAt}</td>
                                                        <td>${u.eRole.name}</td>
                                                        <td>
                                                            <div class="d-flex justify-content-around">
<%--                                                                <a href="/admin?action=view&id=${u.id}" class="btn btn-primary btn-sm"><i--%>
<%--                                                                        class="bi bi-eye-fill"></i></a>--%>

                                                                <button type="button" class="btn btn-primary btn-sm " data-toggle="modal" data-target="#exampleModal${u.id}">
                                                                    <i class="bi bi-eye-fill"></i>
                                                                </button>

                                                                <!-- Modal -->
                                                                <div class="modal fade" id="exampleModal${u.id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                                    <div class="modal-dialog" role="document">
                                                                        <div class="modal-content">
                                                                            <div class="modal-header">
                                                                                <h5 class="modal-title" id="exampleModalLabel">User Detail</h5>
                                                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                                    <span aria-hidden="true">&times;</span>
                                                                                </button>
                                                                            </div>
                                                                            <div class="modal-body">
                                                                                Name: ${u.getName()}
                                                                            </div>
                                                                            <div class="modal-body">
                                                                                DoB: ${u.dob}
                                                                            </div>
                                                                            <div class="modal-body">
                                                                                Address: ${u.address}
                                                                            </div>
                                                                            <div class="modal-footer">
                                                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>

                                                                <a href="/admin?action=edit&id=${u.id}" class="btn btn-primary btn-sm"><i
                                                                        class="fa-solid fa-pen"></i></a>
                                                                <a type="button" onclick="handleDeleteUser(${u.getId()}, '${u.getName()}')" class="btn btn-lighten-danger btn-sm"><i
                                                                        class="fa-solid fa-trash-can"></i></a>

                                                            </div>
                                                        </td>
                                                    </tr>
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

        <!-- Button trigger modal -->

        <!-- Footer Start -->
        <jsp:include page="/WEB-INF/admin/layout/footer.jsp"></jsp:include>
        <!-- end Footer -->

    </div>

    <!-- ============================================================== -->
    <!-- End Page content -->
    <!-- ============================================================== -->

</div>
<!-- END wrapper -->
<form method="post" id="frmHiden" action="/admin?action=delete">
    <input type="hidden" id="txtIdDelete" name="idDelete"  />
</form>

<!-- Right Sidebar -->
<jsp:include page="/WEB-INF/admin/layout/right.jsp"></jsp:include>

<jsp:include page="/WEB-INF/admin/layout/footer_js.jsp"></jsp:include>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>

<script>
    function handleDeleteUser(id, name) {
        document.getElementById("txtIdDelete").value = id;

        const swalWithBootstrapButtons = Swal.mixin({
            customClass: {
                confirmButton: 'btn btn-success',
                cancelButton: 'btn btn-danger'
            },
            buttonsStyling: false
        })
        swalWithBootstrapButtons.fire({
            title: 'Delete product "' + name + '" ?',
            text: "Are you sure?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Yes, delete it!',
            cancelButtonText: 'No, cancel!',
            reverseButtons: true
        }).then((result) => {
            if (result.isConfirmed) {
                swalWithBootstrapButtons.fire(
                    'Deleted!',
                )
                setTimeout(function() {
                    document.getElementById("frmHiden").submit();
                }, 1000);
            } else if (
                /* Read more about handling dismissals below */
                result.dismiss === Swal.DismissReason.cancel
            ) {
                swalWithBootstrapButtons.fire(
                    'Cancelled',
                )
            }
        })
    }
</script>
</body>
<grammarly-desktop-integration data-grammarly-shadow-root="true"></grammarly-desktop-integration>
</html>
