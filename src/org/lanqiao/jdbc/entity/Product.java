package org.lanqiao.jdbc.entity;

import java.util.Objects;

public class Product {
    private int id;
    private String productName;
    private int dir_id;
    private double salePrice;
    private String supplier;
    private String brand;
    private double cutoff;
    private double costPrice;

    public Product(){

    }

    public Product(String productName, int dir_id, double salePrice, String supplier, String brand, double cutoff, double costPrice) {
        this.productName = productName;
        this.dir_id = dir_id;
        this.salePrice = salePrice;
        this.supplier = supplier;
        this.brand = brand;
        this.cutoff = cutoff;
        this.costPrice = costPrice;
    }

    public Product(int id, String productName, int dir_id, double salePrice, String supplier, String brand, double cutoff, double costPrice) {
        this.id = id;
        this.productName = productName;
        this.dir_id = dir_id;
        this.salePrice = salePrice;
        this.supplier = supplier;
        this.brand = brand;
        this.cutoff = cutoff;
        this.costPrice = costPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getDir_id() {
        return dir_id;
    }

    public void setDir_id(int dir_id) {
        this.dir_id = dir_id;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getCutoff() {
        return cutoff;
    }

    public void setCutoff(double cutoff) {
        this.cutoff = cutoff;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                dir_id == product.dir_id &&
                Double.compare(product.salePrice, salePrice) == 0 &&
                Double.compare(product.cutoff, cutoff) == 0 &&
                Double.compare(product.costPrice, costPrice) == 0 &&
                Objects.equals(productName, product.productName) &&
                Objects.equals(supplier, product.supplier) &&
                Objects.equals(brand, product.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productName, dir_id, salePrice, supplier, brand, cutoff, costPrice);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", dir_id=" + dir_id +
                ", salePrice=" + salePrice +
                ", supplier='" + supplier + '\'' +
                ", brand='" + brand + '\'' +
                ", cutoff=" + cutoff +
                ", costPrice=" + costPrice +
                '}';
    }
}
