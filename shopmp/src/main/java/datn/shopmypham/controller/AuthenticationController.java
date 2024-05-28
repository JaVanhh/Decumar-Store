package datn.shopmypham.controller;

import datn.shopmypham.entity.LoginRequest;
import datn.shopmypham.entity.LoginResponse;
import datn.shopmypham.exception.AuthenticationException;
import datn.shopmypham.exception.LockUserException;
import datn.shopmypham.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/back-end/api")
@Slf4j
public class AuthenticationController {
    private final AuthenticationService service;

    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws NoSuchAlgorithmException {
        /**
         * {
         *     "username": "vanhvuive",
         *     "password": "vanh123"
         * }
         */

        String username;
        String password;
        String token;

        username = loginRequest.getUsername();
        password = loginRequest.getPassword();

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes(StandardCharsets.UTF_8));
        byte[] digest = md.digest();
        String myHash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        System.out.println("My Hash: " + myHash);
        try {
            LoginResponse loginResponse = service.authen(username, password);
            return ResponseEntity.ok(loginResponse);
        } catch (AuthenticationException e) {
            log.warn("Invalid user/password - username: {}", username);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Tên đăng nhập hoặc mật khẩu không đúng.");
        } catch (LockUserException e) {
            log.warn("User is locke - username: {}", username);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Tài khoản của bạn bị khóa 5 phút");

        } catch (Exception e) {
            log.error("System error when process atuhen - username: {}", username, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("He thong dang loi!");
        }
    }


}
