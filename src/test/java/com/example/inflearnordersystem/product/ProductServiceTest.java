package com.example.inflearnordersystem.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class ProductServiceTest {

    private ProductService productService;

    @BeforeEach
    void setUp(){
        productService = new ProductService();
    }

    @Test
    void 상품등록() {
        final String name = "상품명";
        final int price = 1000;
        final DiscountPolicy discountPolicy = DiscountPolicy.NONE;
        final AddProductRequest request = new AddProductRequest(name, price, discountPolicy);


        productService.addProduct(request);
    }

    private class ProductService{
        private ProductPort productPort;
        public void addProduct(final AddProductRequest request){
            final Product product = new Product(request.name, request.price, request.discountPolicy);

            productPort.save(product);
        }
    }

    private interface ProductPort{
        public void save(final Product product);
    }


    private class Product {
        public Product(final String name, final int price, final DiscountPolicy discountPolicy) {
            Assert.hasText(name, "상품명 필수");
            Assert.isTrue(price > 0, "상품가격은 0보다 큼");
            Assert.notNull(discountPolicy,"할인정책 필수");
        }
    }

    private class AddProductRequest {
        private final String name;
        private final int price;
        private final DiscountPolicy discountPolicy;

        public AddProductRequest(final String name, final int price, final DiscountPolicy discountPolicy){
            Assert.hasText(name, "상품명 필수");
            Assert.isTrue(price > 0, "상품가격은 0보다 큼");
            Assert.notNull(discountPolicy,"할인정책 필수");
            this.name = name;
            this.price = price;
            this.discountPolicy = discountPolicy;
        }
    }

    private enum DiscountPolicy {
        NONE
    }
}
