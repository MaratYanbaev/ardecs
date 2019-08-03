package com.ardecs.repositories;

import com.ardecs.car_configurator.entities.ModelComplectation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 26.06.2019
 */
@Repository
public interface ModelComplectationRepository extends JpaRepository<ModelComplectation, Long> {

    @Query(value = "select mc.price\n" +
            "from model_complectation mc\n" +
            "inner JOIN complectation c on mc.comp_id = c.id\n" +
            "where mc.model_id = :id\n" +
            "order by mc.price",
            nativeQuery = true)
    List<Integer> getPriceByIdOfModel(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value = "delete model_complectation\n" +
            "FROM model_complectation\n" +
            "where model_id = :modelId and comp_id = :compId",
            nativeQuery = true)
    void delete(@Param("modelId") Long modelId,
                @Param("compId") Long compId);

    @Query(value = "select *\n" +
            "from model_complectation\n" +
            "where model_id = :modelId\n" +
            "  and comp_id = :compId",
            nativeQuery = true)
    ModelComplectation existComplectation(Long modelId, Long compId);
}
