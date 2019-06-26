package com.ardecs.car_configurator.identificators;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 23.06.2019
 */
@Embeddable
public class EngineModComId implements Serializable {

    private Long engineId;
    @EmbeddedId
    private ModelCompId modelCompId;

    public EngineModComId(Long engineId, ModelCompId modelCompId) {
        this.engineId = engineId;
        this.modelCompId = modelCompId;
    }

    public EngineModComId() {
    }

    public Long getEngineId() {
        return engineId;
    }

    public void setEngineId(Long engineId) {
        this.engineId = engineId;
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
        EngineModComId that = (EngineModComId) o;
        return Objects.equals(engineId, that.engineId) &&
                Objects.equals(modelCompId, that.modelCompId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(engineId, modelCompId);
    }
}
