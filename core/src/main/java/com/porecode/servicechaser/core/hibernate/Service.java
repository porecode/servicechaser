package com.porecode.servicechaser.core.hibernate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: fealaer
 * Date: 25.09.11
 */

@Entity
@Table(name = "services")
public class Service {
    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "producers_services",
            joinColumns =
            @JoinColumn(name = "service_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "producer_id", referencedColumnName = "id")
    )
    private Set<Producer> producers = new HashSet<Producer>();

    public Service() {
    }

    private Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Producer> getProducers() {
        return producers;
    }

    public void setProducers(Set<Producer> producers) {
        this.producers = producers;
    }
}
