<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html class="no-js" lang="zxx">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <title>Figure Store-checkout</title>
    <meta name="description" content=""/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <jsp:include page="/WEB-INF/homepage/layout/css_header.jsp"></jsp:include>
</head>
<body>
<div class="row d-flex justify-content-center">
    <c:if test="${requestScope.successMess != null}">
        <script>
            window.onload = function (){
                Swal.fire({
                    position: 'center',
                    icon: 'success',
                    title: 'thanh toán thành công!',
                    showConfirmButton: false,
                    timer: 1500
                })
            }
        </script>
    </c:if>
</div>
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
                                <li><i class="fa fa-angle-right"></i></li>
                                <li>checkout</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <!--breadcrumbs area end-->

            <!--Checkout page section-->
            <div class="Checkout_section">
                <div class="checkout_form">
                    <form action="/cart?action=check" method="post">
                        <div class="row">
                            <div class="col-lg-6 col-md-6">
                                    <h3>Billing Details</h3>
                                    <div class="row">
                                        <div class="col-12 mb-30">
                                            <label>Full Name <span>*</span></label>
                                            <input type="text" name="fullName" value="" />
                                            <c:if test="${requestScope.errorsMap.containsKey('nameInvalid')}">
                                                <p class="alert alert-danger">${requestScope.errorsMap.get('nameInvalid')}</p>
                                            </c:if>
                                        </div>
                                        <div class="col-12 mb-30">
                                            <label>Address <span>*</span></label>
                                            <input type="text" name="address" value="" />
                                            <c:if test="${requestScope.errorsMap.containsKey('addressInvalid')}">
                                                <p class="alert alert-danger">${requestScope.errorsMap.get('addressInvalid')}</p>
                                            </c:if>
                                        </div>
                                        <div class="col-lg-6 mb-30">
                                            <label>Phone<span>*</span></label>
                                            <input type="text" name="phone" value="" />
                                            <c:if test="${requestScope.errorsMap.containsKey('phoneInvalid')}">
                                                <p class="alert alert-danger">${requestScope.errorsMap.get('phoneInvalid')}</p>
                                            </c:if>
                                        </div>
                                        <div class="col-lg-6 mb-30">
                                            <label> Email Address <span>*</span></label>
                                            <input type="text" name="email" value="" />
                                            <c:if test="${requestScope.errorsMap.containsKey('emailInvalid')}">
                                                <p class="alert alert-danger">${requestScope.errorsMap.get('emailInvalid')}</p>
                                            </c:if>
                                        </div>

                                        <div class="col-12">
                                            <div class="order-notes">
                                                <label for="order_note">Order Notes</label>
                                                <textarea
                                                        id="order_note"
                                                        placeholder="Notes about your order, e.g. special notes for delivery."
                                                ></textarea>
                                            </div>
                                        </div>
                                    </div>
                            </div>
                            <div class="col-lg-6 col-md-6">
                                    <h3>Your order</h3>
                                    <div class="order_table table-responsive mb-30">
                                        <table>
                                            <thead>
                                            <tr>
                                                <th>Product</th>
                                                <th>Total</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${requestScope.order.getOrderItems()}" var="oT">
                                                <c:forEach items="${requestScope.products}" var="p">
                                                    <c:if test="${p.getId() == oT.getIdProduct()}">
                                                        <tr>
                                                            <td>${p.getName()} <strong> x ${oT.getQuantity()}</strong></td>
                                                            <td>$${p.getPrice() * oT.getQuantity()}</td>
                                                        </tr>
                                                    </c:if>
                                                </c:forEach>
                                            </c:forEach>
                                            </tbody>
                                            <tfoot>
                                            <tr>
                                                <th>Cart Subtotal</th>
                                                <td>$${requestScope.order.getSubTotal()}</td>
                                            </tr>
                                            <tr>
                                                <th>Discount</th>
                                                <td>${requestScope.order.getDiscount()}%</td>
                                            </tr>
                                            <tr>
                                                <th>Shipping</th>
                                                <td><strong>$10.00</strong></td>
                                            </tr>
                                            <tr class="order_total">
                                                <th>Order Total</th>
                                                <td><strong>$${requestScope.order.getSubTotal() - (requestScope.order.getSubTotal() * requestScope.order.getDiscount() / 100) + 10}</strong></td>
                                            </tr>
                                            </tfoot>
                                        </table>
                                    </div>
                                    <div class="payment_method">
                                        <div class="panel-default">
                                            <input
                                                    id="payment"
                                                    type="radio"
                                                    name="payment_method"
                                                    data-target="createp_account"
                                            />
                                            <label
                                                    for="payment"
                                                    data-toggle="collapse"
                                                    data-target="#method"
                                                    aria-controls="method"
                                                    aria-expanded="true"
                                                    class=""
                                            >COD</label
                                            >
                                        </div>
                                        <div class="panel-default">
                                            <input
                                                    id="payment_defult"
                                                    type="radio"
                                                    name="payment_method"
                                                    data-target="createp_account"
                                            />
                                            <label
                                                    for="payment_defult"
                                                    data-toggle="collapse"
                                                    data-target="#collapsedefult"
                                                    aria-controls="collapsedefult"
                                            >PayPal <img src="/homepage_frontend/assets\img\visha\papyel.png" alt=""
                                            /></label>

                                            <div
                                                    id="collapsedefult"
                                                    class="collapse one"
                                                    data-parent="#accordion"
                                            >
                                                <div class="card-body1">
                                                    <p>
                                                        Pay via PayPal; you can pay with your credit card if you don't have a PayPal account.
                                                    </p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="order_button">
                                            <button type="submit">Proceed to check-out</button>
                                        </div>
                                    </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <!--Checkout page section end-->
        </div>
        <!--pos page inner end-->
    </div>
</div>
<!--pos page end-->

<!--footer area start-->
<jsp:include page="/WEB-INF/homepage/layout/foot.jsp"></jsp:include>
<!--footer area end-->

<!-- all js here -->
<jsp:include page="/WEB-INF/homepage/layout/js_footer.jsp"></jsp:include>
<a
        id="scrollUp"
        href="#top"
        style="position: fixed; z-index: 2147483647; display: none"
><i class="fa fa-angle-double-up"></i
></a>
</body>
</html>
