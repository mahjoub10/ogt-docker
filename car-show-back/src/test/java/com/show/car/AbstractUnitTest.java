package com.show.car;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by mmarzougui on 07/03/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
public abstract  class AbstractUnitTest {


}
