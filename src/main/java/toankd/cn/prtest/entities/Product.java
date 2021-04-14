package toankd.cn.prtest.entities;

import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Data
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 20)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "quantity")
    private BigInteger quantity;

    @Column(name = "category")
    private String category;

    @Column(name = "description")
    private String description;

    @Column(name = "manufacturer")
    private String manufacturer;
}
