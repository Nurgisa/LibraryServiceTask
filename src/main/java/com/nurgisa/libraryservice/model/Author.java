package com.nurgisa.libraryservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by User on 10.05.2019.
 */

@Entity
@Table(name = "authors")
public class Author extends BaseEntity {


    @Column(name = "name")
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
