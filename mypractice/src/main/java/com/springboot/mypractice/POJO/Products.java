package com.springboot.mypractice.POJO;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias(value = "products")
public class Products implements Serializable {

    private static final long serialVersionUID = 3849645291357173083L;
    private Long id;
    private String name;
    private int stock;
    private double price;
    private int version;
    private String note;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
