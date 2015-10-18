package input;

import java.util.HashSet;
import java.util.Set;

import model.ProductModel;
import model.Store;
import model.Warehouse;

import org.junit.Test;

public class StoreLoaderTest {

    @Test
    public void testSuccessfulLoader() {
        StoreLoader loader = new StoreLoader();
        Set<ProductModel> models = new HashSet<>();
        models.add(new ProductModel("ModelA"));
        models.add(new ProductModel("ModelB"));
        models.add(new ProductModel("ModelC"));
        Set<Warehouse> warehouses = new HashSet<>();
        warehouses.add(new Warehouse("WH1"));
        warehouses.add(new Warehouse("WH2"));
        Set<Store> stores = loader.load(models, warehouses);
        stores.stream().forEach(store -> System.out.println(store.toString()));
    }

}
