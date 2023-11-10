package com.dimasDermawanJBusIO.controller;

import com.dimasDermawanJBusIO.Algorithm;
import com.dimasDermawanJBusIO.Bus;
import com.dimasDermawanJBusIO.dbjson.JsonTable;
import com.dimasDermawanJBusIO.dbjson.Serializable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface BasicGetController<T extends Serializable> {
    abstract JsonTable<T> getJsonTable();

    @GetMapping("/page")
    default List<T> getPage(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pageSize) {
        return Algorithm.paginate(getJsonTable(), page, pageSize, t -> true);
    }

    @GetMapping("/{id}")
    default T getById(@PathVariable int id) {
        return Algorithm.<T>find(getJsonTable(), t -> t.id == id);
    }
}
