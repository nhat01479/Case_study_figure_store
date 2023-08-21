<div class="navbar-custom">
  <ul class="list-unstyled topnav-menu float-right mb-0">




    <li class="dropdown notification-list">
      <a class="nav-link dropdown-toggle nav-user mr-0 waves-effect" data-toggle="dropdown" href="#"
         role="button" aria-haspopup="false" aria-expanded="false">
        <img src="/admin_frontend/assets\images\users\avatar-1.jpg" alt="user-image" class="rounded-circle">
        <span class="pro-user-name ml-1">
                                ${sessionScope.user.name}   <i class="mdi mdi-chevron-down"></i>
                            </span>
      </a>
      <div class="dropdown-menu dropdown-menu-right profile-dropdown ">
        <!-- item-->
        <div class="dropdown-header noti-title">
          <h6 class="text-overflow m-0">Welcome !</h6>
        </div>

        <!-- item-->
        <a href="javascript:void(0);" class="dropdown-item notify-item">
          <i class="mdi mdi-account-outline"></i>
          <span>Profile</span>
        </a>

        <!-- item-->
        <a href="javascript:void(0);" class="dropdown-item notify-item">
          <i class="mdi mdi-settings-outline"></i>
          <span>Settings</span>
        </a>

        <!-- item-->
        <a href="javascript:void(0);" class="dropdown-item notify-item">
          <i class="mdi mdi-lock-outline"></i>
          <span>Lock Screen</span>
        </a>

        <div class="dropdown-divider"></div>

        <!-- item-->
        <a href="javascript:void(0);" class="dropdown-item notify-item">
          <i class="mdi mdi-logout-variant"></i>
          <span>Logout</span>
        </a>

      </div>
    </li>

    <li class="dropdown notification-list">
      <a href="javascript:void(0);" class="nav-link right-bar-toggle waves-effect">
        <i class="mdi mdi-settings-outline noti-icon"></i>
      </a>
    </li>


  </ul>

  <!-- LOGO -->
  <div class="logo-box">
    <a href="/" class="logo text-center logo-dark">
                        <span class="logo-lg">
                            <img src="/admin_frontend/assets\images\logo-dark.png" alt="" height="18">
                          <!-- <span class="logo-lg-text-dark">Velonic</span> -->
                        </span>
      <span class="logo-sm">
                            <!-- <span class="logo-lg-text-dark">V</span> -->
                            <img src="/admin_frontend/assets\images\logo-sm.png" alt="" height="22">
                        </span>
    </a>

    <a href="/" class="logo text-center logo-light">
                        <span class="logo-lg">
                            <img src="/admin_frontend/assets\images\logo-light.png" alt="" height="18">
                          <!-- <span class="logo-lg-text-dark">Velonic</span> -->
                        </span>
      <span class="logo-sm">
                            <!-- <span class="logo-lg-text-dark">V</span> -->
                            <img src="/admin_frontend/assets\images\logo-sm.png" alt="" height="22">
                        </span>
    </a>
  </div>

  <!-- LOGO -->


  <ul class="list-unstyled topnav-menu topnav-menu-left m-0">
    <li>
      <button class="button-menu-mobile waves-effect">
        <i class="mdi mdi-menu"></i>
      </button>
    </li>

  </ul>
</div>