package com.example.integration.mq.demo.springintegactivemq.activemqhelloconsumer.domain;

public class Item {
    
    private Integer id;
    private Integer type;
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getType() {
        return type;
    }
    
    public void setType(Integer type) {
        this.type = type;
    }
    
    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", type=" + type +
                '}';
    }
}
