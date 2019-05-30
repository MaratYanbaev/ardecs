package com.ardecs.car_configurator;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 04.05.2019
 */
@Entity
@Table(name = "accessory", schema = "car_configurator")
public class AccessoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accessId;
    private String name;
    private Set<EquipmentEntity> equipmentEntities;

    @ManyToMany
    @JoinTable(name = "equipment_accessory",
    joinColumns = {@JoinColumn(name = "access_id")},
    inverseJoinColumns = {@JoinColumn(name = "equip_id")})
    public Set<EquipmentEntity> getEquipmentEntities() {
        return equipmentEntities;
    }

    public void setEquipmentEntities(Set<EquipmentEntity> equipmentEntities) {
        this.equipmentEntities = equipmentEntities;
    }

    @Id
    @Column(name = "access_id", nullable = false)
    public Long getAccessId() {
        return accessId;
    }

    public void setAccessId(Long accessId) {
        this.accessId = accessId;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 32)
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

        AccessoryEntity that = (AccessoryEntity) o;

        if (accessId != null ? !accessId.equals(that.accessId) : that.accessId != null) return false;
        return name != null ? name.equals(that.name) : that.name == null;

    }

    @Override
    public int hashCode() {
        int result = accessId != null ? accessId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
