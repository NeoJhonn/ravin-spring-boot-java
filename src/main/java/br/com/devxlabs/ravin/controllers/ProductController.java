package br.com.devxlabs.ravin.controllers;

import br.com.devxlabs.ravin.models.dtos.ProductDTO;
import br.com.devxlabs.ravin.models.entities.Product;
import br.com.devxlabs.ravin.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/api/products")
@Tag(name = "Produto", description = "Endpoints relacionados ao produto") // customizando UI do Swagger
public class ProductController {

    @Autowired//intância por debaixo dos panos o service
    ProductService service;


    @Operation(description = "Lista todos os produtos existentes", method = "GET")// customizando UI do Swagger
    @ApiResponses(value = {// customizando UI do Swagger
            @ApiResponse(responseCode = "200", description = "Lista com todos os produtos") })// customizando UI do Swagger
    @GetMapping("/list-all")// "/api/products/list-all"
    public List<ProductDTO> listAll() {
        return service.listAll();
    }

    @Operation(description = "Busca um produto pelo id", method = "GET")// customizando UI do Swagger
    @ApiResponses(// customizando UI do Swagger
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna o produto encontrado com determinado id"),// customizando UI do Swagger
                    @ApiResponse(responseCode = "404", description = "Nenhum produto encontrado com o id especificado")// customizando UI do Swagger
            })
    @GetMapping(value = "/{id}") // "/api/products/1"
    public ResponseEntity<ProductDTO> findById(@PathVariable int id) {// @PathVariable indica o campo do caminho da api entre {id}
        ProductDTO product = service.findById(id);

        // Usar ResponseEntity para dar uma resposta pro front caso seja null
        if (product == null) {
            return  ResponseEntity.notFound().build();
        }


        return ResponseEntity.ok(product);
    }

    @DeleteMapping(value = "/{id}")// "/api/products/1"
    public void deleteById(@PathVariable int id) {// @PathVariable indica o campo do caminho da api entre {id}
        service.deleteById(id);
    }

    @GetMapping("/search")// "/search?name=Jhonny&productType=FOOD&minSalePrice=9.65&maxSalePrice=15.98"
    public List<ProductDTO> search(
            @RequestParam("name") String name,
            @RequestParam("productType") String productType,
            @RequestParam("minSalePrice") float minSalePrice,
            @RequestParam("maxSalePrice") float maxSalePrice,
            @RequestParam(value= "page", defaultValue = "0") Integer page,
            @RequestParam(value = "orderBy", defaultValue = "id", required = false) String orderBy,
            @RequestParam(value = "itensPerPage", defaultValue = "10", required = false) Integer itensPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC", required = false) String direction
    ) {
        return service.search(name, productType, minSalePrice, maxSalePrice,
                              page, orderBy, itensPerPage, direction);
    }

    @PostMapping // /api/products
    public ResponseEntity<Object> create(@Valid @RequestBody ProductDTO product) {// vai receber um JSON como parâmetro
        //notação @Valid valida se o JSON passado esta formatado da forma correta

        try {
            return ResponseEntity.ok(service.create(product));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/{id}")// "/api/products/13"
    public ResponseEntity<Object> update(@Valid @RequestBody ProductDTO product, @PathVariable Integer id) {// vai receber um JSON como parâmetro

        //notação @Valid valida se o JSON passado esta formatado da forma correta

        ProductDTO existingProduct = service.findById(id);

        if (existingProduct == null) {
            return ResponseEntity.notFound().build();
        }

        try {
            // Copie os atributos de product para o existingProduct usando o BeanUtils
            BeanUtils.copyProperties(product, existingProduct);
            // Salve a atualização no banco de dados
            return ResponseEntity.ok(service.update(existingProduct));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
