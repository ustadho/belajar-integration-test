package com.example.testing.demosbtesting;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeTest {
    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void whenFindByName_thenReturnEmployee(){
        //given
        Employee ustadho = new Employee("Ustadho");
        testEntityManager.persist(ustadho);
        testEntityManager.flush();

        //when
        Employee found = employeeRepository.findByName("Ustadho");

        //Then
        assertThat(found.getName()).isEqualTo(ustadho.getName());
    }
}
