package datn.shopmypham.controller;

import datn.shopmypham.entity.Category;
import datn.shopmypham.service.AuthenticationService;
import datn.shopmypham.service.CategoryService;
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
@RequestMapping("/category/api")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService authenticationService;


    @GetMapping("/show")
    public ResponseEntity<?> getAllCategory(@RequestHeader("username") String username,
                                            @RequestHeader("token") String token) throws Exception {
        if (authenticationService.isValidTokenAndUsername(token, username)) {
            if (userService.checkUserRoleAdmin(username)) {
                return ResponseEntity.ok(categoryService.getAllCategory());
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You do not have permission to view the user list.");

            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token or username.");
        }
    }


    @PostMapping("/add")
    public ResponseEntity<?> addCategory(@RequestBody Category category,
                                         @RequestHeader("username") String username,
                                         @RequestHeader("token") String token) throws Exception {
        if (authenticationService.isValidTokenAndUsername(token, username)) {
            if (userService.checkUserRoleAdmin(username)) {
                categoryService.addCategory(category);
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You do not have permission to view the user list.");

            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token or username.");
        }

        return ResponseEntity.ok("Thêm thành công !");
    }


    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteCategory(@RequestBody Category category,
                                            @RequestHeader("username") String username,
                                            @RequestHeader("token") String token) throws Exception {
        if (authenticationService.isValidTokenAndUsername(token, username)) {
            if (userService.checkUserRoleAdmin(username)) {
                categoryService.deleteCategory(category);
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You do not have permission to view the user list.");

            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token or username.");
        }
        return ResponseEntity.ok("Xóa thành công !");
    }


    @PutMapping("/update")
    public ResponseEntity<?> updateCategory(@RequestBody Category category,
                                            @RequestHeader("username") String username,
                                            @RequestHeader("token") String token) throws Exception {
        if (authenticationService.isValidTokenAndUsername(token, username)) {
            if (userService.checkUserRoleAdmin(username)) {
                categoryService.updateCategory(category);
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You do not have permission to view the user list.");

            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token or username.");
        }

        return ResponseEntity.ok("Cập nhật thành công !");
    }


    @GetMapping("/pagination")
    public ResponseEntity<?> pagination(@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "5") int size) throws Exception {
        return ResponseEntity.ok(categoryService.pagination(page, size));
    }


    @GetMapping("/sortbyname")
    public ResponseEntity<?> sortByName() throws Exception {
        return ResponseEntity.ok(categoryService.sortCategoryByName());
    }


    @GetMapping("/searchbyname")
    public ResponseEntity<?> searchByName(@RequestParam("keyword") String keyword) throws Exception {
        return ResponseEntity.ok(categoryService.searchCategoryByName(keyword));
    }
}
