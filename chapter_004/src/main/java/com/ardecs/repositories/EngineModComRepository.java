package com.ardecs.repositories;

import com.ardecs.entities.mainEntities.EngineModCom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EngineModComRepository extends JpaRepository<EngineModCom, Long> {

    @Query(value = "select *\n" +
            "from engine_model_complect\n" +
            "where model_id = :modelId\n" +
            "  and comp_id = :compId\n" +
            "order by engine_id",
            nativeQuery = true)
    List<EngineModCom> getEngineOfModCom(Long modelId, Long compId);

    @Transactional
    @Modifying
    @Query(value = "delete\n" +
            "FROM engine_model_complect\n" +
            "where engine_id = :engine_id\n" +
            "  and model_id = :model_id\n" +
            "  and comp_id = :comp_id",
            nativeQuery = true)
    void delete(@Param(value = "engine_id") Long engineId,
                @Param(value = "model_id") Long modelId,
                @Param(value = "comp_id") Long compId);

    @Transactional
    @Modifying
    @Query(value = "insert into engine_model_complect\n" +
            "    (engine_id, model_id, comp_id, price)\n" +
            "VALUES (:engineId, :modelId, :compId, :price)\n" +
            "on duplicate KEY UPDATE price = :price",
            nativeQuery = true)
    void addEngine(Long engineId, Long modelId, Long compId, int price);
}
