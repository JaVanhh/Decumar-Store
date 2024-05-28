package datn.shopmypham.service;

import datn.shopmypham.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    public List<Category> getAllCategory() throws Exception;

    public void addCategory(Category category) throws Exception;

    public void deleteCategory(Category category) throws Exception;

    public void updateCategory(Category category) throws Exception;

    public List<Category> pagination(int page, int size) throws Exception;

    public List<Category> sortCategoryByName() throws Exception;

    public List<Category> searchCategoryByName(String keyword) throws Exception;

}
