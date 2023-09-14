package br.com.devxlabs.ravin.services;



import static org.assertj.core.api.Assertions.assertThat;


import static br.com.devxlabs.ravin.consts.ExceptionConsts.*;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.*;

import br.com.devxlabs.ravin.enums.ProductType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import br.com.devxlabs.ravin.models.dtos.ProductDTO;
import br.com.devxlabs.ravin.models.entities.Product;

import br.com.devxlabs.ravin.repositories.ProductRepository;

import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @Mock
    ModelMapper mapper;

    @InjectMocks
    ProductService productService;

    @Test
    public void save_ShouldSave() throws Exception {
        // Arrange
        Product product = createProduct();
        Long createdProductId = product.getId();
        Product createdProduct = createProduct();
        ProductDTO productDTO = createProductDTO();
        when(mapper.map(productDTO, Product.class)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(createdProduct);

        // Act
        Long id = productService.save(productDTO);

        // Assert
        verify(mapper).map(productDTO, Product.class);
        verify(productRepository, times(1)).save(product);
        assertThat(id).isEqualTo(createdProductId);
    }

    @Test
    public void save_ShouldntSave() {
        // Arrange
        Product product = createProduct();
        ProductDTO productDTO = createProductDTO();
        when(mapper.map(productDTO, Product.class)).thenReturn(product);
        when(productRepository.save(product)).thenThrow(new IllegalArgumentException());

        // Act
        Throwable exception = catchThrowable(() -> productService.save(productDTO));

        // Asserts
        assertThat(exception.getMessage()).isEqualTo(PRODUCT_INSERT_ERROR);
    }

    public ProductDTO createProductDTO() {
        ProductDTO productDTO = new ProductDTO(1, "Hamburguer", "Veggie", "8df98", 12.9, 20.5, "10 minutos",
                ProductType.SNACK,"", true);

        return productDTO;
    }

    public Product createProduct() {
		Product product = new Product(1, "Hamburguer", "Veggie", "8df98", 12.9, 20.5, "10 minutos", "",
				ProductType.SNACK, true, "", new Date(), "",new Date());
		return product;
    }

}
