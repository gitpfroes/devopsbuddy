package br.com.caprica.spring.devopsbuddy.integration;

import br.com.caprica.spring.devopsbuddy.DevopsbuddyApplication;
import br.com.caprica.spring.devopsbuddy.backend.persistence.domain.backend.Plan;
import br.com.caprica.spring.devopsbuddy.backend.persistence.domain.backend.Role;
import br.com.caprica.spring.devopsbuddy.backend.persistence.domain.backend.User;
import br.com.caprica.spring.devopsbuddy.backend.persistence.domain.backend.UserRole;
import br.com.caprica.spring.devopsbuddy.backend.persistence.repositories.PlanRepository;
import br.com.caprica.spring.devopsbuddy.backend.persistence.repositories.RoleRepository;
import br.com.caprica.spring.devopsbuddy.backend.persistence.repositories.UserRepository;
import br.com.caprica.spring.devopsbuddy.enums.PlansEnum;
import br.com.caprica.spring.devopsbuddy.enums.RolesEnum;
import br.com.caprica.spring.devopsbuddy.utils.UserUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DevopsbuddyApplication.class)
public class RepositoryIntegrationTest {

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Rule
    public TestName testName = new TestName();

    @Before
    public void init(){
        Assert.assertNotNull(planRepository);
        Assert.assertNotNull(userRepository);
        Assert.assertNotNull(roleRepository);
    }

    @Test
    public void testCreateNewPlan() throws Exception {
        Plan basicPlan = createBasicPlan(PlansEnum.BASIC);
        planRepository.save(basicPlan);
        Plan retrievedPlan = planRepository.findOne(PlansEnum.BASIC.getId());
        Assert.assertNotNull(retrievedPlan);
    }

    @Test
    public void testCreateNewRole() throws Exception {

        Role userRole  = createDefaultRole(RolesEnum.BASIC);
        roleRepository.save(userRole);

        Role retrievedRole = roleRepository.findOne(RolesEnum.BASIC.getId());
        Assert.assertNotNull(retrievedRole);
    }

    @Test
    public void createNewUser() throws Exception {

        String username = testName.getMethodName();
        String email = testName.getMethodName() + "@devopsbuddy.com";

        User basicUser = UserUtils.createBasicUser(username, email);
        userRepository.save(basicUser);

        User newlyCreatedUser = userRepository.findOne(basicUser.getId());
        Assert.assertNotNull(newlyCreatedUser);
        Assert.assertTrue(newlyCreatedUser.getId() != 0);
        Assert.assertNotNull(newlyCreatedUser.getPlan());
        Assert.assertNotNull(newlyCreatedUser.getPlan().getId());
        Set<UserRole> newlyCreatedUserUserRoles = newlyCreatedUser.getUserRoles();
        for (UserRole ur : newlyCreatedUserUserRoles) {
            Assert.assertNotNull(ur.getRole());
            Assert.assertNotNull(ur.getRole().getId());
        }

    }

    private Plan createBasicPlan(PlansEnum plansEnum){
        Plan plan = new Plan();
        plan.setId(plansEnum.getId());
        plan.setName(plansEnum.getPlanName());
        return plan;
    }

    private Role createDefaultRole(RolesEnum rolesEnum){
        Role role = new Role();
        role.setId(rolesEnum.getId());
        role.setName(rolesEnum.getRoleName());

        return role;
    }

}
