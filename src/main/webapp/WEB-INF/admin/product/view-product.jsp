<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html class="no-js" lang="zxx">
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Figure-single product</title>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <jsp:include page="/WEB-INF/homepage/layout/css_header.jsp"/>
    <meta name="description" content="">
    <style>
        p.badge{
            font-size: 100%;
        }
    </style>
</head>
<body>
<!-- Add your site or application content here -->
<div class="pos_page">
    <div class="container">
        <!--pos page inner-->
        <div class="pos_page_inner">
            <!--header area -->
            <jsp:include page="/WEB-INF/homepage/layout/head.jsp"/>
            <!--header end -->
            <!--breadcrumbs area start-->
            <div class="breadcrumbs_area">
                <div class="row">
                    <div class="col-12">
                        <div class="breadcrumb_content">
                            <ul>
                                <li><a href="/">home</a></li>
                                <li><i class="fa fa-angle-right"></i></li>
                                <li>single product</li>
                            </ul>

                        </div>
                    </div>
                </div>
            </div>
            <!--breadcrumbs area end-->


            <!--product wrapper start-->
            <div class="product_details">
                <div class="row">
                    <div class="col-lg-5 col-md-6">
                        <div class="product_tab fix">
                            <div class="tab-content produc_tab_c">
                                <div class="tab-pane fade show active" id="p_tab1" role="tabpanel">
                                    <div class="modal_img">
                                        <a href="#"><img src="${product.getImgLink()}" alt=""></a>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                    <div class="col-lg-7 col-md-6">
                        <div class="product_d_right">
                            <h1>${product.getName()}</h1>
                            <div class="product_ratting mb-10">
                                <ul>
                                    <li><a href="#"><i class="fa fa-star"></i></a></li>
                                    <li><a href="#"><i class="fa fa-star"></i></a></li>
                                    <li><a href="#"><i class="fa fa-star"></i></a></li>
                                    <li><a href="#"><i class="fa fa-star"></i></a></li>
                                    <li><a href="#"><i class="fa fa-star"></i></a></li>
                                    <li><a href="#"> Write a review </a></li>
                                </ul>
                            </div>
                            <div class="product_desc">
                                <p>${product.getDescription()}</p>
                            </div>

                            <div class="content_price mb-15">
                                <span>$${product.getPrice()}</span>
                                <span class="old-price">$${product.getPrice()+20}</span>
                            </div>

                            <c:if test="${adMess == null}">
                                <div class="box_quantity mb-20">
                                    <form action="/cart?action=add" method="get">
                                        <label>quantity</label>
                                        <input name="action" value="add" hidden="">
                                        <input name="id" value="${product.getId()}" hidden="">
                                        <input name="doFrom" value="viewProduct" hidden="">
                                        <input name="quantity" min="1" max="10" value="1" type="number">
                                        <button type="submit"><i class="fa fa-shopping-cart"></i> add to cart</button>
                                        <c:if test="${cartMess != null}">
                                            <p style="color: red">${cartMess}</p>
                                        </c:if>
                                    </form>
                                </div>
                            </c:if>

                            <div class="product_stock mb-20">
                                <span> Studio </span>
                                <p class="badge badge-info ml-2">${product.geteStudio().getName()}</p>
                            </div>

                            <div class="product_stock mb-20">
                                <span> Scale </span>
                                <p class="badge badge-info ml-2">${product.geteScale().getScale()}</p>
                            </div>

                            <div class="product_stock mb-20">
                                <span> In stock </span>
                                <p class="badge badge-info ml-2">${product.getLeftQuantity()} items</p>
                            </div>

                            <div class="product_stock mb-20">
                                <span> Category </span>
                                <c:forEach var="c" items="${categoryMap.keySet()}">
                                    <c:if test="${product.getIdCategory() == c}">
                                        <p class="badge badge-info ml-2">${categoryMap.get(c).getName()}</p>
                                    </c:if>
                                </c:forEach>
                            </div>
                            <div class="wishlist-share">
                                <h4>Share on:</h4>
                                <ul>
                                    <li><a href="#"><i class="fa fa-rss"></i></a></li>
                                    <li><a href="#"><i class="fa fa-vimeo"></i></a></li>
                                    <li><a href="#"><i class="fa fa-tumblr"></i></a></li>
                                    <li><a href="#"><i class="fa fa-pinterest"></i></a></li>
                                    <li><a href="#"><i class="fa fa-linkedin"></i></a></li>
                                </ul>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
            <!--product details end-->


            <!--product info start-->
            <!--product info end-->


            <!--new product area start-->

            <!--new product area start-->


            <!--new product area start-->

            <!--new product area start-->

        </div>
        <!--pos page inner end-->
    </div>
