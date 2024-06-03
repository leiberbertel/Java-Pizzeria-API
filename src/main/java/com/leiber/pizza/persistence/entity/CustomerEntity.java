package com.leiber.pizza.persistence.entity;

import com.leiber.pizza.persistence.audit.Auditable;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Entidad que representa un cliente en la base de datos.
 * Hereda los campos de auditoría de la clase {@link Auditable}.
 * @author Leiber Bertel
 */
@Entity
@Table(name = "customer")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
public class CustomerEntity extends Auditable {

    @Id
    @Column(name = "id_customer", nullable = false, length = 15)
    @Schema(description = "Unique ID of the customer.", example = "C123456")
    private String idCustomer;

    @Column(nullable = false, length = 60)
    @Schema(description = "Full name of the customer.", example = "John Doe")
    private String name;

    @Column(nullable = false, length = 100)
    @Schema(description = "Address of the customer.", example = "1234 Main St, Anytown, USA")
    private String address;

    @Column(nullable = false, unique = true, length = 50)
    @Schema(description = "Email address of the customer.", example = "customer@example.com")
    private String email;

    @Column(name = "phone_number", nullable = false, length = 20)
    @Schema(description = "Phone number of the customer.", example = "+1234567890")
    private String phoneNumber;

}
