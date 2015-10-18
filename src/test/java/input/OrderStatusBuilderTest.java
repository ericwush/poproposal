package input;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import model.OrderStatus;

import org.junit.Test;

public class OrderStatusBuilderTest {

    @Test
    public void testOrderStatusBuilder() {
        OrderStatusBuilder builder = new OrderStatusBuilder();
        OrderStatus status = builder.avgSales(5.5).onOrder(1).ranking(12).soh(100).warehouse("WH1").build();
        assertEquals("Should build OrderStatus", status.getAvgSales(), 5.5, 0);
        assertEquals("Should build OrderStatus", status.getOnOrder(), 1, 0);
        assertEquals("Should build OrderStatus", status.getRanking(), 12, 0);
        assertEquals("Should build OrderStatus", status.getSoh(), 100, 0);
        assertTrue("Should build OrderStatus", status.getWarehouse().equals("WH1"));
    }

}
