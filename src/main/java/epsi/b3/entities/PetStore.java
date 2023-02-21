package epsi.b3.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class PetStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String managerName;
    @Embedded
    private Address address;

    @ManyToMany(mappedBy = "petStores")
    private Set<Product> products = new HashSet<>();

    @OneToMany(mappedBy = "petStore")
    private Set<Animal> animals;

    public void addProduct(Product product) {
        this.products.add(product);
        product.getPetStores().add(this);
    }

    public PetStore(String name, String managerName, Address address) {
        this.name = name;
        this.managerName = managerName;
        this.address = address;
    }
}
