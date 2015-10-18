package model;

import java.util.HashMap;
import java.util.Map;

public class Store {

    private final String name;
    private final Map<ProductModel, OrderStatus> orderStatus = new HashMap<>();

    public Store(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Map<ProductModel, OrderStatus> getOrderStatus() {
        return orderStatus;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Store other = (Store) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        orderStatus.forEach((model, status) -> sb.append("Model: " + model.getName() + ": " + status + "\n") );
        return "Store [name=" + name + ", \n" + sb.toString() + "]";
    }

}
