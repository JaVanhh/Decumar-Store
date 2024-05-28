package datn.shopmypham.service.impl;

import datn.shopmypham.entity.Product;
import datn.shopmypham.responsitory.ProductRepository;
import datn.shopmypham.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceIpml implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceIpml(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProduct() throws Exception {
        return productRepository.getAllProduct();
    }

    @Override
    public void addProduct(Product product) throws Exception {
        productRepository.addProduct(product);
    }

    @Override
    public void deleteProduct(Product product) throws Exception {
        productRepository.deleteProduct(product);
    }

    @Override
    public void updateProduct(Product product) throws Exception {
        productRepository.updateProduct(product);
    }

    @Override
    public List<Product> pagination(int page, int size) throws Exception {
        return productRepository.pagination(page, size);
    }

    @Override
    public List<Product> sortProductByName() throws Exception {
        return productRepository.sortProductByName();
    }

    @Override
    public List<Product> searchProductByName(String keyword) throws Exception {
        return productRepository.searchProductByName(keyword);
    }

    @Override
    public List<Product> searchProductByCategoryId(int number) throws Exception {
        return productRepository.searchProductByCategoryId(number);
    }
}
