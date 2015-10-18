package process;

import java.util.Set;
import java.util.TreeSet;

import model.OrderStatus;
import model.Store;
import model.StoreComparator;
import model.Warehouse;

public class ProposalProcessor {

    private final Set<Warehouse> warehouses;
    private final Set<Store> stores;
    private final StoreFilter filter;
    private final StoreComparator comparator;
    private final int version;

    public ProposalProcessor(Set<Warehouse> warehouses, Set<Store> stores, StoreFilter filter,
            StoreComparator comparator, int version) {
        this.warehouses = warehouses;
        this.stores = stores;
        this.filter = filter;
        this.comparator = comparator;
        this.version = version;
    }

    public Set<Store> process() {
        warehouses.forEach(warehouse -> {
            warehouse.getAllocation().forEach((model, quantity) -> {
                comparator.setModel(model);
                filter.setComparator(comparator);
                filter.setStores(stores);
                TreeSet<Store> filtered = filter.filterByWarehouseAndModel(warehouse, model);
                if (filtered.size() > 0) {
                    stores.removeAll(filtered);
                    boolean allocated = false;
                    boolean out = false;
                    while (quantity > 0) {
                        Store allocatedStore = null;
                        for (Store nextStore : filtered) {
                            allocated = false;
                            out = false;
                            OrderStatus orderStatus = nextStore.getOrderStatus().get(model);
                            if (orderStatus.getSimWos() != 0) {
                                allocated = true;
                            } else {
                                if (orderStatus.getAvgSales() > 0) {
                                    allocated = true;
                                    out = true;
                                } else {
                                    if (version == 1 && orderStatus.getSimEoh() == 1) {
                                        allocated = true;
                                        out = true;
                                    }
                                    if (version == 2 && orderStatus.getSimEoh() < (4 * orderStatus.getAvgSales())) {
                                        allocated = true;
                                    }
                                }
                            }
                            if (allocated) {
                                allocatedStore = nextStore;
                                break;
                            }
                        }
                        if (allocatedStore != null) {
                            filtered.remove(allocatedStore);
                            allocatedStore.getOrderStatus().get(model).addProposal(version);
                            if (!out) {
                                filtered.add(allocatedStore);
                            } else {
                                stores.add(allocatedStore);
                            }
                            quantity --;
                        } else {
                            break;
                        }
                    }
                    stores.addAll(filtered);
                }
            });
        });
        return stores;
    }

}
