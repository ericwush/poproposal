package input;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

import model.OrderStatus;
import model.ProductModel;
import model.Store;
import model.Warehouse;

public class StoreLoader {

    private String csvFilename;
    private final Set<Store> stores = new HashSet<>();

    public Set<Store> load(Set<ProductModel> models, Set<Warehouse> warehouses) {
        if (csvFilename == null || csvFilename.trim().length() == 0) {
            csvFilename = "store.csv";
        }
        String line = "";
        String cvsSplitBy = ",";
        try (BufferedReader input = new BufferedReader(
                new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(csvFilename)))) {
            while ((line = input.readLine()) != null) {
                if (line.startsWith("#")) {
                    continue;
                }
                String[] fields = line.split(cvsSplitBy);
                Store store = new Store(fields[0]);
                if (!stores.contains(store)) {
                    buildStore(models, warehouses, fields, store);
                    stores.add(store);
                } else {
                    for (Store st : stores) {
                        if (st.getName().equals(fields[0])) {
                            buildStore(models, warehouses, fields, st);
                            break;
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stores;
    }

    private void buildStore(Set<ProductModel> models, Set<Warehouse> warehouses,
            String[] fields, Store store) {
        ProductModel model;
        Warehouse warehouse;
        model = new ProductModel(fields[1]);
        if (!models.contains(model)) {
            throw new IllegalStateException("Product model " + model.getName() +
                    " is not defined in store file.");
        }
        warehouse = new Warehouse(fields[2]);
        if (!warehouses.contains(warehouse)) {
            throw new IllegalStateException("Warehouse " + warehouse.getName() +
                    " is not defined in store file.");
        }
        OrderStatusBuilder orderStatusBuilder = new OrderStatusBuilder();
        OrderStatus orderStatus = orderStatusBuilder
                .warehouse(fields[2])
                .soh(Integer.valueOf(fields[3]))
                .avgSales(Double.valueOf(fields[4]))
                .onOrder(Integer.valueOf(fields[5]))
                .ranking(Integer.valueOf(fields[6]))
                .build();
        store.getOrderStatus().put(model, orderStatus);
    }

    public void setCsvFilename(String csvFilename) {
        this.csvFilename = csvFilename;
    }

}
