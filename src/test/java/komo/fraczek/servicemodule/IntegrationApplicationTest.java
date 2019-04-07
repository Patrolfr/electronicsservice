package komo.fraczek.servicemodule;

import komo.fraczek.servicemodule.domain.Category;
import komo.fraczek.servicemodule.repository.CategoryRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;


//@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DbConfig.class})
//@ActiveProfiles("DaoTest")
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:dao/TestData.sql")
public class IntegrationApplicationTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void contextLoads() {

//        customerRepository.save(Customer.builder()
//                .id(new Random().nextLong())
//                .address("brussels")
//                .name("TestName")
//                .build());


//        Assert.assertTrue(customerRepository.findCustomerByName("TestName") != null);
        Assertions.assertTrue(true);
    }

    @Test
    public void my_test(){

        Category category = createCategoryFake();


        Category save = categoryRepository.save(category);

        System.out.println("\n\n\n\n\n ************************************ \n :" + save.getName());

    }

    private Category createCategoryFake(){
        return new Category(1L,"categoryStringFake");
    }


}
