package com.ardecs.car_configurator.entities;

import com.ardecs.car_configurator.compositeId.AccessoryModComId;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 24.06.2019
 */
@Entity
@Table(name = "accessory_model_complect")
public class AccessoryModCom {

    @NotNull(message = "*Please provide price")
    @Max(value = 1_000_000, message = "*Max value 1_000_000")
    private Integer price;
    @JsonIgnore
    private AccessoryModComId accessoryModComId;
//    @JsonManagedReference
    private Accessory accessory;
    @JsonBackReference
    private ModelComplectation modelComplectation;

    @Column(name = "price")
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @EmbeddedId
    public AccessoryModComId getAccessoryModComId() {
        return accessoryModComId;
    }

    public void setAccessoryModComId(AccessoryModComId accessoryModComId) {
        this.accessoryModComId = accessoryModComId;
    }

    @ManyToOne
    @MapsId(value = "accessId")
    @JoinColumn(name = "access_id", referencedColumnName = "id")
    public Accessory getAccessory() {
        return accessory;
    }

    public void setAccessory(Accessory accessory) {
        this.accessory = accessory;
    }

    @ManyToOne
    @MapsId(value = "modelCompId")
    @JoinColumns({
            @JoinColumn(name = "model_id", referencedColumnName = "model_id"),
            @JoinColumn(name = "comp_id", referencedColumnName = "comp_id")
    })
    public ModelComplectation getModelComplectation() {
        return modelComplectation;
    }

    public void setModelComplectation(ModelComplectation modelComplectation) {
        this.modelComplectation = modelComplectation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccessoryModCom that = (AccessoryModCom) o;
        return Objects.equals(price, that.price) &&
                Objects.equals(accessoryModComId, that.accessoryModComId) &&
                Objects.equals(accessory, that.accessory) &&
                Objects.equals(modelComplectation, that.modelComplectation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, accessoryModComId, accessory, modelComplectation);
    }
}
