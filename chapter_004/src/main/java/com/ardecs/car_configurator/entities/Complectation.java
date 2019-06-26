package com.ardecs.car_configurator.entities;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 04.05.2019
 */
@Entity
@Table(name = "complectation")
public class Complectation {
    private Long compId;
    private String name;
    private Set<ModelComplectation> modelComplectationSet;

    public Complectation() {
    }

    public Complectation(Long compId, String name) {
        this.compId = compId;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public Long getCompId() {
        return compId;
    }

    public void setCompId(Long equipId) {
        this.compId = equipId;
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

        if (compId != null ? !compId.equals(that.compId) : that.compId != null) return false;
        return name != null ? name.equals(that.name) : that.name == null;

    }

    @Override
    public int hashCode() {
        int result = compId != null ? compId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
