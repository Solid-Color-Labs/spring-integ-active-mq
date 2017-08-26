package com.example.integration.mq.demo.springintegactivemq.domain;

import java.io.Serializable;

public class Item implements Serializable {
    
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
    
}
