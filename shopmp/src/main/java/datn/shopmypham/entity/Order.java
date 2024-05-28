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

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "order_date")
    private Date orderDate;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "total")
    private double total;
    @Column(name = "delivery_fee")
    private double deliveryFee;
    @Column(name = "voucher_value")
    private double voucherValue;
    @Column(name = "shipment_date")
    private Date shipmentDate;

    public Order(int id, Date orderDate, int quantity, double total, double deliveryFee, double voucherValue, Date shipmentDate) {
        this.id = id;
        this.orderDate = orderDate;
        this.quantity = quantity;
        this.total = total;
        this.deliveryFee = deliveryFee;
        this.voucherValue = voucherValue;
        this.shipmentDate = shipmentDate;
    }
}
