//package com.ardecs.car_configurator;
//
//import com.ardecs.repositories.BrandRepository;
//import com.ardecs.repositories.ModelRepository;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//
//import static org.hamcrest.Matchers.is;
//import static org.junit.Assert.*;
//
//@RunWith(SpringRunner.class)
//@DataJpaTest
///*
//По умолчанию, DataJpaTest начинает транзакцию и откатывает ее в конце каждого теста.
//Для того чтобы данные сохранялись необходимо отменить управление транзакцией на уровне
//класса или метода.
// */
////@Transactional(propagation = Propagation.NOT_SUPPORTED)
//
///*
//Если необходимо запустить тест для фактической зарегистрированной базы данных,
//необходимо аннотировать класс как указанно ниже, тогда будет использоваться
//зарегистрированная база данных вместо источника данных в памяти.
//replace = AutoConfigureTestDatabase.Replace.NONE
// */
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//public class ModelEntityTest {
//
//
//    @Autowired
//    private ModelRepository modelRepository;
//
//    @Autowired
//    private BrandRepository brandRepository;
//
//    @Test
//    public void testFindByModelOfModelRepository() {
//        Brand brandEntity = brandRepository.findId(5L);
//        Model entity = new Model("LC150", 5L, brandEntity);
//        modelRepository.save(entity);
//    }
//}