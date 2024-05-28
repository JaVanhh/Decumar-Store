package datn.shopmypham.respositoryimpl;

import datn.shopmypham.entity.FeedBack;
import datn.shopmypham.responsitory.DBconection;
import datn.shopmypham.responsitory.FeedBackRepository;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FeedBackRepositoryImpl implements FeedBackRepository {
    @Override
    public List<FeedBack> getAllFeedBack(String keyword, int page, int size) throws Exception {
        FeedBack feedBack;
        int offset = page * size;
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        List<FeedBack> lst;

        try {
            connection = DBconection.getConnection();
            String sql = "Select user_id,product_id,content From feedback WHERE content LIKE ? ORDER BY content asc LIMIT ?, ? ";
            statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + keyword + "%");
            statement.setInt(2, offset);
            statement.setInt(3, size);
            resultSet = statement.executeQuery();

            lst = new ArrayList<>();

            while (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                int productId = resultSet.getInt("product_id");
                String content = resultSet.getString("content");

                feedBack = new FeedBack(userId, productId, content);
                lst.add(feedBack);

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
    public void addFeedBack(FeedBack feedBack) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBconection.getConnection();
            String sql = "INSERT INTO feedback (content) VALUES (?)";

            statement = connection.prepareStatement(sql);
            statement.setString(1, feedBack.getContent());

            statement.executeUpdate();

        } catch (Exception ex) {
            throw ex;
        } finally {
            DBconection.closeStatement(statement);
            DBconection.closeConnection(connection);
        }
    }

    @Override
    public void deleteFeedBack(Integer feedBackId) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBconection.getConnection();
            String sql = "DELETE FROM feedback WHERE id=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, feedBackId);

            statement.executeUpdate();

        } catch (SQLException ex) {
            throw ex;
        } finally {
            DBconection.closeStatement(statement);
            DBconection.closeConnection(connection);
        }
    }

    @Override
    public void updateFeedBack(FeedBack feedBack, Integer feedBackId) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBconection.getConnection();
            String sql = "UPDATE feedback "
                    + "SET "
                    + "content= ?"
                    + " WHERE id= ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, feedBack.getContent());
            statement.setInt(2, feedBackId);

            statement.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            DBconection.closeStatement(statement);
            DBconection.closeConnection(connection);
        }
    }
}

