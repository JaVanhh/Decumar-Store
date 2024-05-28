package datn.shopmypham.controller;

import datn.shopmypham.entity.Product;
import datn.shopmypham.service.AuthenticationService;
import datn.shopmypham.service.ProductService;
import datn.shopmypham.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/product/api")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService authenticationService;

    
    @GetMapping("/show")
    public ResponseEntity<?> getAllProduct(@RequestHeader("username") String username,
                                           @RequestHeader("token") String token) throws Exception {
        if (authenticationService.isValidTokenAndUsername(token, username)) {
            if (userService.checkUserRoleAdmin(username)) {
                return ResponseEntity.ok(productService.getAllProduct());
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You do not have permission to view the user list.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token or username.");
        }

    }

    
    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody Product product,
                                        @RequestHeader("username") String username,
                                        @RequestHeader("token") String token) throws Exception {

        if (authenticationService.isValidTokenAndUsername(token, username)) {
            if (userService.checkUserRoleAdmin(username)) {
                productService.addProduct(product);
                return ResponseEntity.ok("Thêm thành công !");
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You do not have permission to view the user list.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token or username.");
        }

    }

    
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteProduct(@RequestBody Product product) throws Exception {
        productService.deleteProduct(product);
        return ResponseEntity.ok("Xóa thành công !");
    }

    
    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestBody Product product) throws Exception {
        productService.updateProduct(product);
        return ResponseEntity.ok("Cập nhật thành công !");
    }

    
    @GetMapping("/pagination")
    public ResponseEntity<?> pagination(@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "5") int size) throws Exception {
        return ResponseEntity.ok(productService.pagination(page, size));
    }

    
    @GetMapping("/sortbyname")
    public ResponseEntity<?> sortByName() throws Exception {
        return ResponseEntity.ok(productService.sortProductByName());
    }

    
    @GetMapping("/searchbyname")
    public ResponseEntity<?> searchByName(@RequestParam("keyword") String keyword) throws Exception {
        return ResponseEntity.ok(productService.searchProductByName(keyword));
    }

    @GetMapping("/searchbycategory")
    public ResponseEntity<?> searchByCategoryId(@RequestParam("number") int number) throws Exception {
        return ResponseEntity.ok(productService.searchProductByCategoryId(number));
    }
}
