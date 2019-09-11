package com.ardecs.repositories;

import com.ardecs.entities.mainEntities.Complectation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplectationRepository extends JpaRepository<Complectation, Long> {

    @Query(value = "select c.name, c.id\n" +
            "from complectation c\n" +
            "         inner JOIN model_complectation mc\n" +
            "                    on c.id = mc.comp_id\n" +
            "where model_id = :modelId\n" +
            "group by c.name\n" +
            "order by mc.price",
            nativeQuery = true)
    List<Complectation> getCompByIdOfModel(@Param("modelId") Long modelId);

    Complectation findByName(String name);
}
