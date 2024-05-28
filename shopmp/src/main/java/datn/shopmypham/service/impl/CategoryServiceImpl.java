package datn.shopmypham.service.impl;

import datn.shopmypham.entity.Category;
import datn.shopmypham.responsitory.CategoryRepository;
import datn.shopmypham.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRespository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRespository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategory() throws Exception {
        return categoryRespository.getAllCategory();
    }

    @Override
    public void addCategory(Category category) throws Exception {
        categoryRespository.addCategory(category);
    }

    @Override
    public void deleteCategory(Category category) throws Exception {
        categoryRespository.deleteCategory(category);
    }

    @Override
    public void updateCategory(Category category) throws Exception {
        categoryRespository.updateCategory(category);
    }

    @Override
    public List<Category> pagination(int page, int size) throws Exception {
        return categoryRespository.pagination(page, size);
    }

    @Override
    public List<Category> sortCategoryByName() throws Exception {
        return categoryRespository.sortCategoryByName();
    }

    @Override
    public List<Category> searchCategoryByName(String keyword) throws Exception {
        return categoryRespository.searchCategoryByName(keyword);
    }
}
