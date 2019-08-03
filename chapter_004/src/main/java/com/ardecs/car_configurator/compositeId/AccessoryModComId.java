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
public class AccessoryModComId implements Serializable {

    private Long accessId;
    @EmbeddedId
    private ModelCompId modelCompId;

    public AccessoryModComId(Long accessId, ModelCompId modelCompId) {
        this.accessId = accessId;
        this.modelCompId = modelCompId;
    }

    public AccessoryModComId() {
    }

    public Long getAccessId() {
        return accessId;
    }

    public void setAccessId(Long accessId) {
        this.accessId = accessId;
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
        AccessoryModComId that = (AccessoryModComId) o;
        return Objects.equals(accessId, that.accessId) &&
                Objects.equals(modelCompId, that.modelCompId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accessId, modelCompId);
    }
}
