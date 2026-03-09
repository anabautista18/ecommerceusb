package co.edu.usbcali.ecommerceusb.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "inventory_movements")
public class InventoryMovement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "product_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_inventory_movements_product"),
            referencedColumnName = "id"
    )
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "order_id",
            foreignKey = @ForeignKey(name = "fk_inventory_movements_order"),
            referencedColumnName = "id"
    )
    private Order order; // nullable

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "qty", nullable = false)
    private Integer qty;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = OffsetDateTime.now();
        this.updatedAt = OffsetDateTime.now();
        validate();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = OffsetDateTime.now();
        validate();
    }

    private void validate() {
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo no puede ser nulo o vacío");
        }
        if (qty == null || qty == 0) {
            throw new IllegalArgumentException("La cantidad debe ser diferente de 0");
        }
    }
}
