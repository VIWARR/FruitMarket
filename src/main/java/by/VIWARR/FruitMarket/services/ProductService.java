package by.VIWARR.FruitMarket.services;

import by.VIWARR.FruitMarket.models.Product;
import by.VIWARR.FruitMarket.repositories.ProductRepository;
import by.VIWARR.FruitMarket.util.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll(Sort.by("name"));
    }

    @Transactional
    public void save(String name) {
        Product product = new Product(name);
        productRepository.save(product);
    }

    public Product findById(int id) {
        return productRepository.findById(id).orElseThrow(
                ProductNotFoundException::new
        );
    }
}
