package com.jooskim.demo.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "BookOrder")
public class Order implements Serializable {
    static final long serializableUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ManyToOne(targetEntity = Person.class)
    private Person orderedBy;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the orderedBy
     */
    public Person getOrderedBy() {
        return orderedBy;
    }

    /**
     * @param orderedBy the orderedBy to set
     */
    public void setOrderedBy(Person orderedBy) {
        this.orderedBy = orderedBy;
    }
}