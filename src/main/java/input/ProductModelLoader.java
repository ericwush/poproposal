package input;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

import model.ProductModel;

public class ProductModelLoader {

    private String csvFilename;
    private final Set<ProductModel> models = new HashSet<>();

    public Set<ProductModel> load() {
        if (csvFilename == null || csvFilename.trim().length() == 0) {
            csvFilename = "model.csv";
        }
        String line = "";
        try (BufferedReader input = new BufferedReader(
                new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(csvFilename)))) {
            while ((line = input.readLine()) != null) {
                if (line.startsWith("#")) {
                    continue;
                }
                models.add(new ProductModel(line));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return models;
    }

    public void setCsvFilename(String csvFilename) {
        this.csvFilename = csvFilename;
    }

}
