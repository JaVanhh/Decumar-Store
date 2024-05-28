package datn.shopmypham.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "promotion")
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "promocode")
    private String promocode;
    @Column(name = "discountpercent")
    private String discountpercent;
    @Column(name = "startdate")
    private Date startDate;
    @Column(name = "enddate")
    private Date endDate;
    @Column(name = "order_id")
    private int orderId;

    public Promotion(String promocode, String discountpercent, Date startDate, Date endDate, int orderId) {
        this.promocode = promocode;
        this.discountpercent = discountpercent;
        this.startDate = startDate;
        this.endDate = endDate;
        this.orderId = orderId;
    }
}
