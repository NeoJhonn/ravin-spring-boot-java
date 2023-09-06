package br.com.devxlabs.ravin.controllers;

import br.com.devxlabs.ravin.models.dtos.ProductDTO;
import br.com.devxlabs.ravin.models.entities.Product;
import br.com.devxlabs.ravin.services.ProductService;
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
public class ProductController {

    @Autowired//intância por debaixo dos panos o service
    ProductService service;

    @GetMapping("/list-all")// "/api/products/list-all"
    public List<ProductDTO> listAll() {
        return service.listAll();
    }

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
    public void update(@Valid @RequestBody ProductDTO product, @PathVariable Integer id) {// vai receber um JSON como parâmetro
        System.out.println(product.toString());          // internamente o Spring converte em objeto Java

    //notação @Valid valida se o JSON passado esta formatado da forma correta

        ProductDTO existingProduct = service.findById(id);

       // if (existingProduct == null) {
         //   return ResponseEntity.notFound().build();
        //}

        //try {
            // Copie os atributos do productDTO para o existingProduct usando o BeanUtils
         // BeanUtils.copyProperties(existingProduct, productDTO);
        //} catch (Exception e) {
            // Lidar com exceções, se ocorrerem
           // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                 //   .body("Erro ao copiar os atributos do DTO para o Produto.");
       // }

        // Salve a atualização no banco de dados
        //productService.save(existingProduct);

    }


}
