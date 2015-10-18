package output;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

import model.Store;

public class OutputWriter {

    private String filename;
    private final Set<Store> stores;

    public OutputWriter(Set<Store> stores) {
        this.stores = stores;
    }

    public void generateCsvFile() {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.append("Store");
            writer.append(',');
            writer.append("Model");
            writer.append(',');
            writer.append("Warehouse");
            writer.append(',');
            writer.append("Proposal");
            writer.append(',');
            writer.append("AvgSales");
            writer.append(',');
            writer.append("OnOrder");
            writer.append(',');
            writer.append("SimEoh");
            writer.append(',');
            writer.append("SimWos");
            writer.append(',');
            writer.append("Soh");
            writer.append(',');
            writer.append("Wos");
            writer.append(',');
            writer.append("Ranking");
            writer.append('\n');

            stores.forEach(store -> {
                store.getOrderStatus().forEach((model, orderStatus) -> {
                    try {
                        writer.append(store.getName());
                        writer.append(',');
                        writer.append(model.getName());
                        writer.append(',');
                        writer.append(orderStatus.getWarehouse());
                        writer.append(',');
                        writer.append(Integer.toString(orderStatus.getProposal()));
                        writer.append(',');
                        writer.append(Double.toString(orderStatus.getAvgSales()));
                        writer.append(',');
                        writer.append(Integer.toString(orderStatus.getOnOrder()));
                        writer.append(',');
                        writer.append(Double.toString(orderStatus.getSimEoh()));
                        writer.append(',');
                        writer.append(Double.toString(orderStatus.getSimWos()));
                        writer.append(',');
                        writer.append(Integer.toString(orderStatus.getSoh()));
                        writer.append(',');
                        writer.append(Double.toString(orderStatus.getWos()));
                        writer.append(',');
                        writer.append(Integer.toString(orderStatus.getRanking()));
                        writer.append('\n');
                    } catch (Exception e) {
                        throw new IllegalStateException("Error occurred when writing to file " + filename, e);
                    }
                });
            });
            writer.flush();
        } catch (IOException e) {
            throw new IllegalStateException("Error occurred when writing to file " + filename, e);
        }
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

}
