package process;

import input.ProductModelLoader;
import input.StoreLoader;
import input.WarehouseLoader;

import java.util.Set;

import model.ProductModel;
import model.Store;
import model.StoreComparator;
import model.Warehouse;
import output.OutputWriter;

public class RunProcessor {

    public static void main(String[] args) {
        if (args.length < 1) {
            throw new IllegalArgumentException("Must provide version number.");
        }
        int version;
        try {
            version = Integer.valueOf(args[0]);
        } catch (Exception e) {
            throw new IllegalArgumentException("First argument should version number, e.g. 1, 2...");
        }
        ProductModelLoader modelLoader = new ProductModelLoader();
        Set<ProductModel> models = modelLoader.load();
        System.out.println("Loading models...");
        models.forEach(model -> System.out.println(model));

        WarehouseLoader warehouseLoader = new WarehouseLoader();
        Set<Warehouse> warehouses = warehouseLoader.load(models);
        System.out.println("\nLoading warehouses...");
        warehouses.forEach(wh -> System.out.println(wh));

        StoreLoader storeLoader = new StoreLoader();
        Set<Store> stores = storeLoader.load(models, warehouses);
        System.out.println("\nLoading stores...");
        stores.forEach(store -> System.out.println(store));

        StoreComparator comparator = new StoreComparator();
        StoreFilter filter = new StoreFilter();
        ProposalProcessor processor = new ProposalProcessor(warehouses, stores, filter, comparator, version);
        System.out.println("\nProcessing...");
        Set<Store> processedStores = processor.process();
        processedStores.forEach(store -> System.out.println(store));

        OutputWriter writer = new OutputWriter(processedStores);
        if (args.length > 0 && args[1] != null && args[1].length() > 0) {
            writer.setFilename(args[1]);
        } else {
            writer.setFilename("output.csv");
        }
        writer.generateCsvFile();

    }

}
