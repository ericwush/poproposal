package process;

import java.util.Set;
import java.util.TreeSet;

import model.OrderStatus;
import model.ProductModel;
import model.Store;
import model.StoreComparator;
import model.Warehouse;

public class StoreFilter {

    private Set<Store> stores;
    private StoreComparator comparator;

    public TreeSet<Store> filterByWarehouseAndModel(Warehouse warehouse, ProductModel model) {
        if (comparator == null) {
            throw new IllegalStateException("StoreComparator is not set.");
        }
        TreeSet<Store> filtered = new TreeSet<>(comparator);
        stores.stream()
            .filter(store -> store.getOrderStatus().containsKey(model))
            .forEach(store -> {
                OrderStatus orderStatus = store.getOrderStatus().get(model);
                if (orderStatus.getWarehouse().equals(warehouse.getName())) {
                    filtered.add(store);
                }
            });
        return filtered;
    }

    public void setComparator(StoreComparator comparator) {
        this.comparator = comparator;
    }

    public void setStores(Set<Store> stores) {
        this.stores = stores;
    }

}
