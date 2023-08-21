<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>

<html>
<head>
    <meta charset="utf-8">
    <title>Data Table | Velonic - Responsive Bootstrap 4 Admin Dashboard</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta content="Responsive bootstrap 4 admin template" name="description">
    <meta content="Coderthemes" name="author">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <jsp:include page="/WEB-INF/admin/layout/header_css.jsp"></jsp:include>
    <jsp:include page="/WEB-INF/homepage/layout/css_header.jsp"></jsp:include>

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

                <div class="row">
                    <div class="col-12">
                        <div class="table_desc">
                            <div class="cart_page table-responsive">
                                <table>
                                    <thead>
                                    <tr>
                                        <th class="product_remove">Delete</th>
                                        <th class="product_thumb">Image</th>
                                        <th class="product_name">Name</th>
                                        <th class="product-price">Price</th>
                                        <th class="product_quantity">Quantity</th>
                                        <th class="product_total">Total</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${requestScope.order.getOrderItems()}" var="oT">
                                        <c:forEach items="${requestScope.products}" var="p">
                                            <c:if test="${p.getId() == oT.getIdProduct()}">

                                                <tr>
                                                    <form action="/order?action=edit" method="post">
                                                        <input name="idOrder" value="${requestScope.order.getId()}" hidden="hidden">
                                                        <input name="idProduct" value="${oT.getIdProduct()}" hidden="hidden">
                                                        <td class="product_remove"><button class="btn btn-danger rounded-lg fa fa-trash-o" type="submit"></button></td>
                                                        <td class="product_thumb" ><a href="#"><img style="width: 80px !important;" src="${p.getImgLink()}" alt=""></a></td>
                                                        <td class="product_name"><a href="#">${p.getName()}</a></td>
                                                        <td class="product-price">${p.getPrice()}</td>
                                                        <td class="product_quantity" ><input onchange="handleQuantityChange(${p.getId()},this.value,${requestScope.order.getId()})" min="0" max="10" value="${oT.getQuantity()}" type="number" name="quantity" ></td>
                                                        <td class="product_total">${p.getPrice() * oT.getQuantity()}</td>
                                                    </form>
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

<!-- Vendor js -->
<jsp:include page="/WEB-INF/admin/layout/footer_js.jsp"></jsp:include>
<jsp:include page="/WEB-INF/homepage/layout/js_footer.jsp"></jsp:include>
<script>
    function handleQuantityChange(idProduct, quantity, idOrder){
        let url = "/order?action=change&id="+idProduct+"&quantity="+quantity+"&idOrder="+idOrder;
        window.location.assign(url);
    }
</script>

</body>
<grammarly-desktop-integration data-grammarly-shadow-root="true"></grammarly-desktop-integration>
</html>
