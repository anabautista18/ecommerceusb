package co.edu.usbcali.ecommerceusb.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "inventory")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {
    @Id
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;
}
