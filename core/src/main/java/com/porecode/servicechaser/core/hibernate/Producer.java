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
@Table(name = "producers")
public class Producer {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "producers_services",
            joinColumns =
            @JoinColumn(name = "producer_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "service_id", referencedColumnName = "id")
    )
    private Set<Service> services = new HashSet<Service>();

    public Producer() {
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Service> getServices() {
        return services;
    }

    public void setServices(Set<Service> services) {
        this.services = services;
    }
}
