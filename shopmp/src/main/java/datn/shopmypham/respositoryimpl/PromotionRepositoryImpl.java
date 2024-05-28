package datn.shopmypham.respositoryimpl;

import datn.shopmypham.entity.Promotion;
import datn.shopmypham.responsitory.DBconection;
import datn.shopmypham.responsitory.PromotionRepository;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class PromotionRepositoryImpl implements PromotionRepository {

    @Override
    public List<Promotion> getAllPromotion(String keyword, int page, int size) throws Exception {
        Promotion promotion;
        int offset = page * size;
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        List<Promotion> lst;

        try {
            connection = DBconection.getConnection();
            String sql = "Select promocode,discountpercent,startDate,endDate,order_id From promotion WHERE promocode LIKE ? ORDER BY promocode asc LIMIT ?, ? ";
            statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + keyword + "%");
            statement.setInt(2, offset);
            statement.setInt(3, size);
            resultSet = statement.executeQuery();

            lst = new ArrayList<>();

            while (resultSet.next()) {
                String promocode = resultSet.getString("promocode");
                String discountpercent = resultSet.getString("discountpercent");
                Date startDate = resultSet.getDate("startDate");
                Date endDate = resultSet.getDate("enddate");
                int orderId = resultSet.getInt("order_id");

                promotion = new Promotion(promocode, discountpercent, startDate, endDate, orderId);
                lst.add(promotion);

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
    public void addPromotion(Promotion promotion) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBconection.getConnection();
            String sql = "INSERT INTO promotion (id,promocode,discountpercent,startDate,endDate,order_Id) VALUES ( ?,?,?,?,?,?)";

            statement = connection.prepareStatement(sql);
            statement.setInt(1, promotion.getId());
            statement.setString(2, promotion.getPromocode());
            statement.setString(3, promotion.getDiscountpercent());
            statement.setDate(4, (java.sql.Date) promotion.getStartDate());
            statement.setDate(5, (java.sql.Date) promotion.getEndDate());
            statement.setInt(6, promotion.getOrderId());

            statement.executeUpdate();

        } catch (Exception ex) {
            throw ex;
        } finally {
            DBconection.closeStatement(statement);
            DBconection.closeConnection(connection);
        }
    }

    @Override
    public void deletePromotion(Integer promotionId) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBconection.getConnection();
            String sql = "DELETE FROM promotion WHERE id=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, promotionId);

            statement.executeUpdate();

        } catch (SQLException ex) {
            throw ex;
        } finally {
            DBconection.closeStatement(statement);
            DBconection.closeConnection(connection);
        }
    }

    @Override
    public void updatePromotion(Promotion promotion, Integer promotionId) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBconection.getConnection();
            String sql = "UPDATE User "
                    + "SET "
                    + "promocode= ?"
                    + ",discountpercent= ?"
                    + ",startDate= ?"
                    + ",endDate= ?"
                    + ",order_Id= ?"
                    + " WHERE id= ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, promotion.getPromocode());
            statement.setString(2, promotion.getDiscountpercent());
            statement.setDate(3, (java.sql.Date) promotion.getStartDate());
            statement.setDate(4, (java.sql.Date) promotion.getEndDate());
            statement.setInt(5, promotion.getOrderId());
            statement.setInt(6, promotionId);

            statement.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            DBconection.closeStatement(statement);
            DBconection.closeConnection(connection);
        }
    }
}
