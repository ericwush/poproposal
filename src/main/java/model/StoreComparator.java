package model;

import java.util.Comparator;

public class StoreComparator implements Comparator<Store> {

    private ProductModel model;

    @Override
    public int compare(Store store1, Store store2) {
        if (model == null) {
            throw new IllegalStateException("ProductModel is not set.");
        }
        OrderStatus orderStatus1 = store1.getOrderStatus().get(model);
        OrderStatus orderStatus2 = store2.getOrderStatus().get(model);
        int compare = Double.valueOf(orderStatus1.getSimWos()).
                compareTo(Double.valueOf(orderStatus2.getSimWos()));
        if (compare != 0) {
            return compare;
        }
        return Integer.valueOf(orderStatus1.getRanking()).
                compareTo(Integer.valueOf(orderStatus2.getRanking()));
    }

    public void setModel(ProductModel model) {
        this.model = model;
    }

}
