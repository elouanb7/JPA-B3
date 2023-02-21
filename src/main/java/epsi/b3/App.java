package epsi.b3;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import epsi.b3.entities.*;
import epsi.b3.entities.enumtype.FishLivEnv;
import epsi.b3.entities.enumtype.ProdType;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("petstore");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Product product1 = new Product("1234", "collier", ProdType.ACCESSORY, 19.00);
        Product product2 = new Product("5678", "os", ProdType.FOOD, 5.00);
        Product product3 = new Product("9999", "niche", ProdType.CLEANING, 27.00);

        Address address1 = new Address("66", "rue du parc", "75000", "Paris");
        Address address2 = new Address("77", "impasse des cons", "01373", "Nantes");
        Address address3 = new Address("88", "rue de la soif", "69420", "Rennes");

        PetStore petStore1 = new PetStore("ToutouMarket", "Thomas Henafffff", address1);
        petStore1.addProduct(product1);
        petStore1.addProduct(product2);
        petStore1.addProduct(product3);

        PetStore petStore2 = new PetStore("Tom's Shop", "Cameron Lecompte", address2);
        petStore2.addProduct(product1);
        petStore2.addProduct(product2);
        petStore2.addProduct(product3);

        PetStore petStore3 = new PetStore("Croquettas", "Romain Dillos", address3);
        petStore3.addProduct(product1);
        petStore3.addProduct(product2);
        petStore3.addProduct(product3);

        Animal animal1 = new Animal(LocalDate.now(), "noir", petStore1);
        Cat cat1 = new Cat(LocalDate.now(), "noir", petStore1, "2");
        Fish fish1 = new Fish(LocalDate.now(), "noir", petStore1, FishLivEnv.SEA_WATER);

        Animal animal2 = new Animal(LocalDate.now(), "tacheté", petStore2);
        Cat cat2 = new Cat(LocalDate.now(), "tacheté", petStore2, "chipid");
        Fish fish2 = new Fish(LocalDate.now(), "tacheté", petStore2, FishLivEnv.FRESH_WATER);

        Animal animal3 = new Animal(LocalDate.now(), "blanc", petStore3);
        Cat cat3 = new Cat(LocalDate.now(), "blanc", petStore3, "chipid");
        Fish fish3 = new Fish(LocalDate.now(), "blanc", petStore3, FishLivEnv.SEA_WATER);


        em.persist(product1);
        em.persist(product2);
        em.persist(product3);

        em.persist(petStore1);
        em.persist(petStore2);
        em.persist(petStore3);

        em.persist(animal1);
        em.persist(cat1);
        em.persist(fish1);
        em.persist(animal2);
        em.persist(cat2);
        em.persist(fish2);
        em.persist(animal3);
        em.persist(cat3);
        em.persist(fish3);


        em.getTransaction().commit();

        Query q = em.createQuery("select id, birth, color from Animal where petStore.id = 1");
        List resultList = q.getResultList();
        System.out.println("number of animals : " + resultList.size());
        for (Object next : resultList) {
            System.out.println("next animal: " + next);
        }

        em.close();
        emf.close();
    }
}