</div>
<!--pos page end-->

<!--footer area start-->
<jsp:include page="/WEB-INF/homepage/layout/foot.jsp"/>
<!--footer area end-->


<!-- modal area start -->
<div class="modal fade" id="modal_box" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            <div class="modal_body">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-5 col-md-5 col-sm-12">
                            <div class="modal_tab">
                                <div class="tab-content" id="pills-tabContent">
                                    <div class="tab-pane fade show active" id="tab1" role="tabpanel">
                                        <div class="modal_tab_img">
                                            <a href="#"><img src="assets\img\product\product13.jpg" alt=""></a>
                                        </div>
                                    </div>
                                    <div class="tab-pane fade" id="tab2" role="tabpanel">
                                        <div class="modal_tab_img">
                                            <a href="#"><img src="assets\img\product\product14.jpg" alt=""></a>
                                        </div>
                                    </div>
                                    <div class="tab-pane fade" id="tab3" role="tabpanel">
                                        <div class="modal_tab_img">
                                            <a href="#"><img src="assets\img\product\product15.jpg" alt=""></a>
                                        </div>
                                    </div>
                                </div>
                                <div class="modal_tab_button">
                                    <ul class="nav product_navactive" role="tablist">
                                        <li>
                                            <a class="nav-link active" data-toggle="tab" href="#tab1" role="tab" aria-controls="tab1" aria-selected="false"><img src="assets\img\cart\cart17.jpg" alt=""></a>
                                        </li>
                                        <li>
                                            <a class="nav-link" data-toggle="tab" href="#tab2" role="tab" aria-controls="tab2" aria-selected="false"><img src="assets\img\cart\cart18.jpg" alt=""></a>
                                        </li>
                                        <li>
                                            <a class="nav-link button_three" data-toggle="tab" href="#tab3" role="tab" aria-controls="tab3" aria-selected="false"><img src="assets\img\cart\cart19.jpg" alt=""></a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-7 col-md-7 col-sm-12">
                            <div class="modal_right">
                                <div class="modal_title mb-10">
                                    <h2>Handbag feugiat</h2>
                                </div>
                                <div class="modal_price mb-10">
                                    <span class="new_price">$64.99</span>
                                    <span class="old_price">$78.99</span>
                                </div>
                                <div class="modal_content mb-10">
                                    <p>Short-sleeved blouse with feminine draped sleeve detail.</p>
                                </div>
                                <div class="modal_size mb-15">
                                    <h2>size</h2>
                                    <ul>
                                        <li><a href="#">s</a></li>
                                        <li><a href="#">m</a></li>
                                        <li><a href="#">l</a></li>
                                        <li><a href="#">xl</a></li>
                                        <li><a href="#">xxl</a></li>
                                    </ul>
                                </div>
                                <div class="modal_add_to_cart mb-15">
                                    <form action="#">
                                        <input min="0" max="100" step="2" value="1" type="number">
                                        <button type="submit">add to cart</button>
                                    </form>
                                </div>
                                <div class="modal_description mb-15">
                                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,</p>
                                </div>
                                <div class="modal_social">
                                    <h2>Share this product</h2>
                                    <ul>
                                        <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                                        <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                                        <li><a href="#"><i class="fa fa-pinterest"></i></a></li>
                                        <li><a href="#"><i class="fa fa-google-plus"></i></a></li>
                                        <li><a href="#"><i class="fa fa-linkedin"></i></a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- modal area end -->



<!-- all js here -->
<jsp:include page="/WEB-INF/homepage/layout/js_footer.jsp"/>
</body>
</html>