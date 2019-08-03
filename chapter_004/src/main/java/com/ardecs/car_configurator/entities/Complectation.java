package com.ardecs.car_configurator.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 04.05.2019
 */
@Entity
@Table(name = "complectation")
public class Complectation {
    private Long id;
    private String name;
    @JsonBackReference
    private Set<ModelComplectation> modelComplectationSet;

    public Complectation() {
    }

    public Complectation(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long equipId) {
        this.id = equipId;
    }

    @OneToMany(mappedBy = "complectation")
    public Set<ModelComplectation> getModelComplectationSet() {
        return modelComplectationSet;
    }

    public void setModelComplectationSet(Set<ModelComplectation> modelComplectationSet) {
        this.modelComplectationSet = modelComplectationSet;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 15)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Complectation that = (Complectation) o;

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
