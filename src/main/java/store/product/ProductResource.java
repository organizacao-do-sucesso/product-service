package store.product;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class ProductResource implements ProductController {

    @Autowired
    private ProductService productService;

    @Override
    public ResponseEntity<ProductOut> create(ProductIn in) {
        ProductOut out = ProductParser.to(productService.create(ProductParser.to(in)));
        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(out.id())
            .toUri();
        return ResponseEntity.created(location).body(out);
    }

    @Override
    public ResponseEntity<List<ProductOut>> findAll() {
        return ResponseEntity.ok(ProductParser.to(productService.findAll()));
    }

    @Override
    public ResponseEntity<ProductOut> findById(String id) {
        return ResponseEntity.ok(ProductParser.to(productService.findById(id)));
    }

    @Override
    public ResponseEntity<Void> delete(String id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> healthCheck() {
        return ResponseEntity.ok().build();
    }
}
