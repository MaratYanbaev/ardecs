package com.ardecs.repositories;

import com.ardecs.car_configurator.entities.AccessoryModCom;
import com.ardecs.car_configurator.entities.EngineModCom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 27.06.2019
 */
@Repository
public interface AccessoryModComRepository extends JpaRepository<AccessoryModCom, Long> {

    @Query(value = "select *\n" +
            "from accessory_model_complect\n" +
            "where model_id = :modelId\n" +
            "  and comp_id = :compId\n" +
            "order by access_id",
            nativeQuery = true)
    List<AccessoryModCom> getAccessOfModCom(@Param(value = "modelId") Long modelId,
                                            @Param(value = "compId") Long compId);

    @Transactional
    @Modifying
    @Query(value = "delete\n" +
            "from accessory_model_complect\n" +
            "where access_id = :access_id\n" +
            "  and model_id = :model_id\n" +
            "  and comp_id = :comp_id",
            nativeQuery = true)
    void delete(@Param(value = "access_id") Long accessId,
                @Param(value = "model_id") Long modelId,
                @Param(value = "comp_id") Long compId);

    @Transactional
    @Modifying
    @Query(value = "insert into accessory_model_complect\n" +
            "    (access_id, model_id, comp_id, price)\n" +
            "values (:accessId, :modelId, :compId, :price)\n" +
            "on duplicate KEY UPDATE price = :price",
            nativeQuery = true)
    void addAccessory(Long accessId, Long modelId, Long compId, int price);
}
