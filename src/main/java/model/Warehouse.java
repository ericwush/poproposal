package model;

import java.util.HashMap;
import java.util.Map;

public class Warehouse {

    private final String name;
    private final Map<ProductModel, Integer> allocation = new HashMap<>();

    public Warehouse(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Map<ProductModel, Integer> getAllocation() {
        return allocation;
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
        Warehouse other = (Warehouse) obj;
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
        allocation.forEach((model, quantity) -> sb.append("Model: " + model.getName() + ": " + quantity + "\n") );
        return "Warehouse [name=" + name + ", \n" + sb.toString() + "]";
    }

}
