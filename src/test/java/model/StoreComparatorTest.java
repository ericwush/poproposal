package model;

import static org.junit.Assert.assertTrue;
import input.OrderStatusBuilder;

import org.junit.Test;

public class StoreComparatorTest {

    @Test
    public void testDifferentSimWos() {
        ProductModel model = new ProductModel("ModelA");
        Store store1 = new Store("Store1");
        Store store2 = new Store("Store2");
        OrderStatus orderStatus1 = new OrderStatusBuilder().avgSales(3).onOrder(1).soh(5).ranking(1).build();
        OrderStatus orderStatus2 = new OrderStatusBuilder().avgSales(3.5).onOrder(1).soh(5).ranking(2).build();
        store1.getOrderStatus().put(model, orderStatus1);
        store2.getOrderStatus().put(model, orderStatus2);
        StoreComparator comparator = new StoreComparator();
        comparator.setModel(model);
        assertTrue("Store1 should be greater than Store2", comparator.compare(store1, store2) > 0);
    }

    @Test
    public void testSameSimWos() {
        ProductModel model = new ProductModel("ModelA");
        Store store1 = new Store("Store1");
        Store store2 = new Store("Store2");
        OrderStatus orderStatus1 = new OrderStatusBuilder().avgSales(3).onOrder(1).soh(5).ranking(1).build();
        OrderStatus orderStatus2 = new OrderStatusBuilder().avgSales(3).onOrder(1).soh(5).ranking(2).build();
        store1.getOrderStatus().put(model, orderStatus1);
        store2.getOrderStatus().put(model, orderStatus2);
        StoreComparator comparator = new StoreComparator();
        comparator.setModel(model);
        assertTrue("Store1 should be less than Store2", comparator.compare(store1, store2) < 0);
    }

}
