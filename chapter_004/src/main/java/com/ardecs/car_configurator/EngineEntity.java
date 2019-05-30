package com.ardecs.car_configurator;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 04.05.2019
 */
@Entity
@Table(name = "engine", schema = "car_configurator")
public class EngineEntity {
    private Long engineId;
    private String name;
    private Integer price;
    private String description;
    private Set<EquipmentEntity> equipmentEntities;

    @Id
    @Column(name = "engine_id", nullable = false)
    public Long getEngineId() {
        return engineId;
    }

    public void setEngineId(Long engineId) {
        this.engineId = engineId;
    }

    @ManyToMany
    @JoinTable(name = "equipment_engine",
    joinColumns = @JoinColumn(name = "engine_id"),
    inverseJoinColumns = @JoinColumn(name = "equip_id"))
    public Set<EquipmentEntity> getEquipmentEntities() {
        return equipmentEntities;
    }

    public void setEquipmentEntities(Set<EquipmentEntity> equipmentEntity) {
        this.equipmentEntities = equipmentEntity;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 64)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "price", nullable = false)
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Basic
    @Column(name = "description", nullable = false, length = 190)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EngineEntity that = (EngineEntity) o;

        if (engineId != null ? !engineId.equals(that.engineId) : that.engineId != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        return description != null ? description.equals(that.description) : that.description == null;

    }

    @Override
    public int hashCode() {
        int result = engineId != null ? engineId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
