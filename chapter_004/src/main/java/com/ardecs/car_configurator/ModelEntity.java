package com.ardecs.car_configurator;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 04.05.2019
 */
@Entity
@Table(name = "model", schema = "car_configurator")
public class ModelEntity {

    private Long modelId;
    private Long brandId;
    private String model;
    private Integer price;
    private BrandEntity brandEntity;
    private Set<EquipmentEntity> equipmentEntitySet;

    public ModelEntity() {
    }

    public ModelEntity(String model, Integer price, Long brandId, BrandEntity brandEntity) {
        this.model = model;
        this.price = price;
        this.brandId = brandId;
        this.brandEntity = brandEntity;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "model_id", nullable = false)
    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }


    @Basic
    @Column(name = "brand_id", nullable = false, insertable = false, updatable = false)
    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    @ManyToOne()
    @JoinColumn(name = "brand_id")
    public BrandEntity getBrandEntity() {
        return brandEntity;
    }

    public void setBrandEntity(BrandEntity brandEntity) {
        this.brandEntity = brandEntity;
    }

    @OneToMany(mappedBy = "modelEntity", orphanRemoval = true)
    public Set<EquipmentEntity> getEquipmentEntitySet() {
        return equipmentEntitySet;
    }

    public void setEquipmentEntitySet(Set<EquipmentEntity> equipmentEntitySet) {
        this.equipmentEntitySet = equipmentEntitySet;
    }

    @Basic
    @Column(name = "model", nullable = false, length = 15)
    public String getModel() {
        return model;
    }


    public void setModel(String model) {
        this.model = model;
    }

    @Basic
    @Column(name = "price", nullable = false)
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ModelEntity that = (ModelEntity) o;

        if (modelId != null ? !modelId.equals(that.modelId) : that.modelId != null) return false;
        if (model != null ? !model.equals(that.model) : that.model != null) return false;
        return price != null ? price.equals(that.price) : that.price == null;

    }

    @Override
    public int hashCode() {
        int result = modelId != null ? modelId.hashCode() : 0;
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }
}
