package datn.shopmypham.service;

import datn.shopmypham.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    public List<User> getAllUser(String keyword, int page,int size) throws Exception;

    public void addUser(User user) throws Exception;

    public void deleteUser(Integer userId) throws Exception;

    public void updateUser(User user,Integer userId) throws Exception;

    public boolean authenticateUser(String username, String password) throws Exception;

    public boolean checkUserRoleAdmin(String username) throws Exception;

    public boolean checkUserRoleSuperAdmin(String username) throws Exception;

    public void register(User user) throws Exception;

}
