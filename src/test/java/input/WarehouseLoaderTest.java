package input;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import model.ProductModel;
import model.Warehouse;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class WarehouseLoaderTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testSuccessfulLoad() {
        WarehouseLoader loader = new WarehouseLoader();
        Set<ProductModel> models = new HashSet<>();
        models.add(new ProductModel("ModelA"));
        models.add(new ProductModel("ModelB"));
        models.add(new ProductModel("ModelC"));
        Set<Warehouse> warehouses = loader.load(models);
        warehouses.stream().forEach(warehouse -> System.out.println(warehouse.toString()));
        assertTrue("Should contain correct warehouses", warehouses.contains(new Warehouse("WH1")));
        assertTrue("Should contain correct warehouses", warehouses.contains(new Warehouse("WH2")));
    }

    @Test
    public void testProductModelDoesNotExist() {
        WarehouseLoader loader = new WarehouseLoader();
        Set<ProductModel> models = new HashSet<>();
        models.add(new ProductModel("ModelA"));
        models.add(new ProductModel("ModelB"));
        exception.expect(IllegalStateException.class);
        loader.load(models);
    }

}
