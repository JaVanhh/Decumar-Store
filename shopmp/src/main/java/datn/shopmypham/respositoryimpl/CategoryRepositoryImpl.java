package datn.shopmypham.respositoryimpl;

import datn.shopmypham.entity.Category;
import datn.shopmypham.responsitory.CategoryRepository;
import datn.shopmypham.responsitory.DBconection;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {
    @Override
    public List<Category> getAllCategory() throws Exception {
        Category category;
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        List<Category> lst;

        try {
            connection = DBconection.getConnection();
            String sql = "Select * From Category";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            lst = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int productQuantity = resultSet.getInt("product_Quantity");
                category = new Category(id, name, productQuantity);

                lst.add(category);
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
    public void addCategory(Category category) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBconection.getConnection();
            String sql = "INSERT INTO category (id,name,product_Quantity) VALUES ( ?,?,?)";

            statement = connection.prepareStatement(sql);
            statement.setInt(1, category.getId());
            statement.setString(2, category.getName());
            statement.setInt(3, category.getProductQuantity());
            statement.executeUpdate();

        } catch (Exception ex) {
            throw ex;
        } finally {
            DBconection.closeStatement(statement);
            DBconection.closeConnection(connection);
        }
    }

    @Override
    public void deleteCategory(Category category) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBconection.getConnection();
            String sql = "DELETE FROM Category WHERE id=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, category.getId());

            statement.executeUpdate();

        } catch (SQLException ex) {
            throw ex;
        } finally {
            DBconection.closeStatement(statement);
            DBconection.closeConnection(connection);
        }
    }

    @Override
    public void updateCategory(Category category) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBconection.getConnection();
            String sql = "UPDATE Category "
                    + "SET "
                    + "name= ?"
                    + ",product_Quantity= ?"
                    + " WHERE id= ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, category.getName());
            statement.setInt(2, category.getProductQuantity());
            statement.setInt(3, category.getId());

            statement.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            DBconection.closeStatement(statement);
            DBconection.closeConnection(connection);
        }
    }

    @Override
    public List<Category> pagination(int page, int size) throws Exception {
        Category category;
        int offset = page * size;
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        List<Category> lst;

        try {
            connection = DBconection.getConnection();
            String sql = "SELECT * FROM Category LIMIT ?, ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, offset);
            statement.setInt(2, size);

            resultSet = statement.executeQuery();

            lst = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int productQuantity = resultSet.getInt("product_Quantity");
                category = new Category(id, name, productQuantity);

                lst.add(category);
            }
            return lst;
        } catch (SQLException ex) {
            throw ex;
        } finally {
            DBconection.closeResultSet(resultSet);
            DBconection.closeStatement(statement);
            DBconection.closeConnection(connection);
        }
    }

    @Override
    public List<Category> sortCategoryByName() throws Exception {
        Category category;
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        List<Category> lst;

        try {
            connection = DBconection.getConnection();
            String sql = "SELECT * FROM Category ORDER BY name asc";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            lst = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int productQuantity = resultSet.getInt("product_Quantity");
                category = new Category(id, name, productQuantity);

                lst.add(category);
            }
            return lst;
        } catch (SQLException ex) {
            throw ex;
        } finally {
            DBconection.closeResultSet(resultSet);
            DBconection.closeStatement(statement);
            DBconection.closeConnection(connection);
        }
    }

    @Override
    public List<Category> searchCategoryByName(String keyword) throws Exception {
        Category category;
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        List<Category> lst;

        try {
            connection = DBconection.getConnection();
            String sql = "SELECT * FROM Category WHERE name LIKE ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + keyword + "%");
            resultSet = statement.executeQuery();

            lst = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int productQuantity = resultSet.getInt("product_Quantity");
                category = new Category(id, name, productQuantity);

                lst.add(category);
            }
            return lst;
        } catch (SQLException ex) {
            throw ex;
        } finally {
            DBconection.closeResultSet(resultSet);
            DBconection.closeStatement(statement);
            DBconection.closeConnection(connection);
        }
    }
}
