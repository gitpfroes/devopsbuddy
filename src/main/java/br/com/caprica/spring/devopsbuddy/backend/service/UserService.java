package br.com.caprica.spring.devopsbuddy.backend.service;


import br.com.caprica.spring.devopsbuddy.backend.persistence.domain.backend.Plan;
import br.com.caprica.spring.devopsbuddy.backend.persistence.domain.backend.User;
import br.com.caprica.spring.devopsbuddy.backend.persistence.domain.backend.UserRole;
import br.com.caprica.spring.devopsbuddy.backend.persistence.repositories.PlanRepository;
import br.com.caprica.spring.devopsbuddy.backend.persistence.repositories.RoleRepository;
import br.com.caprica.spring.devopsbuddy.backend.persistence.repositories.UserRepository;
import br.com.caprica.spring.devopsbuddy.enums.PlansEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    public User createUser(User user, PlansEnum plansEnum, Set<UserRole> userRoles) {
        String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        User localUser = userRepository.findByUsername(user.getUsername());

        if (localUser != null) {
            LOG.info("User with username {} and email {} already exist. Nothing will be done. ",
                    user.getUsername(), user.getEmail());
        } else {

            Plan plan = new Plan(plansEnum);
            // It makes sure the plans exist in the database
            if (!planRepository.exists(plansEnum.getId())) {
                plan = planRepository.save(plan);
            }

            user.setPlan(plan);

            for (UserRole ur : userRoles) {
                roleRepository.save(ur.getRole());
            }

            user.getUserRoles().addAll(userRoles);

            localUser = userRepository.save(user);

        }

        return localUser;
    }
}
