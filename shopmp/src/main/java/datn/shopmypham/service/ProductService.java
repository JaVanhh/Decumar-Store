package datn.shopmypham.service;

import datn.shopmypham.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    public List<Product> getAllProduct() throws Exception;

    public void addProduct(Product product) throws Exception;

    public void deleteProduct(Product product) throws Exception;

    public void updateProduct(Product product) throws Exception;

    public List<Product> pagination(int page, int size) throws Exception;

    public List<Product> sortProductByName() throws Exception;

    public List<Product> searchProductByName(String keyword) throws Exception;

    public List<Product> searchProductByCategoryId(int number) throws Exception;



}
