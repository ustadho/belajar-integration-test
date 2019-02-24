package com.example.testing.demosbtesting;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class EmployeeServiceIntegrationTest {
    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {
        @Bean
        public EmployeeService employeeService(){
            return new EmployeeServiceImpl();
        }
    }

    @Autowired
    EmployeeService employeeService;

    @MockBean
    private EmployeeRepository employeeRepository;

    @Before
    public void setup(){
        Employee ustadho = new Employee("Ustadho");
        Mockito.when(employeeRepository.findByName(ustadho.getName()))
                .thenReturn(ustadho);
    }

    @Test
    public void whenValidName_thenEmployeeShouldBeFound(){
        String name = "Ustadho";
        Employee found = employeeService.findByName(name);
        assertThat(found.getName()).isEqualTo(name);
    }
}
