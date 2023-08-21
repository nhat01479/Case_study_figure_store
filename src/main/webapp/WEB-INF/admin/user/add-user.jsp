<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html>
<head>
  <title>Product Management</title>
  <%--    <jsp:include page="/layout/head_css.jsp"></jsp:include>--%>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" />
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
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
      <div class="container justify-center" style="align-items: center;">
        <div>
          <a href="/admin" class="btn btn-primary">Back</a>
        </div>
        <form method="post">
          <fieldset>
            <legend class="text-center"><h4>Add User Information</h4></legend>
            <c:if test="${requestScope.errors != null}">
              <div class="alert alert-danger">
                <ul>
                  <c:forEach items="${requestScope.errors}" var="error">
                    <li>${error}</li>
                  </c:forEach>
                </ul>
              </div>
            </c:if>

            <c:if test="${requestScope.message != null}">
              <div class="alert alert-success text-center">
                <span class="message">${requestScope.message}</span>
              </div>
            </c:if>

            <table class="table table-striped table-hover">
              <tr>
                <td class="col-2">Full Name: </td>
                <td><input type="text" name="name" id="name" class="form-control" value=""></td>
              </tr>
              <tr>
                <td>Day of birth </td>
                <td><input type="text" name="dob" id="dob" class="form-control" value=""></td>
              </tr>
              <tr>
                <td>Address </td>
                <td><input type="text" name="address" id="address" class="form-control" value=""></td>
              </tr>
              <tr>
                <td>Phone </td>
                <td><input type="text" name="phone" id="phone" class="form-control" value=""></td>
              </tr>
              <tr>
                <td>Email </td>
                <td><input type="text" name="email" id="email" class="form-control" value=""></td>
              </tr>
              <tr>
                <td>Password </td>
                <td><input type="text" name="password" id="password" class="form-control" value=""></td>
              </tr>
              <tr>
                <td>Role: </td>
                <td>
                  <select name="role" class="form-control">
                    <c:forEach var="r" items="${roles}">
                      <option
                              <c:if test="${r.name().equals(user.eRole.name)}">selected</c:if>
                              value="${r.name()}">${r.name()}</option>
                    </c:forEach>
                  </select>
                </td>
              </tr>

              <tr>
                <td></td>
                <td><input type="submit" value="Create" class="btn btn-primary"></td>
              </tr>
            </table>
          </fieldset>
        </form>
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
<%--<jsp:include page="/layout/footer_js.jsp"></jsp:include>--%>

<jsp:include page="/WEB-INF/admin/layout/footer_js.jsp"></jsp:include>
</body>
</html>
</html>