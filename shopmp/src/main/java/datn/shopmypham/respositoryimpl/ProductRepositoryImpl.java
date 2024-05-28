package datn.shopmypham.respositoryimpl;

import datn.shopmypham.entity.Product;
import datn.shopmypham.responsitory.DBconection;
import datn.shopmypham.responsitory.ProductRepository;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    @Override
    public List<Product> getAllProduct() throws Exception {
        Product product;
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        List<Product> lst;

        try {
            connection = DBconection.getConnection();
            String sql = "Select * From Product";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            lst = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int categoryId = resultSet.getInt("category_Id");
                int quantityStock = resultSet.getInt("quantity_Stock");
                double productPrice = resultSet.getDouble("product_Price");

                product = new Product(id, name, categoryId, quantityStock, productPrice);
                lst.add(product);

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
    public void addProduct(Product product) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBconection.getConnection();
            String sql = "INSERT INTO product  (id,name,category_Id,quantity_Stock,product_Price) VALUES ( ?,?,?,?,?)";

            statement = connection.prepareStatement(sql);
            statement.setInt(1, product.getId());
            statement.setString(2, product.getName());
            statement.setInt(3, product.getCategoryId());
            statement.setInt(4, product.getQuantityStock());
            statement.setDouble(5, product.getProductPrice());

            statement.executeUpdate();

        } catch (Exception ex) {
            throw ex;
        } finally {
            DBconection.closeStatement(statement);
            DBconection.closeConnection(connection);
        }
    }

    @Override
    public void deleteProduct(Product product) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBconection.getConnection();
            String sql = "DELETE FROM Product WHERE id=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, product.getId());

            statement.executeUpdate();

        } catch (SQLException ex) {
            throw ex;
        } finally {
            DBconection.closeStatement(statement);
            DBconection.closeConnection(connection);
        }
    }

    @Override
    public void updateProduct(Product product) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBconection.getConnection();
            String sql = "UPDATE Product "
                    + "SET "
                    + "name= ?"
                    + ",category_Id= ?"
                    + ",quantity_Stock= ?"
                    + ",product_Price= ?"
                    + " WHERE id= ?";

            statement = connection.prepareStatement(sql);
            statement.setInt(1, product.getId());
            statement.setString(2, product.getName());
            statement.setInt(3, product.getCategoryId());
            statement.setInt(4, product.getQuantityStock());
            statement.setDouble(5, product.getProductPrice());

            statement.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            DBconection.closeStatement(statement);
            DBconection.closeConnection(connection);

        }
    }

    @Override
    public List<Product> pagination(int page, int size) throws Exception {
        Product product;
        int offset = page * size;
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        List<Product> lst;

        try {
            connection = DBconection.getConnection();
            String sql = "SELECT * FROM Product LIMIT ?, ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, offset);
            statement.setInt(2, size);

            resultSet = statement.executeQuery();

            lst = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int categoryId = resultSet.getInt("category_Id");
                int quantityStock = resultSet.getInt("quantity_Stock");
                double productPrice = resultSet.getDouble("product_Price");

                product = new Product(id, name, categoryId, quantityStock, productPrice);
                lst.add(product);
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
    public List<Product> sortProductByName() throws Exception {
        Product product;
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        List<Product> lst;

        try {
            connection = DBconection.getConnection();
            String sql = "SELECT * FROM Product ORDER BY name asc";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            lst = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int categoryId = resultSet.getInt("category_Id");
                int quantityStock = resultSet.getInt("quantity_Stock");
                double productPrice = resultSet.getDouble("product_Price");

                product = new Product(id, name, categoryId, quantityStock, productPrice);
                lst.add(product);
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
    public List<Product> searchProductByName(String keyword) throws Exception {
        Product product;
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        List<Product> lst;

        try {
            connection = DBconection.getConnection();
            String sql = "SELECT * FROM Product WHERE name LIKE ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + keyword + "%");
            resultSet = statement.executeQuery();

            lst = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int categoryId = resultSet.getInt("category_Id");
                int quantityStock = resultSet.getInt("quantity_Stock");
                double productPrice = resultSet.getDouble("product_Price");

                product = new Product(id, name, categoryId, quantityStock, productPrice);
                lst.add(product);
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
    public List<Product> searchProductByCategoryId(int number) throws Exception {
        Product product;
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        List<Product> lst;

        try {
            connection = DBconection.getConnection();
            String sql = "SELECT * FROM Product WHERE Category_id LIKE ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + number + "%");
            resultSet = statement.executeQuery();

            lst = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int categoryId = resultSet.getInt("category_Id");
                int quantityStock = resultSet.getInt("quantity_Stock");
                double productPrice = resultSet.getDouble("product_Price");

                product = new Product(id, name, categoryId, quantityStock, productPrice);
                lst.add(product);
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
