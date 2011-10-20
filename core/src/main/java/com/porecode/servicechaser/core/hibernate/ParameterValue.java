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

  @Column(length = 20, nullable = true, name = "text_value")
  private String textValue;

  @Column(nullable = true, name = "int_value")
  private Long intValue;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "parameter_values_services",
      joinColumns = @JoinColumn(name = "service_id"),
      inverseJoinColumns = @JoinColumn(name = "parameter_value_id")
  )
  private Set<Service> services = new HashSet<Service>();

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "parameter_id")
  private Parameter parameter;

  public ParameterValue() {
  }

  public ParameterValue(Long id, Long intValue, String textValue) {
    this.id = id;
    this.textValue = textValue;
    this.intValue = intValue;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTextValue() {
    return textValue;
  }

  public void setTextValue(String textValue) {
    this.textValue = textValue;
  }

  public Long getIntValue() {
    return intValue;
  }

  public void setIntValue(Long intValue) {
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
