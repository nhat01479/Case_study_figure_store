<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<div class="header_area">
  <!--header top-->
  <div class="header_top">
    <div class="row align-items-center">
      <div class="col-lg-6 col-md-6">
        <div class="switcher">
          <ul>
            <li class="languages"><a href="#"><img src="/homepage_frontend/assets\img\logo\fontlogo.jpg" alt=""> English <i class="fa fa-angle-down"></i></a>
              <ul class="dropdown_languages">
                <li><a href="#"><img src="/homepage_frontend/assets\img\logo\fontlogo.jpg" alt=""> English</a></li>
                <li><a href="#"><img src="/homepage_frontend/assets\img\logo\fontlogo2.jpg" alt=""> French </a></li>
              </ul>
            </li>

            <li class="currency"><a href="#"> Currency : $ <i class="fa fa-angle-down"></i></a>
              <ul class="dropdown_currency">
                <li><a href="#"> Dollar (USD)</a></li>
                <li><a href="#"> Euro (EUR)  </a></li>
              </ul>
            </li>
          </ul>
        </div>
      </div>
      <div class="col-lg-6 col-md-6">
        <div class="header_links">
          <ul>
            <c:if test="${sessionScope.user.geteRole().name.equals('ADMIN')}">
              <li><a href="/dashboard" title="dashboard">My dashboard</a></li>
            </c:if>
            <li><a href="/user?action=myAccount" title="My account">My account</a></li>
            <li><a href="/cart" title="My cart">My cart</a></li>
            <c:if test="${sessionScope.user == null}">
              <li><a href="/user?action=login" title="Login">Login</a></li>
            </c:if>
            <c:if test="${sessionScope.user != null}">
              <li><a href="/user?action=logout" title="Logout">Logout</a></li>
              <li><a class="font-weight-bold" href="/user?action=myAccount">Hello: ${sessionScope.user.name}</a></li>
            </c:if>
          </ul>
        </div>
      </div>
    </div>
  </div>
  <!--header top end-->

  <!--header middel-->
  <div class="header_middel">
    <div class="row align-items-center">
      <!--logo start-->
      <div class="col-lg-3 col-md-3">
        <div class="logo" style="width: 128px">
          <a href="/"><img src="/homepage_frontend/assets\img\logo\logo-figure.png" alt=""></a>
        </div>
      </div>
      <!--logo end-->
      <div class="col-lg-9 col-md-9">
        <div class="header_right_info">
          <div class="search_bar d-flex">
              <input style="height: 37px !important;" placeholder="Search..." type="text" value="${requestScope.pageable.getKw()}" onchange="handleChange(${requestScope.pageable.getPage()},${requestScope.pageable.getLimit()},this.value,'${requestScope.pageable.getSortField()}','${requestScope.pageable.getOrder()}',${requestScope.pageable.getIdCategory()},'${requestScope.pageable.getScale()}')">
              <button style="height: 37px !important;" class="btn btn-success"><i class="fa fa-search"></i></button>
          </div>
          <div class="shopping_cart">
            <a><i class="fa fa-shopping-cart"></i> ${requestScope.order.getOrderItems().size()} Item(s) - $${requestScope.order.getSubTotal()} <i class="fa fa-angle-down"></i></a>

            <!--mini cart-->
            <div class="mini_cart">
              <c:forEach items="${requestScope.order.getOrderItems()}" var="oT">

                <c:forEach items="${requestScope.allProducts}" var="p">
                  <c:if test="${p.getId() == oT.getIdProduct()}">
                    <div class="cart_item">
                      <div class="cart_img">
                        <a href="#"><img src="${p.getImgLink()}" alt=""></a>
                      </div>
                      <div class="cart_info">
                        <a href="#">${p.getName()}</a>
                        <span class="cart_price">$${p.getPrice() * oT.getQuantity()}</span>
                        <span class="quantity">Qty: ${oT.getQuantity()}</span>
                      </div>
                      <div class="cart_remove">
                        <form action="/cart?action=remove" method="post">
                          <input name="idOrder" value="${requestScope.order.getId()}" hidden="hidden">
                          <input name="idProduct" value="${oT.getIdProduct()}" hidden="hidden">
                          <input name="doFrom" value="homepage" hidden="hidden">
                          <button title="Remove this item" type="submit" class="pl-2 pr-2 fa fa-times-circle btn btn-danger"></button>
                        </form>
                      </div>
                    </div>
                  </c:if>
                </c:forEach>

              </c:forEach>

              <div class="shipping_price">
                <span> Shipping </span>
                <span>  $10.00  </span>
              </div>
              <div class="total_price">
                <span> total </span>
                <span class="prices">  $${requestScope.order.getSubTotal() + 10}  </span>
              </div>
              <div class="cart_button">
                <a href="/cart?action=check"> Check out</a>
              </div>
            </div>
            <!--mini cart end-->
          </div>

        </div>
      </div>
    </div>
  </div>
  <!--header middel end-->
  <div class="header_bottom">
    <div class="row">
      <div class="col-12">
        <div class="main_menu_inner">
          <div class="main_menu d-none d-lg-block">
            <nav>
              <ul>
                <li class="active"><a href="/">Home</a>
                  <div class="mega_menu jewelry">
                    <div class="mega_items jewelry">
                      <ul>
                        <li><a href="/">Home 1</a></li>
                        <li><a href="/">Home 2</a></li>
                      </ul>
                    </div>
                  </div>
                </li>
                <li><a href="#">shop</a>
                  <div class="mega_menu jewelry">
                    <div class="mega_items jewelry">
                      <ul>
                        <li><a href="shop-list.html">shop list</a></li>
                        <li><a href="shop-fullwidth.html">shop Full Width Grid</a></li>
                        <li><a href="shop-fullwidth-list.html">shop Full Width list</a></li>
                        <li><a href="shop-sidebar.html">shop Right Sidebar</a></li>
                        <li><a href="shop-sidebar-list.html">shop list Right Sidebar</a></li>
                        <li><a href="single-product.html">Product Details</a></li>
                        <li><a href="single-product-sidebar.html">Product sidebar</a></li>
                        <li><a href="single-product-video.html">Product Details video</a></li>
                        <li><a href="single-product-gallery.html">Product Details Gallery</a></li>
                      </ul>
                    </div>
                  </div>
                </li>
                <li><a href="#">women</a>
                  <div class="mega_menu">
                    <div class="mega_top fix">
                      <div class="mega_items">
                        <h3><a href="#">Accessories</a></h3>
                        <ul>
                          <li><a href="#">Cocktai</a></li>
                          <li><a href="#">day</a></li>
                          <li><a href="#">Evening</a></li>
                          <li><a href="#">Sundresses</a></li>
                          <li><a href="#">Belts</a></li>
                          <li><a href="#">Sweets</a></li>
                        </ul>
                      </div>
                      <div class="mega_items">
                        <h3><a href="#">HandBags</a></h3>
                        <ul>
                          <li><a href="#">Accessories</a></li>
                          <li><a href="#">Hats and Gloves</a></li>
                          <li><a href="#">Lifestyle</a></li>
                          <li><a href="#">Bras</a></li>
                          <li><a href="#">Scarves</a></li>
                          <li><a href="#">Small Leathers</a></li>
                        </ul>
                      </div>
                      <div class="mega_items">
                        <h3><a href="#">Tops</a></h3>
                        <ul>
                          <li><a href="#">Evening</a></li>
                          <li><a href="#">Long Sleeved</a></li>
                          <li><a href="#">Shrot Sleeved</a></li>
                          <li><a href="#">Tanks and Camis</a></li>
                          <li><a href="#">Sleeveless</a></li>
                          <li><a href="#">Sleeveless</a></li>
                        </ul>
                      </div>
                    </div>
                    <div class="mega_bottom fix">
                      <div class="mega_thumb">
                        <a href="#"><img src="/homepage_frontend/assets\img\banner\banner1.jpg" alt=""></a>
                      </div>
                      <div class="mega_thumb">
                        <a href="#"><img src="/homepage_frontend/assets\img\banner\banner2.jpg" alt=""></a>
                      </div>
                    </div>
                  </div>
                </li>
                <li><a href="#">men</a>
                  <div class="mega_menu">
                    <div class="mega_top fix">
                      <div class="mega_items">
                        <h3><a href="#">Rings</a></h3>
                        <ul>
                          <li><a href="#">Platinum Rings</a></li>
                          <li><a href="#">Gold Ring</a></li>
                          <li><a href="#">Silver Ring</a></li>
                          <li><a href="#">Tungsten Ring</a></li>
                          <li><a href="#">Sweets</a></li>
                        </ul>
                      </div>
                      <div class="mega_items">
                        <h3><a href="#">Bands</a></h3>
                        <ul>
                          <li><a href="#">Platinum Bands</a></li>
                          <li><a href="#">Gold Bands</a></li>
                          <li><a href="#">Silver Bands</a></li>
                          <li><a href="#">Silver Bands</a></li>
                          <li><a href="#">Sweets</a></li>
                        </ul>
                      </div>
                      <div class="mega_items">
                        <a href="#"><img src="/homepage_frontend/assets\img\banner\banner3.jpg" alt=""></a>
                      </div>
                    </div>

                  </div>
                </li>
                <li><a href="#">pages</a>
                  <div class="mega_menu">
                    <div class="mega_top fix">
                      <div class="mega_items">
                        <h3><a href="#">Column1</a></h3>
                        <ul>
                          <li><a href="portfolio.html">Portfolio</a></li>
                          <li><a href="portfolio-details.html">single portfolio </a></li>
                          <li><a href="about.html">About Us </a></li>
                          <li><a href="about-2.html">About Us 2</a></li>
                          <li><a href="services.html">Service </a></li>
                          <li><a href="my-account.html">my account </a></li>
                        </ul>
                      </div>
                      <div class="mega_items">
                        <h3><a href="#">Column2</a></h3>
                        <ul>
                          <li><a href="#">Blog </a></li>
                          <li><a href="blog-details.html">Blog  Details </a></li>
                          <li><a href="blog-fullwidth.html">Blog FullWidth</a></li>
                          <li><a href="blog-sidebar.html">Blog  Sidebar</a></li>
                          <li><a href="faq.html">Frequently Questions</a></li>
                          <li><a href="404.html">404</a></li>
                        </ul>
                      </div>
                      <div class="mega_items">
                        <h3><a href="#">Column3</a></h3>
                        <ul>
                          <li><a href="cart.html">cart</a></li>
                          <li><a href="checkout.html">Checkout  </a></li>
                          <li><a href="wishlist.html">Wishlist</a></li>
                          <li><a href="login.html">Login</a></li>
                        </ul>
                      </div>
                    </div>
                  </div>
                </li>

                <li><a href="blog.html">blog</a>
                  <div class="mega_menu jewelry">
                    <div class="mega_items jewelry">
                      <ul>
                        <li><a href="blog-details.html">blog details</a></li>
                        <li><a href="blog-fullwidth.html">blog fullwidth</a></li>
                        <li><a href="blog-sidebar.html">blog sidebar</a></li>
                      </ul>
                    </div>
                  </div>
                </li>
                <li><a href="#">contact us</a></li>

              </ul>
            </nav>
          </div>
          <div class="mobile-menu d-lg-none">
            <nav>
              <ul>
                <li><a href="/">Home</a>
                  <div>
                    <div>
                      <ul>
                        <li><a href="index.html">Home 1</a></li>
                        <li><a href="index-2.html">Home 2</a></li>
                      </ul>
                    </div>
                  </div>
                </li>
                <li><a href="#">shop</a>
                  <div>
                    <div>
                      <ul>
                        <li><a href="shop-list.html">shop list</a></li>
                        <li><a href="shop-fullwidth.html">shop Full Width Grid</a></li>
                        <li><a href="shop-fullwidth-list.html">shop Full Width list</a></li>
                        <li><a href="shop-sidebar.html">shop Right Sidebar</a></li>
                        <li><a href="shop-sidebar-list.html">shop list Right Sidebar</a></li>
                        <li><a href="single-product.html">Product Details</a></li>
                        <li><a href="single-product-sidebar.html">Product sidebar</a></li>
                        <li><a href="single-product-video.html">Product Details video</a></li>
                        <li><a href="single-product-gallery.html">Product Details Gallery</a></li>
                      </ul>
                    </div>
                  </div>
                </li>
                <li><a href="#">women</a>
                  <div>
                    <div>
                      <div>
                        <h3><a href="#">Accessories</a></h3>
                        <ul>
                          <li><a href="#">Cocktai</a></li>
                          <li><a href="#">day</a></li>
                          <li><a href="#">Evening</a></li>
                          <li><a href="#">Sundresses</a></li>
                          <li><a href="#">Belts</a></li>
                          <li><a href="#">Sweets</a></li>
                        </ul>
                      </div>
                      <div>
                        <h3><a href="#">HandBags</a></h3>
                        <ul>
                          <li><a href="#">Accessories</a></li>
                          <li><a href="#">Hats and Gloves</a></li>
                          <li><a href="#">Lifestyle</a></li>
                          <li><a href="#">Bras</a></li>
                          <li><a href="#">Scarves</a></li>
                          <li><a href="#">Small Leathers</a></li>
                        </ul>
                      </div>
                      <div>
                        <h3><a href="#">Tops</a></h3>
                        <ul>
                          <li><a href="#">Evening</a></li>
                          <li><a href="#">Long Sleeved</a></li>
                          <li><a href="#">Shrot Sleeved</a></li>
                          <li><a href="#">Tanks and Camis</a></li>
                          <li><a href="#">Sleeveless</a></li>
                          <li><a href="#">Sleeveless</a></li>
                        </ul>
                      </div>
                    </div>
                    <div>
                      <div>
                        <a href="#"><img src="/homepage_frontend/assets\img\banner\banner1.jpg" alt=""></a>
                      </div>
                      <div>
                        <a href="#"><img src="/homepage_frontend/assets\img\banner\banner2.jpg" alt=""></a>
                      </div>
                    </div>
                  </div>
                </li>
                <li><a href="#">men</a>
                  <div>
                    <div>
                      <div>
                        <h3><a href="#">Rings</a></h3>
                        <ul>
                          <li><a href="#">Platinum Rings</a></li>
                          <li><a href="#">Gold Ring</a></li>
                          <li><a href="#">Silver Ring</a></li>
                          <li><a href="#">Tungsten Ring</a></li>
                          <li><a href="#">Sweets</a></li>
                        </ul>
                      </div>
                      <div>
                        <h3><a href="#">Bands</a></h3>
                        <ul>
                          <li><a href="#">Platinum Bands</a></li>
                          <li><a href="#">Gold Bands</a></li>
                          <li><a href="#">Silver Bands</a></li>
                          <li><a href="#">Silver Bands</a></li>
                          <li><a href="#">Sweets</a></li>
                        </ul>
                      </div>
                      <div>
                        <a href="#"><img src="/homepage_frontend/assets\img\banner\banner3.jpg" alt=""></a>
                      </div>
                    </div>

                  </div>
                </li>
                <li><a href="#">pages</a>
                  <div>
                    <div>
                      <div>
                        <h3><a href="#">Column1</a></h3>
                        <ul>
                          <li><a href="portfolio.html">Portfolio</a></li>
                          <li><a href="portfolio-details.html">single portfolio </a></li>
                          <li><a href="about.html">About Us </a></li>
                          <li><a href="about-2.html">About Us 2</a></li>
                          <li><a href="services.html">Service </a></li>
                          <li><a href="my-account.html">my account </a></li>
                        </ul>
                      </div>
                      <div>
                        <h3><a href="#">Column2</a></h3>
                        <ul>
                          <li><a href="#">Blog </a></li>
                          <li><a href="blog-details.html">Blog  Details </a></li>
                          <li><a href="blog-fullwidth.html">Blog FullWidth</a></li>
                          <li><a href="blog-sidebar.html">Blog  Sidebar</a></li>
                          <li><a href="faq.html">Frequently Questions</a></li>
                          <li><a href="404.html">404</a></li>
                        </ul>
                      </div>
                      <div>
                        <h3><a href="#">Column3</a></h3>
                        <ul>
                          <li><a href="contact.html">Contact</a></li>
                          <li><a href="cart.html">Cart</a></li>
                          <li><a href="checkout.html">Checkout  </a></li>
                          <li><a href="wishlist.html">Wishlist</a></li>
                          <li><a href="login.html">Login</a></li>
                        </ul>
                      </div>
                    </div>
                  </div>
                </li>

                <li><a href="blog.html">blog</a>
                  <div>
                    <div>
                      <ul>
                        <li><a href="blog-details.html">blog details</a></li>
                        <li><a href="blog-fullwidth.html">blog fullwidth</a></li>
                        <li><a href="blog-sidebar.html">blog sidebar</a></li>
                      </ul>
                    </div>
                  </div>
                </li>
                <li><a href="contact.html">contact us</a></li>

              </ul>
            </nav>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>