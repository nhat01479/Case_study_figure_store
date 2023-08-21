package com.example.case_study_md3.controller.admin;

import com.example.case_study_md3.model.ERole;
import com.example.case_study_md3.model.Pageable;
import com.example.case_study_md3.model.Product;
import com.example.case_study_md3.model.User;
import com.example.case_study_md3.service.UserService;
import com.example.case_study_md3.utils.Config;
import com.example.case_study_md3.utils.DateFormat;
import com.example.case_study_md3.utils.ValidateUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "AdminUserServlet", value = "/admin")
public class AdminUserServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null || !user.geteRole().name().equals("ADMIN")){
            resp.sendRedirect("/404");
            return;
        }

        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "edit":
                showAdminEditUser(req, resp);
                break;
            case "create":
                showAdminCreateUser(req, resp);
                break;
            default:
                showUser(req, resp);
        }
    }

    private void showAdminCreateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ERole[] roles = ERole.values();
        req.setAttribute("roles", roles);
        req.getRequestDispatcher(Config.ADMIN_TO_USER+ "add-user.jsp").forward(req, resp);
    }

    private void showAdminEditUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        User user = userService.findUser(id);
        ERole[] roles = ERole.values();
        req.setAttribute("user", user);
        req.setAttribute("roles", roles);

        req.getRequestDispatcher(Config.ADMIN_TO_USER + "edit-user.jsp").forward(req, resp);
    }

    private void showUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userService.findAll();
        int count = userService.getTotalUser();
        int endPage = count/3;
        if (count % 3 != 0) {
            endPage++;
        }
        req.setAttribute("users", users);
        req.setAttribute("endPage", endPage);
        req.getRequestDispatcher("/WEB-INF/admin/user/list-user.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "edit":
                adminEditUser(req, resp);
                break;
            case "create":
                adminCreateUser(req, resp);
                break;
            case "delete":

                int idDelete = Integer.parseInt(req.getParameter("idDelete"));
                userService.remove(idDelete);

                resp.sendRedirect("/admin");
                break;
            default:
                break;
        }
    }

    private void adminCreateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> errors = new ArrayList<>();
        User user = new User();
        validateInputName(req, errors, user);
        validateInputDob(req, errors, user);
        validateInputAddress(req, errors, user);
        validateInputPhone(req, errors, user);
        checkInputEmail(req, errors, user);
        String password = req.getParameter("password");
        user.setPassword(password);
        String strERole = req.getParameter("role");
        ERole eRole = ERole.getERoleByRole(strERole);
        if (eRole != null) {
            user.seteRole(eRole);
        } else {
            errors.add("Role không hợp lệ");
            user.seteRole(ERole.USER);
        }
        if (errors.isEmpty()) {
            user.setCreateAt(new Date());
            userService.save(user);
            req.setAttribute("message", "Thêm thành công");
        } else {
            req.setAttribute("errors", errors);
        }
        req.setAttribute("user", user);
        ERole[] eRoles = ERole.values();
        req.setAttribute("roles", eRoles);
        req.getRequestDispatcher(Config.ADMIN_TO_USER + "add-user.jsp").forward(req, resp);
    }

    private void checkInputEmail(HttpServletRequest req, List<String> errors, User user) {
        String email = req.getParameter("email");
        if (!ValidateUtils.isEmailValid(email)) {
            errors.add("Email không hợp lệ");
        } else {
            User user1 = userService.checkAccount(email);
            if (user1 != null) {
                errors.add("Email đã đăng ký. Vui lòng nhập email khác");
            } else {
                user.setEmail(email);
            }
        }
    }

    private void adminEditUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> errors = new ArrayList<>();
         int id = Integer.parseInt(req.getParameter("id"));
         User user = userService.findUser(id);
         validateInputName(req, errors, user);
         validateInputDob(req, errors, user);
         validateInputAddress(req, errors, user);
         validateInputPhone(req, errors, user);
         validateInputEmail(req, errors, user);
         String password = req.getParameter("password");
         user.setPassword(password);
         String strERole = req.getParameter("role");
         ERole eRole = ERole.getERoleByRole(strERole);
        if (eRole != null) {
            user.seteRole(eRole);
        } else {
            errors.add("Role không hợp lệ");
            user.seteRole(ERole.USER);
        }
        if (errors.isEmpty()) {
            userService.update(user.getId(), user);
            req.setAttribute("message", "Cập nhập thành công");
        } else {
            req.setAttribute("errors", errors);
        }
        req.setAttribute("user", user);
        ERole[] eRoles = ERole.values();
        req.setAttribute("roles", eRoles);
        req.getRequestDispatcher(Config.ADMIN_TO_USER + "edit-user.jsp").forward(req, resp);
    }
    private void validateInputDob(HttpServletRequest req, List<String> errors, User user) {
        String dob = req.getParameter("dob");
        if (!ValidateUtils.isDOBValid(dob)) {
            errors.add("Ngày sinh không hợp lệ");
        } else {
            user.setDob(DateFormat.parse(dob));
        }
    }

    private void validateInputEmail(HttpServletRequest req, List<String> errors, User user) {
        String email = req.getParameter("email");
        if (!ValidateUtils.isEmailValid(email)) {
            errors.add("Email không hợp lệ");
        } else {
            user.setEmail(email);
        }
    }

    private void validateInputPhone(HttpServletRequest req, List<String> errors, User user) {
        String phone = req.getParameter("phone");
        if (!ValidateUtils.isPhoneValid(phone)) {
            errors.add("Phone không hợp lệ");
        } else {
            user.setPhone(phone);
        }
    }

    private void validateInputAddress(HttpServletRequest req, List<String> errors, User user) {
        String address = req.getParameter("address");
        if (!ValidateUtils.isAddressValid(address)) {
            errors.add("Địa chỉ không hợp lệ");
        } else {
            user.setAddress(address);
        }
    }

    private void validateInputName(HttpServletRequest req, List<String> errors, User user) {
        String name = req.getParameter("name");
        if (!ValidateUtils.isNameValid(name)) {
            errors.add("Tên không hợp lệ. Tên phải từ 8-30 ký tự và bắt đầu là chữ cái");
        } else {
            user.setName(name);
        }
    }
}
