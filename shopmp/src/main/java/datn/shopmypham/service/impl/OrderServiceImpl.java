package datn.shopmypham.service.impl;

import datn.shopmypham.entity.Order;
import datn.shopmypham.responsitory.OrderRepository;
import datn.shopmypham.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderResponsitory;

    public OrderServiceImpl(OrderRepository orderResponsitory) {
        this.orderResponsitory = orderResponsitory;
    }

    @Override
    public List<Order> getAllOrder() throws Exception {
        return orderResponsitory.getAllOrder();
    }

    @Override
    public void addOrder(Order order) throws Exception {
        orderResponsitory.addOrder(order);
    }

    @Override
    public void deleteOrder(Order order) throws Exception {
        orderResponsitory.deleteOrder(order);
    }

    @Override
    public void updateOrder(Order order) throws Exception {
        orderResponsitory.updateOrder(order);
    }

    @Override
    public List<Order> pagination(int page, int size) throws Exception {
        return orderResponsitory.pagination(page, size);
    }

    @Override
    public List<Order> sortOrderByQuantity() throws Exception {
        return orderResponsitory.sortOrderByQuantity();
    }

    @Override
    public List<Order> searchOrderBy(String keyword) throws Exception {
        return orderResponsitory.searchOrderBy(keyword);
    }
}
