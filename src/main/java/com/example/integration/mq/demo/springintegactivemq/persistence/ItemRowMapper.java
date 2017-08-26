package com.example.integration.mq.demo.springintegactivemq.persistence;

import com.example.integration.mq.demo.springintegactivemq.domain.Item;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemRowMapper implements RowMapper<Item> {
    @Override
    public Item mapRow(ResultSet resultSet, int i) throws SQLException {
        Item item = new Item();
        item.setId(resultSet.getInt("id"));
        item.setType(resultSet.getInt("type"));
        return item;
    }
}
