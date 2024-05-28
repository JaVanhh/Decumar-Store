package datn.shopmypham.responsitory;

import datn.shopmypham.entity.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository {

    public List<Order> getAllOrder() throws Exception;

    public void addOrder(Order order) throws Exception;

    public void deleteOrder(Order order) throws Exception;

    public void updateOrder(Order order) throws Exception;

    public List<Order> pagination(int page, int size) throws Exception;

    public List<Order> sortOrderByQuantity() throws Exception;

    public List<Order> searchOrderBy(String keyword) throws Exception;
}
