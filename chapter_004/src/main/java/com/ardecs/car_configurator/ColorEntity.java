package com.ardecs.car_configurator;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 04.05.2019
 */
@Entity
@Table(name = "color", schema = "car_configurator")
public class ColorEntity {
    private Long colorId;
    private String name;
    private Set<EquipmentEntity> equipmentEntities;

    @ManyToMany
    @JoinTable(name = "equipment_color",
            joinColumns = {@JoinColumn(name = "color_id")},
            inverseJoinColumns = {@JoinColumn(name = "equip_id")})
    public Set<EquipmentEntity> getEquipmentEntities() {
        return equipmentEntities;
    }

    public void setEquipmentEntities(Set<EquipmentEntity> equipmentEntities) {
        this.equipmentEntities = equipmentEntities;
    }

    @Id
    @Column(name = "color_id", nullable = false)
    public Long getColorId() {
        return colorId;
    }

    public void setColorId(Long colorId) {
        this.colorId = colorId;
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

        ColorEntity that = (ColorEntity) o;

        if (colorId != null ? !colorId.equals(that.colorId) : that.colorId != null) return false;
        return name != null ? name.equals(that.name) : that.name == null;

    }

    @Override
    public int hashCode() {
        int result = colorId != null ? colorId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
