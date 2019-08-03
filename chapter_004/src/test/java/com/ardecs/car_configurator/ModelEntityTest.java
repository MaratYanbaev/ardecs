package com.ardecs.car_configurator;

import com.ardecs.car_configurator.entities.Brand;
import com.ardecs.car_configurator.entities.Model;
import com.ardecs.repositories.BrandRepository;
import com.ardecs.repositories.ModelRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@DataJpaTest
/*
По умолчанию, DataJpaTest начинает транзакцию и откатывает ее в конце каждого теста.
Для того чтобы данные сохранялись необходимо отменить управление транзакцией на уровне
класса или метода.
 */
//@Transactional(propagation = Propagation.NOT_SUPPORTED)

/*
Если необходимо запустить тест для фактической зарегистрированной базы данных,
необходимо аннотировать класс как указанно ниже, тогда будет использоваться
зарегистрированная база данных вместо источника данных в памяти.
replace = AutoConfigureTestDatabase.Replace.NONE
 */
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ModelEntityTest {


    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Test
    public void testSaveModel() {
        Brand brandEntity = brandRepository.findId(5L);
        Model model = new Model();
        model.setBrand(brandEntity);
        model.setName("Hilux");
        model.setPrice(2_300_000);
        model = modelRepository.save(model);

        assertThat(model, is(notNullValue()));
    }
}