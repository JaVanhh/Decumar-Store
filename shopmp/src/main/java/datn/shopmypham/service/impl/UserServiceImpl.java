package datn.shopmypham.service.impl;

import datn.shopmypham.controller.Validate;
import datn.shopmypham.entity.User;
import datn.shopmypham.responsitory.UserRepository;
import datn.shopmypham.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final Validate validate;

    public UserServiceImpl(UserRepository userRepository, Validate validate) {
        this.userRepository = userRepository;
        this.validate = validate;
    }

    @Override
    public List<User> getAllUser(String keyword, int page, int size) throws Exception {
        return userRepository.getAllUser(keyword, page, size);
    }


    @Override
    public boolean authenticateUser(String username, String password) throws Exception {
        return userRepository.authenticateUser(username, password);
    }

    @Override
    public boolean checkUserRoleAdmin(String username) throws Exception {
        String userRole = userRepository.getUserRole(username);
        return userRole != null && (userRole.contains("ROLE_ADMIN") || userRole.contains("ROLE_SUPER_ADMIN"));
    }

    @Override
    public boolean checkUserRoleSuperAdmin(String username) throws Exception {
        String userRole = userRepository.getUserRole((username));
        return userRole != null && userRole.equals("ROLE_SUPER_ADMIN");
    }

    @Override
    public void register(User user) throws Exception{
        String username = user.getUsername();
        String confirmPassword = user.getPassword();
        String password = user.getPassword();
        int countUsername = userRepository.countUserByUsername(username);
        if (!validate.isValidPhone(user.getPhone())) {
            throw new RuntimeException("Số điện thoại không hợp lệ");
        }
        if (!validate.isValidEmail(user.getEmail())) {
            throw new RuntimeException("Email không hợp lệ ");
        }
        if (!validate.isValidPassword(user.getPassword())) {
            throw new RuntimeException("Password không được bỏ trống ");
        }
        if (!confirmPassword.equals(password)) {
            throw new RuntimeException("Xác nhận mật khẩu sai !");
        }
        if (countUsername > 0) {
            throw new RuntimeException("Username đã tồn tại: " + username);
        }
        userRepository.register(user);

    }

    @Override
    public void addUser(User user) throws Exception {
        String username = user.getUsername();
        int countUsername = userRepository.countUserByUsername(username);

        if (!validate.isValidPhone(user.getPhone())) {
            throw new RuntimeException("Số điện thoại không hợp lệ");
        }
        if (!validate.isValidEmail(user.getEmail())) {
            throw new RuntimeException("Email không hợp lệ ");
        }
        if (!validate.isValidPassword(user.getPassword())) {
            throw new RuntimeException("Password không được bỏ trống ");
        }

        if (countUsername > 0) {
            throw new RuntimeException("Username đã tồn tại: " + username);
        }
        userRepository.addUser(user);
    }

    @Override
    public void deleteUser(Integer userId) throws Exception {
        int id = userId;
        int countId = userRepository.countUserByUserId(id);

        if (countId > 0) {
            userRepository.deleteUser(userId);
        } else {
            throw new RuntimeException("Không tìm thấy id muốn xóa: " + id);
        }
    }

    @Override
    public void updateUser(User user, Integer userId) throws Exception {
        String username = user.getUsername();
        int id = userId;
        int countUsername = userRepository.countUserByUsername(username);
        int countId = userRepository.countUserByUserId(id);

        if (countId > 0) {
            if (countUsername > 0) {
                throw new RuntimeException("Username đã tồn tại: " + username);
            }
            userRepository.updateUser(user, userId);
        }

//        if (countId > 0 && countUsername > 0) {
//            userRespository.updateUser(user);
//        } else {
//            throw new RuntimeException("Không tìm thấy id hoặc username muốn cập nhật");
//        }
    }
}
