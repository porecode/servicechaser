package com.porecode.servicechaser.core.hibernate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: fealaer
 * Date: 26.09.11
 */
@Entity
@Table(name = "parameter_values")
public class ParameterValue {
    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 20, nullable = true)
    private String textValue;

    @Column(nullable = true)
    private Long intValue;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "parameter_values_services",
            joinColumns =
            @JoinColumn(name = "parameter_value_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "service_id", referencedColumnName = "id")
    )
    private Set<Service> services = new HashSet<Service>();

    @ManyToOne(cascade = CascadeType.ALL)
    private Parameter parameter;

    public ParameterValue() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText_value() {
        return textValue;
    }

    public void setText_value(String textValue) {
        this.textValue = textValue;
    }

    public Long getInt_value() {
        return intValue;
    }

    public void setInt_value(Long intValue) {
        this.intValue = intValue;
    }

    public Set<Service> getServices() {
        return services;
    }

    public void setServices(Set<Service> services) {
        this.services = services;
    }

    public Parameter getParameter() {
        return parameter;
    }

    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
    }
}
