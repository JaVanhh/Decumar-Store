package datn.shopmypham.service.impl;

import datn.shopmypham.entity.Logged;
import datn.shopmypham.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class TokenManager {
    private Map<String, Logged> logged = new HashMap<>();
    private final long TOKEN_EXPIRATION_DURATION_SECONDS = 3600;

    @Autowired
    private UserService userService;

    public void addLoggedUser(String username, String token) {
        // Kiểm tra xem người dùng đã tồn tại trong danh sách đăng nhập chưa
        Logged existingUser = getUserByUsername(username);
        if (existingUser != null) {
            // Nếu người dùng đã tồn tại, vô hiệu hóa token cũ của người dùng
//            logged.remove(existingUser.getToken());
        }

        // Thêm người dùng mới vào danh sách đăng nhập
        //Logged user = new Logged(username, token);
//        user.setLastAccessTime(LocalDateTime.now());
//        logged.put(token, user);
    }


    public String generateToken() {
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        // Đảm bảo token không trùng lặp
        while (logged.containsKey(token)) {
            token = UUID.randomUUID().toString().replaceAll("-", "");
        }
        return token;
    }

    public boolean isValidTokenAndUser(String token, String username) throws Exception {

        if (logged.containsKey(token)) {
            Logged user = logged.get(token);
            if (user != null && user.getUsername().equals(username)) {
                LocalDateTime lastAccessTime = user.getLastAccessTime();
                if (lastAccessTime != null) {
                    LocalDateTime currentTime = LocalDateTime.now();
                    long elapsedTime = ChronoUnit.SECONDS.between(lastAccessTime, currentTime);
                    if (elapsedTime <= TOKEN_EXPIRATION_DURATION_SECONDS) {
                        user.setLastAccessTime(currentTime); // Cập nhật thời gian cuối cùng sử dụng token
                        return true;
                    } else {
                        // Token đã hết hạn
                        logged.remove(token); // Xóa token đã hết hạn khỏi danh sách
                        return false;
                    }
                }
            }
        }
        return false;
    }

    public boolean isValidUsernameAndPassword(String username, String password) throws Exception {
        Logged user = getUserByUsername(username);

        if (user != null && user.isLocked()) {
            // Kiểm tra thời gian khóa tài khoản
            LocalDateTime lockTime = user.getLockTime();
            LocalDateTime currentTime = LocalDateTime.now();

            if (lockTime.isAfter(currentTime)) {
                throw new Exception("Tài khoản của bạn đã bị khóa. Vui lòng thử lại sau.");
            } else {
                // Đặt lại trạng thái khóa và thời gian khóa
                user.setLocked(false);
                user.setLockTime(null);
                user.setLoginAttempts(0);

//                logged.remove(user.getToken());
            }
        }

        if (userService.authenticateUser(username, password)) {
            return true;
        } else {
            if (user != null) {
                user.setLoginAttempts(user.getLoginAttempts() + 1);
                if (user.getLoginAttempts() >= 5) {
                    // Nếu số lần nhập sai mật khẩu vượt quá ngưỡng, khóa tài khoản và đặt thời gian khóa
                    user.setLocked(true);
                    user.setLockTime(LocalDateTime.now().plusMinutes(5)); // Khóa tài khoản trong 5 phút
                }
            }
            return false;
        }
    }

    private Logged getUserByUsername(String username) {
        for (Logged user : logged.values()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
