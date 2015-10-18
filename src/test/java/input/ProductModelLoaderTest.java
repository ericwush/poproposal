package input;

import static org.junit.Assert.assertTrue;

import java.util.Set;

import model.ProductModel;

import org.junit.Test;

public class ProductModelLoaderTest {

    @Test
    public void testLoad() {
        ProductModelLoader loader = new ProductModelLoader();
        Set<ProductModel> models = loader.load();
        models.stream().forEach(model -> System.out.println(model));
        assertTrue("Should contain correct models", models.contains(new ProductModel("ModelA")));
        assertTrue("Should contain correct models", models.contains(new ProductModel("ModelB")));
        assertTrue("Should contain correct models", models.contains(new ProductModel("ModelC")));
    }

}
