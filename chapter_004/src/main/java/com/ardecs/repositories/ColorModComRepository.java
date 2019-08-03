package com.ardecs.repositories;

import com.ardecs.car_configurator.entities.ColorModCom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ColorModComRepository extends JpaRepository<ColorModCom, Long> {

    @Query(value = "select *\n" +
            "from color_model_complect\n" +
            "where model_id = :modelId\n" +
            "  and comp_id = :compId\n" +
            "order by color_id",
            nativeQuery = true)
    List<ColorModCom> getColorOfModCom(Long modelId, Long compId);

    @Transactional
    @Modifying
    @Query(value = "delete\n" +
            "FROM color_model_complect\n" +
            "where color_id = :color_id\n" +
            "  and model_id = :model_id\n" +
            "  and comp_id = :comp_id",
            nativeQuery = true)
    void delete(@Param(value = "color_id") Long colorId,
                @Param(value = "model_id") Long modelId,
                @Param(value = "comp_id") Long compId);

    @Transactional
    @Modifying
    @Query(value = "insert into color_model_complect\n" +
            "    (color_id, model_id, comp_id, price)\n" +
            "VALUES (:colorId, :modelId, :compId, :price)\n" +
            "on duplicate KEY UPDATE price = :price",
            nativeQuery = true)
    void addColor(Long colorId, Long modelId, Long compId, int price);
}
