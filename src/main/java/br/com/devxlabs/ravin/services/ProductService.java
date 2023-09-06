package br.com.devxlabs.ravin.services;


import static br.com.devxlabs.ravin.consts.ExceptionConsts.*;
import br.com.devxlabs.ravin.models.dtos.ProductDTO;
import br.com.devxlabs.ravin.models.entities.Product;
import br.com.devxlabs.ravin.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {// todas as validações e regras de negócio devem ser feitas nos services

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ModelMapper mapper;

    public List<ProductDTO> listAll() {
        List<ProductDTO> products = productRepository.findAll().stream()
                .map(p -> mapper.map(p, ProductDTO.class)).toList();

        return products;
    }

    public ProductDTO findById(long id) {
        Optional<Product> optional = productRepository.findById(id);
        ProductDTO productDTO = null;

        // se dentro do optinal  tem um product
        if (optional.isPresent()) {
            productDTO = mapper.map(optional.get(), ProductDTO.class);
        }

        return  productDTO;
    }

    public void deleteById(long id) {
        productRepository.deleteById(id);
    }

    public List<ProductDTO> search(String nome,
                                   String productType,
                                   double minSalePrice,
                                   double maxSalePrice,
                                   Integer page,
                                   String orderBy,
                                   Integer itensPerPage,
                                   String direction
    ) {
        System.out.println("Buscando produtos com filtros.");

        Pageable pageable = PageRequest.of(page,itensPerPage, Sort.Direction.fromString(direction));

        Page<Product> products = productRepository.findAll(pageable);

        return null;
    }

    public Long create(ProductDTO productDTO) throws Exception {
        return save(productDTO);
    }

    public long update(ProductDTO productDTO) throws Exception {
        return save(productDTO);
    }

    public Long save(ProductDTO productDTO) throws Exception {
        try {
            if (productDTO.getCostPrice() > productDTO.getSalePrice()) {
                throw  new Exception(PRODUCT_COST_PRICE_GRATHER_THEN_SALE_PRICE);
            }


            Product product = mapper.map(productDTO, Product.class);
            Product created = productRepository.save(product);
            return created.getId();
        } catch (Exception e) {
            throw new Exception(PRODUCT_INSERT_ERROR);
        }
    }

}
