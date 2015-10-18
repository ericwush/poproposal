package process;

import static org.junit.Assert.assertEquals;
import input.OrderStatusBuilder;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import model.OrderStatus;
import model.ProductModel;
import model.Store;
import model.StoreComparator;
import model.Warehouse;

import org.junit.Test;

public class StoreFilterTest {

    @Test
    public void testFilter() {
        ProductModel modelA = new ProductModel("ModelA");
        ProductModel modelB = new ProductModel("ModelB");
        Warehouse wh1 = new Warehouse("WH1");
        Store store1 = new Store("Store1");
        Store store2 = new Store("Store2");
        Store store3 = new Store("Store3");
        Store store4 = new Store("Store4");
        OrderStatus orderStatus1 = new OrderStatusBuilder().warehouse("WH1").avgSales(3).onOrder(1).soh(5).ranking(1).build();
        OrderStatus orderStatus2 = new OrderStatusBuilder().warehouse("WH2").avgSales(3.5).onOrder(1).soh(5).ranking(2).build();
        OrderStatus orderStatus3 = new OrderStatusBuilder().warehouse("WH1").avgSales(3).onOrder(1).soh(5).ranking(3).build();
        OrderStatus orderStatus4 = new OrderStatusBuilder().warehouse("WH1").avgSales(3.5).onOrder(1).soh(5).ranking(4).build();
        store1.getOrderStatus().put(modelB, orderStatus1);
        store2.getOrderStatus().put(modelA, orderStatus2);
        store3.getOrderStatus().put(modelA, orderStatus3);
        store4.getOrderStatus().put(modelA, orderStatus4);
        StoreComparator comparator = new StoreComparator();
        comparator.setModel(modelA);
        Set<Store> origin = new HashSet<>();
        origin.add(store1);
        origin.add(store2);
        origin.add(store3);
        origin.add(store4);
        StoreFilter filter = new StoreFilter();
        filter.setStores(origin);
        filter.setComparator(comparator);
        TreeSet<Store> filtered = filter.filterByWarehouseAndModel(wh1, modelA);
        assertEquals("Filtered set should have 2 stores.", 2, filtered.size());
        assertEquals("Store4 should come first.", store4, filtered.first());
        assertEquals("Store3 should come last.", store3, filtered.last());
    }

}
