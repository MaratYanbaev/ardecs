package com.ardecs.car_configurator;

import com.ardecs.repositories.BrandRepository;
import com.ardecs.repositories.ModelRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ModelEntityTest {


    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Test
    public void testFindByModelOfModelRepository() {
        BrandEntity brandEntity = brandRepository.findId(1L);
        ModelEntity entity = new ModelEntity("LC150", 2_800_000, 1L, brandEntity);
        modelRepository.save(entity);

        entity = modelRepository.findByModel("LC150");

        assertThat(entity.getPrice(), is(2_800_000));
    }

    @Test
    public void testFindByPriceBetweenOfModelRepository() {
        List<ModelEntity> modelEntityList = modelRepository.findByPriceBetween(1_000_000, 2_000_000);

        boolean containsCamry = modelEntityList.contains(modelRepository.findByModel("Camry"));
        boolean containsRav4 = modelEntityList.contains(modelRepository.findByModel("Rav4"));

        assertThat(containsCamry, is(true));
        assertThat(containsRav4, is(true));
    }
}