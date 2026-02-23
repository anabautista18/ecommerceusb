package co.edu.usbcali.ecommerceusb.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "inventory_movements")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryMovement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order; // nullable

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "qty", nullable = false)
    private Integer qty;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;
}
