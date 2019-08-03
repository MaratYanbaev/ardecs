package com.ardecs.car_configurator.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 04.05.2019
 */
@Entity
@Table(name = "color")
public class Color {

    private Long id;
    private String name;
    @JsonBackReference
    private Set<ColorModCom> colorModComSet;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long colorId) {
        this.id = colorId;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 32)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "color")
    public Set<ColorModCom> getColorModComSet() {
        return colorModComSet;
    }

    public void setColorModComSet(Set<ColorModCom> colorModComSet) {
        this.colorModComSet = colorModComSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Color that = (Color) o;

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
