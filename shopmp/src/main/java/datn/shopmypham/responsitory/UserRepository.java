package datn.shopmypham.responsitory;

import datn.shopmypham.entity.User;

import java.util.List;

public interface UserRepository {

    public List<User> getAllUser(String keyword, int page, int size) throws Exception;

    public void addUser(User user) throws Exception;

    public void deleteUser(Integer userId) throws Exception;

    public void updateUser(User user,Integer userId) throws Exception;

    public int countUserByUsername(String username) throws Exception;

    public int countUserByUserId(int id) throws Exception;

    public boolean authenticateUser(String username, String password) throws Exception;

    public String getUserRole(String username) throws Exception;

    public User getUserInfoByUserName(String username) throws Exception;

    public void register(User user) throws Exception;

}
