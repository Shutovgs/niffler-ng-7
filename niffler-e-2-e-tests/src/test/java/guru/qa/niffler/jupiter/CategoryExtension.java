package guru.qa.niffler.jupiter;

import guru.qa.niffler.api.categories.CategoriesApiClient;
import guru.qa.niffler.model.CategoryJson;
import guru.qa.niffler.utils.DataGenerator;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.platform.commons.support.AnnotationSupport;

public class CategoryExtension implements BeforeEachCallback, ParameterResolver, AfterTestExecutionCallback {

    public static final ExtensionContext.Namespace NAMESPACE = ExtensionContext.Namespace.create(CategoryExtension.class);

    private final CategoriesApiClient categoriesApiClient = new CategoriesApiClient();

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        AnnotationSupport.findAnnotation(context.getRequiredTestMethod(), Category.class)
            .ifPresent(anno -> {
                CategoryJson category = new CategoryJson(
                    null,
                    DataGenerator.randomCategory(),
                    anno.username(),
                    false
                );
                CategoryJson createdCategory = categoriesApiClient.createCategory(category);

                if (anno.archived()) {
                    CategoryJson archivedCategory = new CategoryJson(
                        createdCategory.id(),
                        createdCategory.name(),
                        createdCategory.username(),
                        true
                    );
                    createdCategory = categoriesApiClient.updateCategory(archivedCategory);
                }

                context.getStore(NAMESPACE).put(context.getUniqueId(), createdCategory);
            });
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().isAssignableFrom(CategoryJson.class);
    }

    @Override
    public CategoryJson resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return extensionContext.getStore(NAMESPACE).get(extensionContext.getUniqueId(), CategoryJson.class);
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        CategoryJson category = context.getStore(CategoryExtension.NAMESPACE).get(context.getUniqueId(), CategoryJson.class);
        // создаем объект с archived = true
        CategoryJson archivedCategory = new CategoryJson(
            category.id(),
            category.name(),
            category.username(),
            true
        );
        categoriesApiClient.updateCategory(archivedCategory);
    }
}
