package datn.shopmypham.respositoryimpl;

import datn.shopmypham.entity.User;
import datn.shopmypham.responsitory.DBconection;
import datn.shopmypham.responsitory.UserRepository;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Override
    public List<User> getAllUser(String keyword, int page, int size) throws Exception {
        User user;
        int offset = page * size;
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        List<User> lst;

        try {
            connection = DBconection.getConnection();
            String sql = "Select id,name,phone,username,email,role From User WHERE name LIKE ? ORDER BY name asc LIMIT ?, ? ";
            statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + keyword + "%");
            statement.setInt(2, offset);
            statement.setInt(3, size);
            resultSet = statement.executeQuery();

            lst = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String phone = resultSet.getString("phone");
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                String role = resultSet.getString("role");

                user = new User(id, name, phone, username, email, role);
                lst.add(user);

            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            DBconection.closeResultSet(resultSet);
            DBconection.closeStatement(statement);
            DBconection.closeConnection(connection);
        }
        return lst;
    }

    @Override
    public void addUser(User user) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBconection.getConnection();
            String sql = "INSERT INTO user (id,name,phone,username,email,password,role) VALUES ( ?,?,?,?,?,?,?)";

            statement = connection.prepareStatement(sql);
            statement.setInt(1, user.getId());
            statement.setString(2, user.getName());
            statement.setString(3, user.getPhone());
            statement.setString(4, user.getUsername());
            statement.setString(5, user.getEmail());
            statement.setString(6, user.getPassword());
            statement.setString(7, user.getRole());

            statement.executeUpdate();

        } catch (Exception ex) {
            throw ex;
        } finally {
            DBconection.closeStatement(statement);
            DBconection.closeConnection(connection);
        }
    }

    @Override
    public void deleteUser(Integer userId) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBconection.getConnection();
            String sql = "DELETE FROM User WHERE id=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);

            statement.executeUpdate();

        } catch (SQLException ex) {
            throw ex;
        } finally {
            DBconection.closeStatement(statement);
            DBconection.closeConnection(connection);
        }
    }

    @Override
    public void updateUser(User user, Integer userId) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBconection.getConnection();
            String sql = "UPDATE User "
                    + "SET "
                    + "name= ?"
                    + ",phone= ?"
                    + " WHERE id= ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, user.getName());
            statement.setString(2, user.getPhone());
            statement.setInt(3, userId);

            statement.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            DBconection.closeStatement(statement);
            DBconection.closeConnection(connection);
        }
    }

    @Override
    public int countUserByUsername(String username) throws Exception {
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement statement = null;

        try {
            connection = DBconection.getConnection();
            String sql = "SELECT count(*) as count FROM shopmypham.user Where username = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            resultSet = statement.executeQuery();

            resultSet.next();
            return resultSet.getInt("count");
        } catch (SQLException ex) {
            throw ex;
        } finally {
            DBconection.closeResultSet(resultSet);
            DBconection.closeStatement(statement);
            DBconection.closeConnection(connection);

        }
    }

    @Override
    public int countUserByUserId(int id) throws Exception {
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement statement = null;

        try {
            connection = DBconection.getConnection();
            String sql = "SELECT count(*) as count FROM shopmypham.user Where id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            resultSet.next();
            return resultSet.getInt("count");
        } catch (SQLException ex) {
            throw ex;
        } finally {
            DBconection.closeResultSet(resultSet);
            DBconection.closeStatement(statement);
            DBconection.closeConnection(connection);

        }
    }


    @Override
    public boolean authenticateUser(String username, String password) throws Exception {
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement statement = null;

        try {
            connection = DBconection.getConnection();
            String sql = "SELECT COUNT(*) FROM user WHERE username = ? AND password = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
            return false;

        } catch (SQLException ex) {
            throw ex;
        } finally {
            DBconection.closeResultSet(resultSet);
            DBconection.closeStatement(statement);
            DBconection.closeConnection(connection);

        }
    }

    @Override
    public String getUserRole(String username) throws Exception {
        String role = null;
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement statement = null;

        try {
            connection = DBconection.getConnection();
            String sql = "Select role From User WHERE username = ? ";
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                role = resultSet.getString("role");
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            DBconection.closeResultSet(resultSet);
            DBconection.closeStatement(statement);
            DBconection.closeConnection(connection);
        }
        return role;
    }

    @Override
    public User getUserInfoByUserName(String username) throws Exception {
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement statement = null;

        try {
            connection = DBconection.getConnection();
            String sql = "Select name,email,password,phone, role From User WHERE username = ? ";
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String phone = resultSet.getString("phone");
                String role = resultSet.getString("role");

                User user = new User();

                user.setName(name);
                user.setUsername(username);
                user.setEmail(email);
                user.setPassword(password);
                user.setPhone(phone);
                user.setRole(role);

                return user;
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            DBconection.closeResultSet(resultSet);
            DBconection.closeStatement(statement);
            DBconection.closeConnection(connection);
        }

        return null;
    }

    @Override
    public void register(User user) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBconection.getConnection();
            String sql = "INSERT INTO user (name,username,email,password,phone,role) VALUES ( ?,?,?,?,?,?)";

            statement = connection.prepareStatement(sql);
            statement.setString(1, user.getName());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getPhone());
            statement.setString(6, "ROLE_CUSTOMER");

            statement.executeUpdate();

        } catch (Exception ex) {
            throw ex;
        } finally {
            DBconection.closeStatement(statement);
            DBconection.closeConnection(connection);
        }
    }

}



