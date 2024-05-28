package datn.shopmypham.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @NotEmpty(message = "Thiếu số điện thoại")
    @Column(name = "phone")
    @Pattern(regexp = "\\d{10}", message = "Số điện thoại phải có 10 số")
    private String phone;
    @NotEmpty(message = "Thiếu username")
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    @Email(message = "Email không hợp lệ")
    private String email;
    @NotEmpty(message = "Thiếu password")
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private String role;
    private String url;
    @OneToOne
    @JoinColumn(name = "id")
    @JsonIgnore
    private Image image;

    public User(int id, String name, @NotEmpty(message = "Thiếu số điện thoại") @Pattern(regexp = "\\d{10}", message = "Số điện thoại phải có 10 số") String phone, @NotEmpty(message = "Thiếu username") String username, @Email(message = "Email không hợp lệ") String email, String role) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.username = username;
        this.email = email;
        this.role = role;
    }
}

