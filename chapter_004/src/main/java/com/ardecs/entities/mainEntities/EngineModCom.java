package com.ardecs.entities.mainEntities;

import com.ardecs.entities.compositeId.EngineModComId;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 23.06.2019
 */
@Entity
@Table(name = "engine_model_complect")
public class EngineModCom {

    @NotNull(message = "*Please provide price")
    @Max(value = 1_000_000, message = "*Max value 1_000_000")
    private Integer price;
    @JsonIgnore
    private EngineModComId engineModComId;
    private Engine engine;
    @JsonBackReference
    private ModelComplectation modelComplectation;

    @EmbeddedId
    public EngineModComId getEngineModComId() {
        return engineModComId;
    }

    public void setEngineModComId(EngineModComId engineModComId) {
        this.engineModComId = engineModComId;
    }

    @Column(name = "price")
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @ManyToOne
    @MapsId(value = "engineId")
    @JoinColumn(name = "engine_id", referencedColumnName = "id")
    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    @ManyToOne
    @MapsId(value = "modelCompId")
    @JoinColumns({
            @JoinColumn(name = "comp_id", referencedColumnName = "comp_id"),
            @JoinColumn(name = "model_id", referencedColumnName = "model_id")
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
        EngineModCom that = (EngineModCom) o;
        return Objects.equals(engineModComId, that.engineModComId) &&
                Objects.equals(price, that.price) &&
                Objects.equals(engine, that.engine) &&
                Objects.equals(modelComplectation, that.modelComplectation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(engineModComId, price, engine, modelComplectation);
    }
}
