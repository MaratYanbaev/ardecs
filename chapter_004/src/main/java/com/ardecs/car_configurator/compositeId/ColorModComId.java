package com.ardecs.car_configurator.compositeId;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 24.06.2019
 */
@Embeddable
public class ColorModComId implements Serializable {

    private Long colorId;
    @EmbeddedId
    private ModelCompId modelCompId;

    public ColorModComId(Long colorId, ModelCompId modelCompId) {
        this.colorId = colorId;
        this.modelCompId = modelCompId;
    }

    public ColorModComId() {
    }

    public Long getColorId() {
        return colorId;
    }

    public void setColorId(Long colorId) {
        this.colorId = colorId;
    }

    public ModelCompId getModelCompId() {
        return modelCompId;
    }

    public void setModelCompId(ModelCompId modelCompId) {
        this.modelCompId = modelCompId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ColorModComId that = (ColorModComId) o;
        return Objects.equals(colorId, that.colorId) &&
                Objects.equals(modelCompId, that.modelCompId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(colorId, modelCompId);
    }
}
