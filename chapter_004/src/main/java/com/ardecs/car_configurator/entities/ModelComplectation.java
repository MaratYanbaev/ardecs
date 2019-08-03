package com.ardecs.car_configurator.entities;

import com.ardecs.car_configurator.compositeId.ModelCompId;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 13.06.2019
 */
@Entity
@Table(name = "model_complectation")
public class ModelComplectation {

    private int price;
    @JsonIgnore
    private ModelCompId id;
    @JsonBackReference
    private Model model;
    @JsonManagedReference
    private Complectation complectation;
    @JsonManagedReference
    private Set<ColorModCom> colorModComSet;
    @JsonManagedReference
    private Set<EngineModCom> engineModComSet;
    @JsonManagedReference
    private Set<AccessoryModCom> accessoryModComSet;

    @EmbeddedId
    public ModelCompId getId() {
        return id;
    }

    public void setId(ModelCompId id) {
        this.id = id;
    }

    @OneToMany(mappedBy = "modelComplectation")
    public Set<ColorModCom> getColorModComSet() {
        return colorModComSet;
    }

    public void setColorModComSet(Set<ColorModCom> colorModComSet) {
        this.colorModComSet = colorModComSet;
    }

    @OneToMany(mappedBy = "modelComplectation")
    public Set<AccessoryModCom> getAccessoryModComSet() {
        return accessoryModComSet;
    }

    public void setAccessoryModComSet(Set<AccessoryModCom> accessoryModComSet) {
        this.accessoryModComSet = accessoryModComSet;
    }

    @OneToMany(mappedBy = "modelComplectation")
    public Set<EngineModCom> getEngineModComSet() {
        return engineModComSet;
    }

    public void setEngineModComSet(Set<EngineModCom> engineModComSet) {
        this.engineModComSet = engineModComSet;
    }

    @ManyToOne
    @MapsId(value = "modelId")
    @JoinColumn(name = "model_id")
    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    @ManyToOne
    @MapsId(value = "comp_Id")
    @JoinColumn(name = "comp_id")
    public Complectation getComplectation() {
        return complectation;
    }

    public void setComplectation(Complectation complectation) {
        this.complectation = complectation;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModelComplectation that = (ModelComplectation) o;
        return price == that.price &&
                Objects.equals(id, that.id) &&
                Objects.equals(model, that.model) &&
                Objects.equals(complectation, that.complectation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, id, model, complectation);
    }
}
