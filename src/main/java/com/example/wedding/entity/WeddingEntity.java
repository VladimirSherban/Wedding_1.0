package com.example.wedding.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "wedding")
public class WeddingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    @Column(updatable = false)
    private Date createDate;
    @UpdateTimestamp
    private Date updateDate;
    private Date weddingDate;
    private String wifeFio;
    private String husbandFio;
    private Integer price;
    private String wifeNumber;
    private String husbandNumber;
    private Integer prepayment;
    private Integer leftToPay;
    private String comment;
    private Date deadLine;
    private Boolean deleted;
}
