package datn.shopmypham.respositoryimpl;

import datn.shopmypham.entity.Order;
import datn.shopmypham.responsitory.DBconection;
import datn.shopmypham.responsitory.OrderRepository;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class OrderRepositoryImpl implements OrderRepository {
    @Override
    public List<Order> getAllOrder() throws Exception {
        Order order;
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        List<Order> lst;

        try {
            connection = DBconection.getConnection();
            String sql = "Select * From Orders";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            lst = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                Date orderDate = resultSet.getDate("order_Date");
                int quantity = resultSet.getInt("quantity");
                double total = resultSet.getDouble("total");
                double deliveryFee = resultSet.getDouble("delivery_Fee");
                double voucherValue = resultSet.getDouble("voucher_Value");
                Date shipmentDate = resultSet.getDate("shipment_Date");

                order = new Order(id, orderDate, quantity, total, deliveryFee, voucherValue, shipmentDate);

                lst.add(order);
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
    public void addOrder(Order order) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBconection.getConnection();
            String sql = "INSERT INTO Orders (id,order_Date,quantity,total,delivery_Fee,voucher_Value,shipment_Date) VALUES ( ?,?,?,?,?,?,?)";

            statement = connection.prepareStatement(sql);
            statement.setInt(1, order.getId());
            statement.setDate(2, (java.sql.Date) order.getOrderDate());
            statement.setInt(3, order.getQuantity());
            statement.setDouble(4, order.getTotal());
            statement.setDouble(5, order.getDeliveryFee());
            statement.setDouble(6, order.getVoucherValue());
            statement.setDate(7, (java.sql.Date) order.getOrderDate());
            statement.executeUpdate();

        } catch (Exception ex) {
            throw ex;
        } finally {
            DBconection.closeStatement(statement);
            DBconection.closeConnection(connection);
        }
    }

    @Override
    public void deleteOrder(Order order) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBconection.getConnection();
            String sql = "DELETE FROM Orders WHERE id=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, order.getId());

            statement.executeUpdate();

        } catch (SQLException ex) {
            throw ex;
        } finally {
            DBconection.closeStatement(statement);
            DBconection.closeConnection(connection);
        }
    }

    @Override
    public void updateOrder(Order order) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBconection.getConnection();
            String sql = "UPDATE Orders "
                    + "SET "
                    + "order_Date= ?"
                    + ",quantity= ?"
                    + ",total= ?"
                    + ",delivery_Fee= ?"
                    + ",voucher_Value= ?"
                    + ",shipment_Date= ?"
                    + " WHERE id= ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, order.getId());
            statement.setDate(2, (java.sql.Date) order.getOrderDate());
            statement.setInt(3, order.getQuantity());
            statement.setDouble(4, order.getTotal());
            statement.setDouble(5, order.getDeliveryFee());
            statement.setDouble(6, order.getVoucherValue());
            statement.setDate(7, (java.sql.Date) order.getOrderDate());

            statement.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            DBconection.closeStatement(statement);
            DBconection.closeConnection(connection);
        }
    }

    @Override
    public List<Order> pagination(int page, int size) throws Exception {
        Order order;
        int offset = page * size;
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        List<Order> lst;

        try {
            connection = DBconection.getConnection();
            String sql = "SELECT * FROM Orders LIMIT ?, ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, offset);
            statement.setInt(2, size);

            resultSet = statement.executeQuery();

            lst = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                Date orderDate = resultSet.getDate("order_Date");
                int quantity = resultSet.getInt("quantity");
                double total = resultSet.getDouble("total");
                double deliveryFee = resultSet.getDouble("delivery_Fee");
                double voucherValue = resultSet.getDouble("voucher_Value");
                Date shipmentDate = resultSet.getDate("shipment_Date");

                order = new Order(id, orderDate, quantity, total, deliveryFee, voucherValue, shipmentDate);

                lst.add(order);
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
    public List<Order> sortOrderByQuantity() throws Exception {
        Order order;
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        List<Order> lst;

        try {
            connection = DBconection.getConnection();
            String sql = "SELECT * FROM Orders ORDER BY quantity asc";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            lst = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                Date orderDate = resultSet.getDate("order_Date");
                int quantity = resultSet.getInt("quantity");
                double total = resultSet.getDouble("total");
                double deliveryFee = resultSet.getDouble("delivery_Fee");
                double voucherValue = resultSet.getDouble("voucher_Value");
                Date shipmentDate = resultSet.getDate("shipment_Date");

                order = new Order(id, orderDate, quantity, total, deliveryFee, voucherValue, shipmentDate);

                lst.add(order);
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
    public List<Order> searchOrderBy(String keyword) throws Exception {
        return null;
    }
}
