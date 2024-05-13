package org.nikitakosh.estateagency.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import jdk.jfr.DataAmount;
import lombok.Data;
import org.hibernate.type.descriptor.java.IntegerPrimitiveArrayJavaType;
import org.nikitakosh.estateagency.models.enums.ApartmentStatus;
import org.nikitakosh.estateagency.models.enums.ApartmentType;


@Entity
@Data
@Table(name = "apartment")
public class Apartment {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "address")
    private String address;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private ApartmentType type;

    @Column(name = "area")
    private Integer area;

    @Column(name = "price")
    private Integer price;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ApartmentStatus status;

    @ManyToOne
    private Project project;

    @ManyToOne
    private User user;
}
