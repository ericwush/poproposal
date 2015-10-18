package model;

import java.math.BigDecimal;

public class OrderStatus {

    private int soh;
    private double avgSales;
    private int onOrder;
    private int ranking;
    private double wos;
    private double simEoh;
    private double simWos;
    private int proposal;
    private String warehouse;

    public int getSoh() {
        return soh;
    }

    public void setSoh(int soh) {
        this.soh = soh;
    }

    public double getAvgSales() {
        return avgSales;
    }

    public void setAvgSales(double avgSales) {
        this.avgSales = avgSales;
    }

    public int getOnOrder() {
        return onOrder;
    }
    public void setOnOrder(int onOrder) {
        this.onOrder = onOrder;
    }

    public double getSimEoh() {
        simEoh = getSoh() + getOnOrder() + getProposal() - getAvgSales();
        return simEoh;
    }

    public double getSimWos() {
        if (getAvgSales() == 0) {
            simWos = 0;
        } else {
            simWos = new BigDecimal(getSimEoh() / getAvgSales()).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
        }
        return simWos;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public int getProposal() {
        return proposal;
    }

    public void addProposal(int version) {
        if (version == 2 && this.proposal == 0) {
            this.proposal = this.proposal + 2;
        } else {
            this.proposal ++;
        }
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public double getWos() {
        if (getAvgSales() == 0) {
            wos = 0;
        } else {
            wos = new BigDecimal(getSoh() / getAvgSales()).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
        }
        return wos;
    }

    @Override
    public String toString() {
        return "OrderStatus [soh=" + soh + ", avgSales=" + avgSales
                + ", onOrder=" + onOrder + ", ranking=" + ranking + ", wos="
                + wos + ", simEoh=" + simEoh + ", simWos=" + simWos
                + ", proposal=" + proposal + ", warehouse=" + warehouse + "]";
    }

}
