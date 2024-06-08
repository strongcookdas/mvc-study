package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDaoTest {
    @BeforeEach
    void setUp() {
        //Spring jdbc 라이브러리 클래스
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("db_schema.sql"));
        DatabasePopulatorUtils.execute(populator,ConnectionManager.getDataSource());
    }

    @Test
    void createTest() throws SQLException {
        //Data Access Object db 작업시 dao에게 유임
        UserDao userDao = new UserDao();
        userDao.create(new User("jdbcId", "password", "name", "email"));
        User user = userDao.findUserId("hong");
        assertThat(user).isEqualTo(new User("jdbcId", "password", "name","email"));
    }
}
