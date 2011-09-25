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
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "categories_parameters",
            joinColumns =
            @JoinColumn(name = "category_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "parameter_id", referencedColumnName = "id")
    )
    private Set<Parameter> parameters = new HashSet<Parameter>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "services",
            joinColumns = {@JoinColumn(name = "id", referencedColumnName = "category_id")})
    private Set<Service> services = new HashSet<Service>();

    public Category() {
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Set<Parameter> getParameters() {
        return parameters;
    }

    public Set<Service> getServices() {
        return services;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setParameters(Set<Parameter> parameters) {
        this.parameters = parameters;
    }

    public void setServices(Set<Service> services) {
        this.services = services;
    }
}
