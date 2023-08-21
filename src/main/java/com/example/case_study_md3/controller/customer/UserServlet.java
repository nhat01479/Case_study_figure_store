package com.example.case_study_md3.controller.customer;
import com.example.case_study_md3.model.ERole;
import com.example.case_study_md3.model.Order;
import com.example.case_study_md3.model.OrderItem;
import com.example.case_study_md3.model.User;
import com.example.case_study_md3.service.OrderItemService;
import com.example.case_study_md3.service.OrderService;
import com.example.case_study_md3.service.UserService;
import com.example.case_study_md3.utils.Config;
import com.example.case_study_md3.utils.DateFormat;
import com.example.case_study_md3.utils.ValidateUtils;
import org.mindrot.jbcrypt.BCrypt;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
@WebServlet(name = "UserServlet", value = "/user")
public class UserServlet extends HttpServlet {
    private UserService userService;
    private OrderService orderService;
    private OrderItemService orderItemService;
    public void init() {
        userService = new UserService();
        orderService = new OrderService();
        orderItemService = new OrderItemService();
    }
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "login":
                showLogin(req, resp);
                break;
            case "signup":
                showSignup(req, resp);
                break;
            case "logout":
                showLogout(req, resp);
                break;
            case "myAccount":
                showMyAccount(req, resp);
            case "delete":
//                showDeleteUser(req, resp);
                break;
//            case "view":
//                showDetailsUser(req, resp);
            default:
//                showListUser(req,resp);
                break;
        }
    }

    private void showMyAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            List<Order> orders = orderService.findUserOrders(user.getId());
//            List<OrderItem> orderItems = orderItemService.

            req.setAttribute("orders",orders);
            req.getRequestDispatcher("/WEB-INF/homepage/user-account.jsp").forward(req, resp);

        } else {
            resp.sendRedirect("/user?action=login");
//            req.getRequestDispatcher("/WEB-INF/homepage/signIn.jsp").forward(req, resp);
        }
    }
    private void showLogout(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();
        session.removeAttribute("user");
//        req.getRequestDispatcher("/product").forward(req, resp);
        resp.sendRedirect("/product");
    }
    private void showSignup(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/admin/user/create-user.jsp").forward(req, resp);
    }
    private void showLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/homepage/signIn.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "login":
                checkLogin(req, resp);
                break;
            case "signup":
                signup(req, resp);
                break;
            case "updateInfo":
                updateInfo(req, resp);
                break;
            case "changePass":
                changePass(req, resp);
                break;
            default:
                break;
        }
    }

    private void changePass(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<String> errorsP = new ArrayList<>();
        String password = req.getParameter("password");
        String re_pass = req.getParameter("confirm-password");
        if (!password.equals(re_pass)) {
            errorsP.add("Mật khẩu không trùng khớp. Hãy nhập lại");
//            req.setAttribute("errorsP", errorsP);
            session.removeAttribute("messageP");
            session.setAttribute("errorsP",errorsP);
//            req.getRequestDispatcher("/WEB-INF/homepage/user-account.jsp").forward(req, resp);
            String redirectURL = req.getContextPath() + "/user?action=myAccount&scrollTo=address";
            resp.sendRedirect(redirectURL);
        } else {
            User user = (User) session.getAttribute("user");
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            user.setPassword(hashedPassword);
            userService.update(user.getId(), user);
//            req.setAttribute("messageP", "Đổi mật khẩu thành công");
            session.removeAttribute("errorsP");
            session.setAttribute("messageP", "Đổi mật khẩu thành công");
//            req.getRequestDispatcher("/WEB-INF/homepage/user-account.jsp").forward(req, resp);
            String redirectURL = req.getContextPath() + "/user?action=myAccount&scrollTo=address";
            resp.sendRedirect(redirectURL);
        }
    }

    private void updateInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> errors = new ArrayList<>();
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        validateInputName(req, errors, user);
        validateInputAddress(req, errors, user);
        validateInputPhone(req, errors, user);
        validateInputEmail(req, errors, user);
        validateInputDob(req, errors, user);
        if (errors.isEmpty()) {
            userService.update(user.getId(), user);
//            req.setAttribute("message", "Sửa thành công");
            session.removeAttribute("errors");
            session.setAttribute("message","Sửa thành công");
        } else {
//            req.setAttribute("errors", errors);
            session.removeAttribute("message");
            session.setAttribute("errors", errors);
            session.setAttribute("user", user);
        }
        String redirectURL = req.getContextPath() + "/user?action=myAccount&scrollTo=account-details";
        resp.sendRedirect(redirectURL);
//        req.getRequestDispatcher("/WEB-INF/homepage/user-account.jsp").forward(req, resp);
    }

    private void validateInputDob(HttpServletRequest req, List<String> errors, User user) {
        String dob = req.getParameter("birthday");
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

    private void signup(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> errors = new ArrayList<>();
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String re_pass = req.getParameter("confirm-password");
        if (!password.equals(re_pass)) {
            errors.add("Mật khẩu không trùng khớp. Hãy nhập lại");
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("/WEB-INF/admin/user/create-user.jsp").forward(req, resp);
        } else {
            User user = userService.checkAccount(email);
            if (user != null) {
                errors.add("Email đã đăng ký. Vui lòng nhập email khác");
                req.setAttribute("errors", errors);
                req.getRequestDispatcher("/WEB-INF/admin/user/create-user.jsp").forward(req, resp);
            } else {
                String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
                User userNew = new User();
                userNew.setName(name);
                userNew.setCreateAt(new Date());
                userNew.setEmail(email);
                userNew.setPassword(hashedPassword);
                userNew.seteRole(ERole.USER);
                userService.save(userNew);
                req.setAttribute("message", "Đăng ký thành công. Login để đăng nhập");
                resp.sendRedirect("user?action=myAccount");
//                req.getRequestDispatcher("/WEB-INF/admin/user/create-user.jsp").forward(req, resp);
            }
        }
    }

    private void checkLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> errors = new ArrayList<>();
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String remember = req.getParameter("remember");
        User user = userService.checkUserHash(email);
        if (user == null || user.getDeleteAt() != null || !BCrypt.checkpw(password, user.getPassword())) {
            errors.add("Email hoặc password không đúng");
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("/WEB-INF/homepage/signIn.jsp").forward(req, resp);
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            Cookie emailC = new Cookie("email", email);
            Cookie passC = new Cookie("pass", password);
            Cookie rem = new Cookie("remember", remember);
            if (remember != null) {
                emailC.setMaxAge(60 * 60);
                passC.setMaxAge(60 * 60);
                rem.setMaxAge(60 * 60);
            } else {
                emailC.setMaxAge(0);
                passC.setMaxAge(0);
                rem.setMaxAge(0);
            }
            resp.addCookie(emailC);
            resp.addCookie(passC);
            resp.addCookie(rem);
            resp.sendRedirect("/product");
        }
    }
}