package datn.shopmypham.responsitory;

import datn.shopmypham.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository {

    public List<Product> getAllProduct() throws Exception;

    public void addProduct(Product product) throws Exception;

    public void deleteProduct(Product product) throws Exception;

    public void updateProduct(Product product) throws Exception;

    public List<Product> pagination(int page, int size) throws Exception;

    public List<Product> sortProductByName() throws Exception;

    public List<Product> searchProductByName(String keyword) throws Exception;

    public List<Product> searchProductByCategoryId(int number) throws Exception;

}
