package guru.qa.niffler.api.categories;

import guru.qa.niffler.api.ApiClient;
import guru.qa.niffler.model.CategoryJson;
import retrofit2.Response;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoriesApiClient extends ApiClient {
    private final CategoriesApi categoriesApi = retrofit.create(CategoriesApi.class);

    public CategoryJson createCategory(CategoryJson category) {
        final Response<CategoryJson> response;
        try {
            response = categoriesApi.addCategory(category).execute();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
        assertEquals(200, response.code());
        return response.body();
    }

    public CategoryJson updateCategory(CategoryJson category) {
        final Response<CategoryJson> response;
        try {
            response = categoriesApi.updateCategory(category).execute();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
        assertEquals(200, response.code());
        return response.body();
    }

    public List<CategoryJson> getAllCategories(String username, boolean excludeArchived) {
        final Response<List<CategoryJson>> response;
        try {
            response = categoriesApi.getCategories(username, excludeArchived).execute();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
        assertEquals(200, response.code());
        return response.body();
    }
}
