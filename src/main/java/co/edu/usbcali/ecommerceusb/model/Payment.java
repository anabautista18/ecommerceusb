package co.edu.usbcali.ecommerceusb.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "order_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_payments_order"),
            referencedColumnName = "id"
    )
    private Order order;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "provider_ref")
    private String providerRef;

    @Column(name = "idempotency_key", nullable = false)
    private String idempotencyKey;

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
        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("El estado no puede ser nulo o vacío");
        }
        if (idempotencyKey == null || idempotencyKey.trim().isEmpty()) {
            throw new IllegalArgumentException("La clave de idempotencia no puede ser nula o vacía");
        }
    }
}
