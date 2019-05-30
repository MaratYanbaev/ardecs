package com.ardecs.car_configurator;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 04.05.2019
 */
@Entity
@Table(name = "equipment", schema = "car_configurator")
public class EquipmentEntity {
    private Long equipId;
    private String name;
    private ModelEntity modelEntity;
    private Set<EngineEntity> engineEntities;
    private Set<AccessoryEntity> accessoryEntities;
    private Set<ColorEntity> colorEntities;

    public EquipmentEntity() {
    }

    @Id
    @Column(name = "equip_id", nullable = false)
    public Long getEquipId() {
        return equipId;
    }

    public void setEquipId(Long equipId) {
        this.equipId = equipId;
    }

    @ManyToMany
    @JoinTable(name = "equipment_color",
    joinColumns = {@JoinColumn(name = "equip_id")},
    inverseJoinColumns = {@JoinColumn(name = "color_id")})
    public Set<ColorEntity> getColorEntities() {
        return colorEntities;
    }

    public void setColorEntities(Set<ColorEntity> colorEntities) {
        this.colorEntities = colorEntities;
    }

    @ManyToMany
    @JoinTable(name = "equipment_accessory",
    joinColumns = {@JoinColumn(name = "equip_id")},
    inverseJoinColumns = {@JoinColumn(name = "access_id")})
    public Set<AccessoryEntity> getAccessoryEntities() {
        return accessoryEntities;
    }

    public void setAccessoryEntities(Set<AccessoryEntity> accessoryEntities) {
        this.accessoryEntities = accessoryEntities;
    }

    @ManyToMany
    @JoinTable(name = "equipment_engine",
            joinColumns = {@JoinColumn(name = "equip_id")},
            inverseJoinColumns = {@JoinColumn(name = "engine_id")})
    public Set<EngineEntity> getEngineEntities() {
        return engineEntities;
    }

    public void setEngineEntities(Set<EngineEntity> engineEntities) {
        this.engineEntities = engineEntities;
    }

    @ManyToOne()
    @JoinColumn(name = "model_id")
    public ModelEntity getModelEntity() {
        return modelEntity;
    }

    public void setModelEntity(ModelEntity modelEntity) {
        this.modelEntity = modelEntity;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 15)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EquipmentEntity that = (EquipmentEntity) o;

        if (equipId != null ? !equipId.equals(that.equipId) : that.equipId != null) return false;
        return name != null ? name.equals(that.name) : that.name == null;

    }

    @Override
    public int hashCode() {
        int result = equipId != null ? equipId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
