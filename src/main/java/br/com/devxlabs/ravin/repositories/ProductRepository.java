package br.com.devxlabs.ravin.repositories;

import br.com.devxlabs.ravin.models.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByName(String name);
    Optional<Product> findByCostPrice(Double costPrice);
    // Fazendo as Queries "na mão"
    @Query("SELECT p from Product p where p.costPrice <= :costPrice AND p.salePrice >= :salePrice")
    List<Product> findByCostPriceAndSalesPrice(Double costPrice, Double salePrice);

    @Query("SELECT p.name FROM Product p")
    List<String> findProductNames();

    @Query(nativeQuery = true, value = "SELECT name FROM Product WHERE productType = :productType AND createdDate = TODAY()")
    List<String> fidProductNamesByProductTypeInsertedToday(String productType);

    Page<Product> findAll(Pageable pageable);
}
