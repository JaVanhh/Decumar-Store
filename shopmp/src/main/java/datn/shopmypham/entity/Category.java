package datn.shopmypham.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @NotEmpty(message = "Thiếu tên danh mục")
    @Column(name = "name")
    private String name;
    @NotEmpty(message = "Thiếu mã danh mục")
    @Column(name = "product_quantity")
    private int productQuantity;
    @Column(name = "image_id")
    private int imageId;

    public Category(int id, @NotEmpty(message = "Thiếu tên danh mục") String name, @NotEmpty(message = "Thiếu mã danh mục") int productQuantity) {
        this.id = id;
        this.name = name;
        this.productQuantity = productQuantity;
    }
}
