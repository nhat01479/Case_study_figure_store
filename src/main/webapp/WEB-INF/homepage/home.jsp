<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html class="no-js" lang="zxx">
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Figure-shop</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <jsp:include page="/WEB-INF/homepage/layout/css_header.jsp"/>
    <style>
        .product_thumb{
            height: 340px !important;
        }
    </style>
</head>
<body>
<!-- Add your site or application content here -->

<!--pos page start-->
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
                                <li>shop</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <!--breadcrumbs area end-->

            <!--pos home section-->
            <div class=" pos_home_section">
                <div class="row pos_home">
                    <div class="col-lg-3 col-md-12">
                        <!--layere categorie"-->
                        <div class="d-flex align-items-start flex-column">
                            <div class="sidebar_widget shop_c">
                                <h4>Categories</h4>
                                <select name="idCategory" id="cate" onchange="handleChange(${requestScope.pageable.getPage()},${requestScope.pageable.getLimit()},'${requestScope.pageable.getKw()}','${requestScope.pageable.getSortField()}','${requestScope.pageable.getOrder()}',this.value,'${requestScope.pageable.getScale()}')">
                                    <option <c:if test="${requestScope.pageable.getIdCategory() == -1}">selected</c:if> value="-1">All</option>
                                    <c:forEach var="c" items="${requestScope.categoryMap.keySet()}">
                                        <option <c:if test="${c == requestScope.pageable.getIdCategory()}">selected</c:if> value="${c}">${requestScope.categoryMap.get(c).getName()}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="sidebar_widget shop_c">
                                <h4>Scale</h4>
                                <select name="scale" id="scale" onchange="handleChange(${requestScope.pageable.getPage()},${requestScope.pageable.getLimit()},'${requestScope.pageable.getKw()}','${requestScope.pageable.getSortField()}','${requestScope.pageable.getOrder()}',${requestScope.pageable.getIdCategory()},this.value)">
                                    <option <c:if test="${requestScope.pageable.getScale().equals('all')}">selected</c:if> value="all">All</option>
                                    <c:forEach var="sc" items="${requestScope.scales}">
                                        <option <c:if test="${sc.getScale().equals(requestScope.pageable.getScale())}">selected</c:if> value="${sc.getScale()}">${sc.getScale()}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <!--color area end-->

                        <!--price slider start-->
                        <div class="sidebar_widget price">
                            <h2>Price</h2>
                            <div class="ca_search_filters">

                                <input type="text" name="text" id="amount">
                                <div id="slider-range"></div>
                            </div>
                        </div>
                        <!--price slider end-->

                        <!--wishlist block start-->
                        <!--wishlist block end-->

                        <!--popular tags area-->
                        <!--popular tags end-->

                        <!--newsletter block start-->
                        <!--newsletter block end-->

                        <!--special product start-->
                        <div class="sidebar_widget special">
                            <div class="block_title">
                                <h3 style="z-index: 1">Special Products</h3>
                            </div>
                            <c:forEach var="p" items="${specials}">
                                <div class="special_product_inner mb-20 row">
                                    <div class="special_p_thumb col-5">
                                        <a href="/product?action=view&id=${p.getId()}"><img src="${p.getImgLink()}" alt=""></a>
                                    </div>
                                    <div class="small_p_desc col-6">
                                        <div class="product_ratting">
                                            <ul>
                                                <li><a href="#"><i class="fa fa-star"></i></a></li>
                                                <li><a href="#"><i class="fa fa-star"></i></a></li>
                                                <li><a href="#"><i class="fa fa-star"></i></a></li>
                                                <li><a href="#"><i class="fa fa-star"></i></a></li>
                                                <li><a href="#"><i class="fa fa-star"></i></a></li>
                                            </ul>
                                        </div>
                                        <h3><a href="/product?action=view&id=${p.getId()}">${p.getName()}</a></h3>
                                        <div class="special_product_proce">
                                            <span class="old_price">$${p.getPrice() + 20}</span>
                                            <span class="new_price">$${p.getPrice()}</span>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        <!--special product end-->


                    </div>
                    <div class="col-lg-9 col-md-12">
                        <!--banner slider start-->
                        <!--banner slider start-->

                        <!--shop toolbar start-->
                        <div class="shop_toolbar mb-35">

                            <div class="list_button">
                                <ul class="nav" role="tablist">
                                    <li>
                                        <a class="active" data-toggle="tab" href="#large" role="tab" aria-controls="large" aria-selected="true"><i class="fa fa-th-large"></i></a>
                                    </li>
                                    <li>
                                        <a data-toggle="tab" href="#list" role="tab" aria-controls="list" aria-selected="false"><i class="fa fa-th-list"></i></a>
                                    </li>
                                </ul>
                            </div>
                            <div class="page_amount">
                                <p>Showing ${(requestScope.pageable.getPage()-1) * requestScope.pageable.getLimit() + 1} - ${requestScope.pageable.getPage() * requestScope.pageable.getLimit()} of found results</p>
                            </div>

                            <div class="select_option">
                                <label>Sort By</label>
                                <select onchange="handleSort(${requestScope.pageable.getPage()},${requestScope.pageable.getLimit()},'${requestScope.pageable.getKw()}',this.value,${requestScope.pageable.getIdCategory()},'${requestScope.pageable.getScale()}')">
                                    <option value="price-asc">Price: Increase</option>
                                    <option value="price-desc">Price: Decrease</option>
                                    <option value="name-desc">Product Name: Z-A</option>
                                    <option value="name-asc">Product Name: A-Z</option>
                                </select>
                            </div>
                        </div>
                        <!--shop toolbar end-->

                        <!--shop tab product-->
                        <div class="shop_tab_product">
                            <div class="tab-content" id="myTabContent">
                                <div class="tab-pane fade show active" id="large" role="tabpanel">
                                    <div class="row">
                                        <c:forEach var="p" items="${products}">
                                            <div class="col-lg-4 col-md-6">
                                                <div class="single_product">
                                                    <div class="product_thumb">
                                                        <a href="/product?action=view&id=${p.getId()}"><img src="${p.getImgLink()}" alt=""></a>
                                                            <%--                                                        <div class="img_icone">--%>
                                                            <%--                                                            <img src="/homepage_frontend/assets\img\cart\span-new.png" alt="">--%>
                                                            <%--                                                        </div>--%>
                                                        <div class="product_action">
                                                            <a href="/cart?action=add&id=${p.getId()}&quantity=1&doFrom=home"> <i class="fa fa-shopping-cart"></i> Add to cart</a>
                                                        </div>
                                                    </div>
                                                    <div class="product_content">
                                                        <span class="product_price">$${p.getPrice()}</span>
                                                        <h3 class="product_title"><a href="/product?action=view&id=${p.getId()}">${p.getName()}</a></h3>
                                                    </div>
                                                    <div class="product_info">
                                                        <ul>
                                                                <%--                                                            <li><a href="#" title=" Add to Wishlist ">Add to Wishlist</a></li>--%>
                                                            <li><a href="/product?action=view&id=${p.getId()}" data-toggle="modal" data-target="#modal_box" title="Quick view">View Detail</a></li>
                                                        </ul>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                                <div class="tab-pane fade" id="list" role="tabpanel">
                                    <div class="product_list_item mb-35">
                                        <c:forEach var="p" items="${products}">
                                            <div class="row align-items-center mb-2">
                                                <div class="col-lg-4 col-md-6 col-sm-6">
                                                    <div class="product_thumb">
                                                        <a href="/product?action=view&id=${p.getId()}"><img src="${p.getImgLink()}" alt=""></a>
                                                            <%--                                                        <div class="hot_img">--%>
                                                            <%--                                                            <img src="/homepage_frontend/assets\img\cart\span-hot.png" alt="">--%>
                                                            <%--                                                        </div>--%>
                                                    </div>
                                                </div>
                                                <div class="col-lg-8 col-md-6 col-sm-6">
                                                    <div class="list_product_content">
                                                        <div class="product_ratting">
                                                            <ul>
                                                                <li><a href="#"><i class="fa fa-star"></i></a></li>
                                                                <li><a href="#"><i class="fa fa-star"></i></a></li>
                                                                <li><a href="#"><i class="fa fa-star"></i></a></li>
                                                                <li><a href="#"><i class="fa fa-star"></i></a></li>
                                                                <li><a href="#"><i class="fa fa-star"></i></a></li>
                                                            </ul>
                                                        </div>
                                                        <div class="list_title">
                                                            <h3><a href="/product?action=view&id=${p.getId()}">${p.getName()}</a></h3>
                                                        </div>
                                                        <p class="design">${p.getDescription()}</p>

                                                        <div class="content_price">
                                                            <span>$${p.getPrice()}</span>
                                                            <span class="old-price">$${p.getPrice() + 20}</span>
                                                        </div>
                                                        <div class="add_links">
                                                            <ul>
                                                                <li><a href="/cart?action=add&id=${p.getId()}&quantity=1&doFrom=home" title="add to cart"><i class="fa fa-shopping-cart" aria-hidden="true"></i></a></li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>

                            </div>
                        </div>
                        <!--shop tab product end-->

                        <!--pagination style start-->
                        <div class="pagination_style">
                            <div class="item_page">
                                <label for="page_select">show</label>
                                <select id="page_select" name="limit" onchange="handleChange(${requestScope.pageable.getPage()},this.value,'${requestScope.pageable.getKw()}','${requestScope.pageable.getSortField()}','${requestScope.pageable.getOrder()}',${requestScope.pageable.getIdCategory()},'${requestScope.pageable.getScale()}')">
                                    <c:forEach var="limit" begin="5" end="10">
                                        <option <c:if test="${limit == requestScope.pageable.getLimit()}">selected</c:if> value="${limit}">${limit}</option>
                                    </c:forEach>
                                </select>
                                <span>Products by page</span>
                            </div>
                            <div class="page_number">
                                <span>Pages: </span>
                                <nav aria-label="Page navigation example ">
                                    <ul>
                                        <c:if test="${pageable.getPage() > 1}">
                                            <li class="page-item">
                                                <a class="page-link" href="/product?page=${requestScope.pageable.getPage()-1}&limit=${requestScope.pageable.getLimit()}&kw=${requestScope.pageable.getKw()}&sortField=${requestScope.pageable.getSortField()}&order=${requestScope.pageable.getOrder()}&idCategory=${requestScope.pageable.getIdCategory()}&scale=${requestScope.pageable.getScale()}">
                                                    <span aria-hidden="true">&laquo;</span>
                                                </a>
                                            </li>
                                        </c:if>
                                        <c:forEach begin="1" end="${pageable.getTotalPage()}" var="page">
                                            <c:choose>
                                                <c:when test="${page == pageable.getPage()}">
                                                    <li class="page-item active">
                                                        <a class="page-link" href="#">${page}</a>
                                                    </li>
                                                </c:when>
                                                <c:otherwise>
                                                    <li class="page-item">
                                                        <a class="page-link" href="/product?page=${page}&limit=${requestScope.pageable.getLimit()}&kw=${requestScope.pageable.getKw()}&sortField=${requestScope.pageable.getSortField()}&order=${requestScope.pageable.getOrder()}&idCategory=${requestScope.pageable.getIdCategory()}&scale=${requestScope.pageable.getScale()}">${page}</a>
                                                    </li>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>

                                        <%--                                        <li class="page-item"><a class="page-link" href="#">1</a></li>--%>
                                        <%--                                        <li class="page-item"><a class="page-link" href="#">2</a></li>--%>
                                        <%--                                        <li class="page-item"><a class="page-link" href="#">3</a></li>--%>

                                        <c:if test="${pageable.getPage() < pageable.getTotalPage()}">
                                            <li class="page-item">
                                                <a class="page-link" href="/product?page=${requestScope.pageable.getPage()+1}&limit=${requestScope.pageable.getLimit()}&kw=${requestScope.pageable.getKw()}&sortField=${requestScope.pageable.getSortField()}&order=${requestScope.pageable.getOrder()}&idCategory=${requestScope.pageable.getIdCategory()}&scale=${requestScope.pageable.getScale()}">
                                                    <span aria-hidden="true">&raquo;</span>
                                                </a>
                                            </li>
                                        </c:if>
                                    </ul>
                                </nav>
                            </div>
                        </div>
                        <!--pagination style end-->
                    </div>
                </div>
            </div>
            <!--pos home section end-->
        </div>
        <!--pos page inner end-->
    </div>
</div>
<!--pos page end-->

<!--footer area start-->
<jsp:include page="/WEB-INF/homepage/layout/foot.jsp"/>
<!--footer area end-->


<!-- modal area start -->

<!-- modal area end -->



<!-- all js here -->
<jsp:include page="/WEB-INF/homepage/layout/js_footer.jsp"/>
<script>
    function handleSort(page,limit,kw,fieldorder,idCategory,scale){
        var parts = fieldorder.split("-");
        var sortField = parts[0];
        var order = parts[1];
        let url = "/product?page="+page+"&limit="+limit+"&kw="+kw+"&sortField="+sortField+"&order="+order+"&idCategory="+idCategory+"&scale="+scale;
        window.location.assign(url);
    }
</script>
</body>
</html>