package br.com.caprica.spring.devopsbuddy.backend.service;


import br.com.caprica.spring.devopsbuddy.backend.persistence.domain.backend.Plan;
import br.com.caprica.spring.devopsbuddy.backend.persistence.domain.backend.Role;
import br.com.caprica.spring.devopsbuddy.backend.persistence.domain.backend.User;
import br.com.caprica.spring.devopsbuddy.backend.persistence.domain.backend.UserRole;
import br.com.caprica.spring.devopsbuddy.backend.persistence.repositories.PlanRepository;
import br.com.caprica.spring.devopsbuddy.backend.persistence.repositories.RoleRepository;
import br.com.caprica.spring.devopsbuddy.backend.persistence.repositories.UserRepository;
import br.com.caprica.spring.devopsbuddy.enums.PlansEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional(readOnly = true)
public class UserService {

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public User createUser(User user, PlansEnum plansEnum, Set<UserRole> userRoles) {
        Plan plan = new Plan(plansEnum);

        if(!planRepository.exists(plansEnum.getId())){
            plan = planRepository.save(plan);
        }

        user.setPlan(plan);

        for (UserRole userRole : userRoles){
            roleRepository.save(userRole.getRole());
        }

        user.getUserRoles().addAll(userRoles);

        return user;
    }
}
