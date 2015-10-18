package input;

import model.OrderStatus;

public class OrderStatusBuilder {

    private final OrderStatus orderStatus;

    public OrderStatusBuilder() {
        this.orderStatus = new OrderStatus();
    }

    public OrderStatus build() {
        return this.orderStatus;
    }

    public OrderStatusBuilder soh(int soh) {
        this.orderStatus.setSoh(soh);
        return this;
    }

    public OrderStatusBuilder avgSales(double avgSales) {
        this.orderStatus.setAvgSales(avgSales);
        return this;
    }

    public OrderStatusBuilder onOrder(int onOrder) {
        this.orderStatus.setOnOrder(onOrder);
        return this;
    }

    public OrderStatusBuilder ranking(int ranking) {
        this.orderStatus.setRanking(ranking);
        return this;
    }

    public OrderStatusBuilder warehouse(String warehouse) {
        this.orderStatus.setWarehouse(warehouse);
        return this;
    }

}
