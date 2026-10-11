package store.product;

import java.util.List;

public final class ProductParser {

    private ProductParser() {
    }

    public static ProductOut to(Product product) {
        return product == null ? null : ProductOut.builder()
            .id(product.id())
            .name(product.name())
            .price(product.price())
            .unit(product.unit())
            .build();
    }

    public static List<ProductOut> to(List<Product> products) {
        return products.stream().map(ProductParser::to).toList();
    }

    public static Product to(ProductIn in) {
        return in == null ? null : Product.builder()
            .name(in.name())
            .price(in.price())
            .unit(in.unit())
            .build();
    }
}
