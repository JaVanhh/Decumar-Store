package datn.shopmypham.controller;

import datn.shopmypham.entity.Promotion;
import datn.shopmypham.service.AuthenticationService;
import datn.shopmypham.service.impl.TokenManager;
import datn.shopmypham.service.PromotionService;
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
@RequestMapping("/promotion/api")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService authenticationService;

    
    @GetMapping("/show")
    public ResponseEntity<?> getAllPromotion(
            @RequestHeader("username") String username,
            @RequestHeader("token") String token,
            @RequestParam(name = "keyword", defaultValue = "") String keyword,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "50") int size) throws Exception {

        if (authenticationService.isValidTokenAndUsername(token, username)) {
            if (userService.checkUserRoleSuperAdmin(username)) {
                return ResponseEntity.ok(promotionService.getAllPromotion(keyword, page, size));
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You do not have permission to view the user list.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token or username.");
        }
    }

    
    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestHeader("username") String username,
                                     @RequestHeader("token") String token,
                                     @RequestBody Promotion promotion) throws Exception {
        if (authenticationService.isValidTokenAndUsername(token, username)) {
            if (userService.checkUserRoleSuperAdmin(username)) {
                promotionService.addPromotion(promotion);
                return ResponseEntity.ok("Add successfully !");
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You do not have permission to view the user list.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token or username.");
        }
    }

    
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestHeader("username") String username,
                                        @RequestHeader("token") String token,
                                        @RequestParam Integer promotionId) throws Exception {
        if (authenticationService.isValidTokenAndUsername(token, username)) {
            if (userService.checkUserRoleSuperAdmin(username)) {
                promotionService.deletePromotion(promotionId);
                return ResponseEntity.ok("Delete successfully !");
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You do not have permission to view the user list.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token or username.");
        }
    }

    
    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestHeader("username") String username,
                                        @RequestHeader("token") String token,
                                        @RequestBody Promotion promotion,
                                        @RequestParam Integer promotionId) throws Exception {
        if (authenticationService.isValidTokenAndUsername(token, username)) {
            if (userService.checkUserRoleSuperAdmin(username)) {
                promotionService.updatePromotion(promotion, promotionId);
                return ResponseEntity.ok("Update successfully !");
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You do not have permission to view the user list.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token or username.");
        }
    }

}
