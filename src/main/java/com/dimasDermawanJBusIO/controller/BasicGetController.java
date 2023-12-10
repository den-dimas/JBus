package com.dimasDermawanJBusIO.controller;

import com.dimasDermawanJBusIO.Account;
import com.dimasDermawanJBusIO.Algorithm;
import com.dimasDermawanJBusIO.Bus;
import com.dimasDermawanJBusIO.dbjson.JsonTable;
import com.dimasDermawanJBusIO.dbjson.Serializable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * BasicGetController defines basic CRUD GET endpoints for a REST controller managing a JSON table entity.
 * Subinterfaces will provide implementation to return the {@link JsonTable} to operate on.
 *
 * @param <T> The Serializable entity type stored in the JSON table
 */
@RestController
public interface BasicGetController<T extends Serializable> {

    /**
     * Gets the JSON table that stores the entities.
     * Implementing controllers provide the table instance.
     *
     * @return The JSON table storing the T entities
     */
    abstract JsonTable<T> getJsonTable();

    /**
     * Retrieves a page of entities from the table.
     * Applies default paging parameters if not specified.
     *
     * @param page The page number (zero indexed)
     * @param pageSize The number of entities per page
     * @return A list containing the page of entities
     * @see Algorithm
     */
    @GetMapping("/page")
    default List<T> getPage(@RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "10") int pageSize) {
        return Algorithm.paginate(getJsonTable(), page, pageSize, t -> true);
    }

    /**
     * Retrieves a single entity by its unique ID.
     *
     * @param id The entity's unique ID
     * @return The matching T entity
     * @throws RuntimeException If no entity with given ID exists
     * @see Algorithm
     */
    @GetMapping("/{id}")
    default T getById(@PathVariable int id) {
        return Algorithm.<T>find(getJsonTable(), t -> t.id == id);
    }
}
