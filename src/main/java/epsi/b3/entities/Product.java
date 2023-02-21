package epsi.b3.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import epsi.b3.entities.enumtype.ProdType;

import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;
    private String code;
    private String label;
    @Enumerated(EnumType.STRING)
    private ProdType prodType;
    private Double price;

    @ManyToMany(cascade = CascadeType.ALL )
    @JoinTable(
            name = "product_petstore",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "petstore_id")
    )
    private Set<PetStore> petStores = new HashSet<>();

    public Product(String code, String label, ProdType prodType, Double price) {
        this.code = code;
        this.label = label;
        this.prodType = prodType;
        this.price = price;
    }
}
