package com.ardecs.entities.mainEntities;

import com.ardecs.entities.compositeId.ColorModComId;
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
@Table(name = "color_model_complect")
public class ColorModCom {

    @NotNull(message = "*Please provide price")
    @Max(value = 1_000_000, message = "*Max value 1_000_000")
    private Integer price;
    @JsonIgnore
    private ColorModComId colorModComId;
    private Color color;
    @JsonBackReference
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
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
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
                Objects.equals(color, that.color) &&
                Objects.equals(modelComplectation, that.modelComplectation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(colorModComId, price, color, modelComplectation);
    }
}
