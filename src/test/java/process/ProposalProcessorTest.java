package process;

import static org.junit.Assert.assertEquals;
import input.OrderStatusBuilder;

import java.util.HashSet;
import java.util.Set;

import model.OrderStatus;
import model.ProductModel;
import model.Store;
import model.StoreComparator;
import model.Warehouse;

import org.junit.Test;

public class ProposalProcessorTest {

    @Test
    public void testProcessorV1() {
        ProductModel modelA = new ProductModel("ModelA");
        ProductModel modelB = new ProductModel("ModelB");
        Warehouse wh1 = new Warehouse("WH1");
        wh1.getAllocation().put(modelA, 5);
        wh1.getAllocation().put(modelB, 3);
        Warehouse wh2 = new Warehouse("WH2");
        wh2.getAllocation().put(modelA, 4);
        wh2.getAllocation().put(modelB, 5);
        OrderStatus orderStatus1A = new OrderStatusBuilder().avgSales(5).ranking(1).onOrder(2).soh(5).warehouse("WH1").build();
        OrderStatus orderStatus2A = new OrderStatusBuilder().avgSales(0).ranking(10).onOrder(3).soh(10).warehouse("WH1").build();
        OrderStatus orderStatus3A = new OrderStatusBuilder().avgSales(1).ranking(15).onOrder(3).soh(10).warehouse("WH2").build();
        OrderStatus orderStatus1B = new OrderStatusBuilder().avgSales(5).ranking(1).onOrder(2).soh(5).warehouse("WH1").build();
        OrderStatus orderStatus2B = new OrderStatusBuilder().avgSales(5).ranking(10).onOrder(2).soh(5).warehouse("WH1").build();
        OrderStatus orderStatus3B = new OrderStatusBuilder().avgSales(3).ranking(15).onOrder(1).soh(8).warehouse("WH1").build();
        Store store1 = new Store("Store1");
        Store store2 = new Store("Store2");
        Store store3 = new Store("Store3");
        store1.getOrderStatus().put(modelA, orderStatus1A);
        store1.getOrderStatus().put(modelB, orderStatus1B);
        store2.getOrderStatus().put(modelA, orderStatus2A);
        store2.getOrderStatus().put(modelB, orderStatus2B);
        store3.getOrderStatus().put(modelA, orderStatus3A);
        store3.getOrderStatus().put(modelB, orderStatus3B);
        Set<Warehouse> warehouses = new HashSet<>();
        warehouses.add(wh1);
        warehouses.add(wh2);
        Set<Store> stores = new HashSet<>();
        stores.add(store1);
        stores.add(store2);
        stores.add(store3);
        StoreComparator comparator = new StoreComparator();
        StoreFilter filter = new StoreFilter();
        ProposalProcessor processor = new ProposalProcessor(warehouses, stores, filter, comparator, 1);
        Set<Store> processed = processor.process();
        processed.forEach(store -> System.out.println(store));
        assertEquals("Processed set should have 3 stores.", 3, processed.size());
    }

    @Test
    public void testProcessorV2() {
        ProductModel modelA = new ProductModel("ModelA");
        ProductModel modelB = new ProductModel("ModelB");
        Warehouse wh1 = new Warehouse("WH1");
        wh1.getAllocation().put(modelA, 5);
        wh1.getAllocation().put(modelB, 3);
        Warehouse wh2 = new Warehouse("WH2");
        wh2.getAllocation().put(modelA, 4);
        wh2.getAllocation().put(modelB, 5);
        OrderStatus orderStatus1A = new OrderStatusBuilder().avgSales(5).ranking(1).onOrder(2).soh(5).warehouse("WH1").build();
        OrderStatus orderStatus2A = new OrderStatusBuilder().avgSales(0).ranking(10).onOrder(3).soh(10).warehouse("WH1").build();
        OrderStatus orderStatus3A = new OrderStatusBuilder().avgSales(1).ranking(15).onOrder(3).soh(10).warehouse("WH2").build();
        OrderStatus orderStatus1B = new OrderStatusBuilder().avgSales(5).ranking(1).onOrder(2).soh(5).warehouse("WH1").build();
        OrderStatus orderStatus2B = new OrderStatusBuilder().avgSales(5).ranking(10).onOrder(2).soh(5).warehouse("WH1").build();
        OrderStatus orderStatus3B = new OrderStatusBuilder().avgSales(3).ranking(15).onOrder(1).soh(8).warehouse("WH1").build();
        Store store1 = new Store("Store1");
        Store store2 = new Store("Store2");
        Store store3 = new Store("Store3");
        store1.getOrderStatus().put(modelA, orderStatus1A);
        store1.getOrderStatus().put(modelB, orderStatus1B);
        store2.getOrderStatus().put(modelA, orderStatus2A);
        store2.getOrderStatus().put(modelB, orderStatus2B);
        store3.getOrderStatus().put(modelA, orderStatus3A);
        store3.getOrderStatus().put(modelB, orderStatus3B);
        Set<Warehouse> warehouses = new HashSet<>();
        warehouses.add(wh1);
        warehouses.add(wh2);
        Set<Store> stores = new HashSet<>();
        stores.add(store1);
        stores.add(store2);
        stores.add(store3);
        StoreComparator comparator = new StoreComparator();
        StoreFilter filter = new StoreFilter();
        ProposalProcessor processor = new ProposalProcessor(warehouses, stores, filter, comparator, 2);
        Set<Store> processed = processor.process();
        processed.forEach(store -> System.out.println(store));
        assertEquals("Processed set should have 3 stores.", 3, processed.size());
    }

}
