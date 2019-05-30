package com.ardecs.car_configurator;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 04.05.2019
 */
@Entity
@Table(name = "brand", schema = "car_configurator")
public class BrandEntity {

    private Long brandId;
    private String name;
    private Set<ModelEntity> modelEntity;

    public BrandEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "brand_id", nullable = false)
    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    @OneToMany(mappedBy = "brandEntity", orphanRemoval = true)
//    @OrderBy("price desc")
    public Set<ModelEntity> getModelEntity() {
        return modelEntity;
    }

    public void setModelEntity(Set<ModelEntity> modelEntity) {
        this.modelEntity = modelEntity;
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

        BrandEntity that = (BrandEntity) o;

        if (brandId != null ? !brandId.equals(that.brandId) : that.brandId != null) return false;
        return name != null ? name.equals(that.name) : that.name == null;

    }

    @Override
    public int hashCode() {
        int result = brandId != null ? brandId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
