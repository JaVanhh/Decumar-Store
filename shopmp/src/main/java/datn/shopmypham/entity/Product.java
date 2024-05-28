package datn.shopmypham.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "category_id")
    private int categoryId;

    @Column(name = "quantity_stock")
    private int quantityStock;

    @Column(name = "product_price")
    private double productPrice;

    public Product(int id, String name, int categoryId, int quantityStock, double productPrice) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.quantityStock = quantityStock;
        this.productPrice = productPrice;
    }
}
