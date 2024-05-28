package datn.shopmypham.controller;

import datn.shopmypham.entity.Order;
import datn.shopmypham.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/order/api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    
    @GetMapping("/show")
    public ResponseEntity<?> getAllOrder() throws Exception {
        return ResponseEntity.ok(orderService.getAllOrder());
    }

    
    @PostMapping("/add")
    public ResponseEntity<?> addOrder(@RequestBody Order order) throws Exception {
        orderService.addOrder(order);
        return ResponseEntity.ok("Thêm thành công !");
    }

    
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteOrder(@RequestBody Order order) throws Exception {
        orderService.deleteOrder(order);
        return ResponseEntity.ok("Xóa thành công !");
    }

    
    @PutMapping("/update")
    public ResponseEntity<?> updateOrder(@RequestBody Order order) throws Exception {
        orderService.updateOrder(order);
        return ResponseEntity.ok("Cập nhật thành công !");
    }

    
    @GetMapping("/pagination")
    public ResponseEntity<?> pagination(@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "5") int size) throws Exception {
        return ResponseEntity.ok(orderService.pagination(page, size));
    }

    
    @GetMapping("/sortbyname")
    public ResponseEntity<?> sortByName() throws Exception {
        return ResponseEntity.ok(orderService.sortOrderByQuantity());
    }

    
    @GetMapping("/searchbyname")
    public ResponseEntity<?> searchByName(@RequestParam("keyword") String keyword) throws Exception {
        return ResponseEntity.ok(orderService.searchOrderBy(keyword));
    }
}
