package input;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

import model.ProductModel;
import model.Warehouse;

public class WarehouseLoader {

    private String csvFilename;
    private final Set<Warehouse> warehouses = new HashSet<>();

    public Set<Warehouse> load(Set<ProductModel> models) {
        if (csvFilename == null || csvFilename.trim().length() == 0) {
            csvFilename = "warehouse.csv";
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
                Warehouse warehouse = new Warehouse(fields[0]);
                if (!warehouses.contains(warehouse)) {
                    buildWarehouse(models, fields, warehouse);
                    warehouses.add(warehouse);
                } else {
                    for (Warehouse wh : warehouses) {
                        if (wh.getName().equals(fields[0])) {
                            buildWarehouse(models, fields, wh);
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
        return warehouses;
    }

    private void buildWarehouse(Set<ProductModel> models, String[] fields,
            Warehouse warehouse) {
        ProductModel model;
        model = new ProductModel(fields[1]);
        if (!models.contains(model)) {
            throw new IllegalStateException("Product model " + model.getName() +
                    " is not defined in warehouse file.");
        }
        warehouse.getAllocation().put(model, Integer.valueOf(fields[2]));
    }

    public void setCsvFilename(String csvFilename) {
        this.csvFilename = csvFilename;
    }

}
