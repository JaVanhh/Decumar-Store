package datn.shopmypham.service;

import datn.shopmypham.entity.LoginResponse;

public interface AuthenticationService {
    public LoginResponse authen(String user, String password) throws Exception;

    public boolean isValidTokenAndUsername(String token, String username) throws Exception;
}
