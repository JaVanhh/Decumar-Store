package datn.shopmypham.service.impl;

import datn.shopmypham.entity.Logged;
import datn.shopmypham.entity.LoginResponse;
import datn.shopmypham.entity.User;
import datn.shopmypham.exception.AuthenticationException;
import datn.shopmypham.exception.LockUserException;
import datn.shopmypham.responsitory.UserRepository;
import datn.shopmypham.service.AuthenticationService;
import datn.shopmypham.ultil.CacheUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    static final long TOKEN_EXPIRATION_DURATION_SECONDS = 3600;

    final UserRepository repository;

    public AuthenticationServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * Nghiep vu login
     * B1: Check user/pass co hop le hay k?
     * - Input: user, pass
     * - Output: Ok/Not-OK
     * - Method:
     * + Query User by Username => Ko ton tai => Loi
     * + Check password: Khong trung => Loi =>> Xu ly buoc B11
     * B2: Tao token, cache, response cho client
     * - Input: Loged Info
     * - Output: Token
     * - Method:
     * + Dung UIID gen chuoi string -> Set vao truong sid cua Loged Info
     * + Dung Map cache lai thong tin Loged Info
     * <p>
     * B11: Neu sai 5 lan -> Khoa
     * B12: Check lock-user khi dang nhap
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public LoginResponse authen(String username, String password) throws Exception {
        User user;
        Logged logged;

        try {
            user = repository.getUserInfoByUserName(username);
            if (user == null) {
                throw new AuthenticationException("Username/password invalid");
            }

            //Check user co bi khoa hay ko
            //Lay thong
            // tin logon trong cache, null -> chua tung dang nhap
            Logged tmp = CacheUtil.LOGGED_MAP.get(username);
            log.info("tmp: {}", tmp == null ? "NULL" : tmp.getLoginAttempts());
            if (tmp != null) {
                if (tmp.isLock()) {
                    throw new LockUserException("Tai khoan cua ban dang bi khoa");
                }
            }

            if (!password.equals(user.getPassword())) {
                //B11 -> Check and lock user
                incCountAndLockUser(username, tmp);
                throw new AuthenticationException("Username/password invalid");
            }

            logged = new Logged();
            logged.setUsername(username);
            logged.setSession(UUID.randomUUID().toString());//Gen ra session id ~ TOKEN
            logged.setLastAccessTime(LocalDateTime.now().plusSeconds(TOKEN_EXPIRATION_DURATION_SECONDS));
            logged.setRole(user.getRole());
            //Thuc hien cache lai thong tin
            CacheUtil.LOGGED_MAP.put(username, logged);

            return new LoginResponse(logged.getSession(),user);
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public boolean isValidTokenAndUsername(String token, String username) throws Exception {
        return CacheUtil.isValidTokenAndUser(token, username);
    }

    private void incCountAndLockUser(String username, Logged logged) {
        if (logged == null) {
            logged = new Logged();
            logged.setLoginAttempts(1);
            logged.setUsername(username);
            CacheUtil.LOGGED_MAP.put(username, logged);
            return;
        }

        int count = logged.getLoginAttempts();
        count++;
        logged.setLoginAttempts(count);

        if (count >= 5) {
            LocalDateTime lockTime = LocalDateTime.now().plusMinutes(1);
            logged.setLocked(true);
            logged.setLockTime(lockTime);
        }
    }


}
