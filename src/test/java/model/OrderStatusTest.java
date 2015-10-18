package model;

import static org.junit.Assert.assertEquals;
import input.OrderStatusBuilder;

import org.junit.Test;

public class OrderStatusTest {

    @Test
    public void testWos() {
        OrderStatus orderStatus = new OrderStatusBuilder().avgSales(3).onOrder(1).soh(5).ranking(1).build();
        assertEquals("Should return correct Wos", 1.7, orderStatus.getWos(), 0);
        orderStatus = new OrderStatusBuilder().avgSales(3).onOrder(1).soh(7).ranking(1).build();
        assertEquals("Should return correct Wos", 2.3, orderStatus.getWos(), 0);
        orderStatus = new OrderStatusBuilder().avgSales(0.0).onOrder(1).soh(7).ranking(1).build();
        assertEquals("Should return correct Wos", 0, orderStatus.getWos(), 0);
    }

    @Test
    public void testSimEoh() {
        OrderStatus orderStatus = new OrderStatusBuilder().avgSales(3).onOrder(1).soh(5).ranking(1).build();
        assertEquals("Should return correct SimEoh", 3, orderStatus.getSimEoh(), 0);
        orderStatus = new OrderStatusBuilder().avgSales(3.5).onOrder(1).soh(5).ranking(1).build();
        assertEquals("Should return correct SimEoh", 2.5, orderStatus.getSimEoh(), 0);
    }

    @Test
    public void testSimWos() {
        OrderStatus orderStatus = new OrderStatusBuilder().avgSales(3).onOrder(1).soh(5).ranking(1).build();
        assertEquals("Should return correct SimWos", 1, orderStatus.getSimWos(), 0);
        orderStatus = new OrderStatusBuilder().avgSales(3.5).onOrder(1).soh(5).ranking(1).build();
        assertEquals("Should return correct SimWos", 0.7, orderStatus.getSimWos(), 0);
        orderStatus = new OrderStatusBuilder().avgSales(0).onOrder(1).soh(5).ranking(1).build();
        assertEquals("Should return correct SimWos", 0.0, orderStatus.getSimWos(), 0);
    }

}
