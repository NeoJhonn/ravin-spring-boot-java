package br.com.devxlabs.ravin.services;

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
        return  null;
    }

    public void deleteById(long id) {
        System.out.println("Produto excluido com sucesso!");
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
}
