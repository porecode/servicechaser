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
@Table(name = "parameters")
public class Parameter {
    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Kind kind;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "categories_parameters",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "parameter_id")
    )
    private Set<Category> categories = new HashSet<Category>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "parameter_id")
    private Set<ParameterValue> parameter_values = new HashSet<ParameterValue>();

    public Parameter() {
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Kind getKind() {
        return kind;
    }

    public void setKind(Kind kind) {
        this.kind = kind;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<ParameterValue> getParameter_values() {
        return parameter_values;
    }

    public void setParameter_values(Set<ParameterValue> parameter_values) {
        this.parameter_values = parameter_values;
    }
}
