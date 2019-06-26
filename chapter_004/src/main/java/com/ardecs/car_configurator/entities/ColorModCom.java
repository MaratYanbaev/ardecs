package com.ardecs.car_configurator.entities;

import com.ardecs.car_configurator.identificators.ColorModComId;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 24.06.2019
 */
@Entity
@Table(name = "color_model_complect")
public class ColorModCom {

    private ColorModComId colorModComId;
    private Integer price;
    private ColorEntity colorEntity;
    private ModelComplectation modelComplectation;

    @EmbeddedId
    public ColorModComId getColorModComId() {
        return colorModComId;
    }

    public void setColorModComId(ColorModComId colorModComId) {
        this.colorModComId = colorModComId;
    }

    @Column(name = "price")
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @ManyToOne
    @MapsId(value = "colorId")
    @JoinColumn(name = "color_id", referencedColumnName = "id")
    public ColorEntity getColorEntity() {
        return colorEntity;
    }

    public void setColorEntity(ColorEntity colorEntity) {
        this.colorEntity = colorEntity;
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
        ColorModCom that = (ColorModCom) o;
        return Objects.equals(colorModComId, that.colorModComId) &&
                Objects.equals(price, that.price) &&
                Objects.equals(colorEntity, that.colorEntity) &&
                Objects.equals(modelComplectation, that.modelComplectation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(colorModComId, price, colorEntity, modelComplectation);
    }
}
