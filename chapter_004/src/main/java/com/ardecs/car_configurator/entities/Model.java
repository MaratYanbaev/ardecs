package com.ardecs.car_configurator.entities;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 04.05.2019
 */
@Entity
@Table(name = "model")
public class Model {

    private Long id;
    private Integer price;
    private String name;
    private Brand brand;
    private Set<ModelComplectation> modelComplectationSet;

    public Model() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long modelId) {
        this.id = modelId;
    }

    @ManyToOne
    @JoinColumn(name = "brand_id")
    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @OneToMany(mappedBy = "model", cascade = CascadeType.ALL)
    public Set<ModelComplectation> getModelComplectationSet() {
        return modelComplectationSet;
    }

    public void setModelComplectationSet(Set<ModelComplectation> modelComplectationSet) {
        this.modelComplectationSet = modelComplectationSet;
    }

    @NaturalId
    @Basic
    @Column(name = "name", nullable = false, length = 15)
    public String getName() {
        return name;
    }


    public void setName(String model) {
        this.name = model;
    }

    @Basic
    @Column(name = "price", nullable = false)
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Model that = (Model) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return name != null ? name.equals(that.name) : that.name == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
