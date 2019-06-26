package com.ardecs.car_configurator.identificators;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 14.06.2019
 */
@Embeddable
public class ModelCompId implements Serializable {

    @NotNull
    private Long modelId;

    @NotNull
    private Long comp_Id;

    public ModelCompId() {
    }

    public ModelCompId(@NotNull Long modelId, @NotNull Long comp_Id) {
        this.modelId = modelId;
        this.comp_Id = comp_Id;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public Long getComp_Id() {
        return comp_Id;
    }

    public void setComp_Id(Long compId) {
        this.comp_Id = compId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModelCompId that = (ModelCompId) o;
        return modelId.equals(that.modelId) &&
                comp_Id.equals(that.comp_Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modelId, comp_Id);
    }
}
