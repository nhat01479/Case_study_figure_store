<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Product Management</title>
<%--    <jsp:include page="/layout/head_css.jsp"></jsp:include>--%>

    <jsp:include page="/WEB-INF/admin/layout/header_css.jsp"></jsp:include>

</head>
<body>
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
                <h1 class="text-center">Product Management</h1>
                <div>
                     <a href="/product-manager?action=create" class="btn btn-success m-3"><i class="fa-solid fa-plus"></i> New Product</a>
                 </div>
                <!-- end row -->

                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-body table-responsive">
                                <h4 class="m-t-0 header-title mb-4"><b></b></h4>

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
                                                        style="width: 20px;" aria-sort="ascending"
                                                        aria-label="Name: activate to sort column descending">
                                                        ID
                                                    </th>
                                                    <th class="sorting" tabindex="0" aria-controls="datatable-buttons"
                                                        rowspan="1" colspan="1" style="width: 272.2px;"
                                                        aria-label="Position: activate to sort column ascending">
                                                        Name
                                                    </th>
                                                    <th class="sorting" tabindex="0" aria-controls="datatable-buttons"
                                                        rowspan="1" colspan="1" style="width: 272.2px;"
                                                        aria-label="Position: activate to sort column ascending">
                                                        Price
                                                    </th>
                                                    <th class="sorting" tabindex="0" aria-controls="datatable-buttons"
                                                        rowspan="1" colspan="1" style="width: 30px;"
                                                        aria-label="Age: activate to sort column ascending">Quantity
                                                    </th>
                                                    <th class="sorting" tabindex="0" aria-controls="datatable-buttons"
                                                        rowspan="1" colspan="1" style="width: 121.2px;"
                                                        aria-label="Start date: activate to sort column ascending">
                                                        Scale
                                                    </th>
                                                    <th class="sorting" tabindex="0" aria-controls="datatable-buttons"
                                                        rowspan="1" colspan="1" style="width: 108px;"
                                                        aria-label="Salary: activate to sort column ascending">Category
                                                    </th>
                                                    <th class="sorting" tabindex="0" aria-controls="datatable-buttons"
                                                        rowspan="1" colspan="1" style="width: 126.2px;"
                                                        aria-label="Office: activate to sort column ascending">Studio
                                                    </th>
                                                    <th style="width: 60px;">
                                                        Action
                                                    </th>
                                                </tr>
                                                </thead>

                                                <tbody>


                                                <c:forEach items='${requestScope.products}' var="p">
                                                <tr>
                                                    <td>${p.getId()}</td>
                                                    <td>${p.getName()}</td>
                                                    <td>$${p.getPrice()}</td>
                                                    <td>${p.getLeftQuantity()}</td>
                                                    <td>${p.geteScale().getScale()}</td>
                                                    <td>
                                                        <c:forEach var="c" items="${categoryMap.keySet()}">
                                                            <c:if test="${p.getIdCategory() == c}">
                                                                ${categoryMap.get(c).getName()}
                                                            </c:if>
                                                        </c:forEach>
                                                    </td>
                                                    <td>${p.geteStudio().getName()}</td>
                                                    <td>
                                                        <div class="d-flex justify-content-around">
                                                            <a href="/product?action=view&id=${p.getId()}&mess=adView" class="btn btn-primary btn-sm"><i
                                                                    class="bi bi-eye-fill"></i></a>
                                                            <a href="/product-manager?action=edit&id=${p.getId()}" class="btn btn-primary btn-sm"><i
                                                                    class="fa-solid fa-pen"></i></a>
                                                            <a onclick="handleDeleteProduct(${p.getId()}, '${p.getName()}')" class="btn btn-lighten-danger btn-sm"><i
                                                                    class="fa-solid fa-trash-can"></i></a>
                                                        </div>
                                                    </td>
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
    <form method="post" id="frmHiden" action="/product-manager?action=delete">
        <input type="hidden" id="txtIdDelete" name="idDelete"  />
    </form>

</div>
<jsp:include page="/WEB-INF/admin/layout/right.jsp"></jsp:include>
<jsp:include page="/WEB-INF/admin/layout/footer.jsp"></jsp:include>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<script>
    function handleDeleteProduct(id, name) {
        document.getElementById("txtIdDelete").value = id;
        // let cf = confirm("Bạn chắc chắn muốn xoá " + name);
        // if(cf){
        //     document.getElementById("frmHiden").submit();
        // }

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
<jsp:include page="/WEB-INF/admin/layout/footer_js.jsp"></jsp:include>
</body>
</html>
</html>
