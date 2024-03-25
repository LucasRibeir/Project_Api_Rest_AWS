package category;

import com.lucasribeiro.anotaai.domain.category.Category;
import com.lucasribeiro.anotaai.domain.category.CategoryDto;
import com.lucasribeiro.anotaai.repositories.CategoryRepository;
import com.lucasribeiro.anotaai.services.CategoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

public class CategoryServiceTest {

    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    CategoryService categoryService;

    private CategoryDto categoryDto;

    private Category category;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        categoryDto = new CategoryDto("Ex", "Description", "789");
        category = new Category(categoryDto);
    }

    @Test
    void must_save_a_new_category() {
        Mockito.when(categoryRepository.save(any(Category.class))).thenReturn(category);

        var result = categoryService.insert(categoryDto);

        Assertions.assertEquals(category, result);
    }

    @Test
    void must_call_a_save_method() {
        categoryService.insert(categoryDto);

        Mockito.verify(categoryRepository).save(any(Category.class));
    }

    @Test
    void must_search_all_categories() {
        Mockito.when(categoryRepository.findAll()).thenReturn(List.of(category));

        var result = categoryService.getAll();

        Assertions.assertEquals(List.of(category), result);
    }

    @Test
    void must_update_categories() {
        Mockito.when(categoryRepository.findById("123")).thenReturn(Optional.of(category));
        Mockito.when(categoryRepository.save(any(Category.class))).thenReturn(category);

        var result = categoryService.update("123", categoryDto);

        Assertions.assertEquals(result, category);
        Mockito.verify(categoryRepository).findById("123");
        Mockito.verify(categoryRepository).save(category);
    }

    @Test
    void must_delete_categories() {
        Mockito.when(categoryRepository.findById("123")).thenReturn(Optional.of(category));

        categoryService.delete("123");

        Mockito.verify(categoryRepository).delete(any(Category.class));
    }
}