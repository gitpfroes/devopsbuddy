package br.com.caprica.spring.devopsbuddy.integration;

import br.com.caprica.spring.devopsbuddy.DevopsbuddyApplication;
import br.com.caprica.spring.devopsbuddy.backend.persistence.domain.backend.Plan;
import br.com.caprica.spring.devopsbuddy.backend.persistence.domain.backend.Role;
import br.com.caprica.spring.devopsbuddy.backend.persistence.domain.backend.User;
import br.com.caprica.spring.devopsbuddy.backend.persistence.domain.backend.UserRole;
import br.com.caprica.spring.devopsbuddy.backend.persistence.repositories.PlanRepository;
import br.com.caprica.spring.devopsbuddy.backend.persistence.repositories.RoleRepository;
import br.com.caprica.spring.devopsbuddy.backend.persistence.repositories.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DevopsbuddyApplication.class)
public class RepositoryIntegrationTest {

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private static final int DEFAULT_ID = 123;

    @Before
    public void init(){
        Assert.assertNotNull(planRepository);
        Assert.assertNotNull(userRepository);
        Assert.assertNotNull(roleRepository);
    }

    private Plan createBasicPlan(){
        Plan plan = new Plan();
        plan.setId(DEFAULT_ID);
        plan.setDescription("Basic Plan");
        plan.setName("Basic");
        return plan;
    }

    private User createDefaultUser(){
        User user = new User();
        user.setId(DEFAULT_ID);
        user.setCountry("Brazil");
        user.setDescription("It's me, Mario");
        user.setEnabled(true);
        user.setFirstName("Mario");
        user.setLastName("Bros");

        return user;
    }

    private Role createDefaultRole(){
        Role role = new Role();
        role.setId(DEFAULT_ID);
        role.setName("Standard");

        return role;
    }

    @Test
    public void testCreatePlan() throws Exception{
        Plan plan = createBasicPlan();
        planRepository.save(plan);

        Plan retrievedPlan = planRepository.findOne(DEFAULT_ID);
        Assert.assertNotNull(retrievedPlan);
    }

}
