package product;

import com.lucasribeiro.anotaai.domain.category.Category;
import com.lucasribeiro.anotaai.domain.product.Product;
import com.lucasribeiro.anotaai.domain.product.ProductDto;
import com.lucasribeiro.anotaai.repositories.ProductRepository;
import com.lucasribeiro.anotaai.services.CategoryService;
import com.lucasribeiro.anotaai.services.ProductService;
import com.lucasribeiro.anotaai.services.aws.AwsSnsService;
import com.lucasribeiro.anotaai.services.aws.MessageDto;
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

public class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;

    @Mock
    CategoryService categoryService;

    @Mock
    AwsSnsService awsSnsService;

    private Product product;

    private ProductDto productDto;

    private Category category;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        category = new Category();
        product = new Product("id", "teste", "description", "333", 33, new Category());
        productDto = new ProductDto("Teste", "descrition", "23627", 33, "2121");
    }

    @Test
    void must_save_new_product() {
        Mockito.when(productRepository.save(any(Product.class))).thenReturn(product);
        Mockito.when(categoryService.getById(productDto.categoryId())).thenReturn(Optional.of(category));

        var result = productService.insert(productDto);

        Mockito.verify(awsSnsService).pusblishMessage(any(MessageDto.class));
        Assertions.assertEquals(product, result);
    }

    @Test
    void must_call_method_save() {
        Mockito.when(categoryService.getById(productDto.categoryId())).thenReturn(Optional.of(category));

        productService.insert(productDto);

        Mockito.verify(productRepository).save(any(Product.class));
        Mockito.verify(categoryService).getById(productDto.categoryId());
    }

    @Test
    void must_get_all_products() {
        Mockito.when(productRepository.findAll()).thenReturn(List.of(product));

        var result = productService.getAll();

        Assertions.assertEquals(List.of(product), result);
    }

    @Test
    void must_call_and_update_product() {
        Mockito.when(productRepository.findById("123")).thenReturn(Optional.of(product));
        Mockito.when(productRepository.save(any(Product.class))).thenReturn(product);
        Mockito.when(categoryService.getById(productDto.categoryId())).thenReturn(Optional.of(category));

        var result = productService.update("123", productDto);

        Assertions.assertEquals(product, result);
        Mockito.verify(awsSnsService).pusblishMessage(any(MessageDto.class));
        Mockito.verify(productRepository).save(any(Product.class));
        Mockito.verify(categoryService).getById(productDto.categoryId());
        Mockito.verify(productRepository).findById("123");
    }

    @Test
    void must_delete_product() {
        Mockito.when(productRepository.findById("123")).thenReturn(Optional.of(product));

        productService.delete("123");

        Mockito.verify(productRepository).delete(any(Product.class));
    }
}