package com.ardecs.car_configurator.entities;

import com.ardecs.car_configurator.identificators.EngineModComId;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 23.06.2019
 */
@Entity
@Table(name = "engine_model_complect")
public class EngineModCom {

    private EngineModComId engineModComId;
    private Integer price;
    private Engine engine;
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
