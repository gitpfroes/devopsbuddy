package br.com.caprica.spring.devopsbuddy;

import br.com.caprica.spring.devopsbuddy.backend.persistence.domain.backend.Role;
import br.com.caprica.spring.devopsbuddy.backend.persistence.domain.backend.User;
import br.com.caprica.spring.devopsbuddy.backend.persistence.domain.backend.UserRole;
import br.com.caprica.spring.devopsbuddy.backend.service.UserService;
import br.com.caprica.spring.devopsbuddy.enums.PlansEnum;
import br.com.caprica.spring.devopsbuddy.enums.RolesEnum;
import br.com.caprica.spring.devopsbuddy.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.HashSet;
import java.util.Set;

@EnableJpaRepositories(basePackages = "br.com.caprica.spring.devopsbuddy.backend.persistence.repositories")
@SpringBootApplication
public class DevopsbuddyApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DevopsbuddyApplication.class, args);
    }

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        User user = UserUtils.createBasicUser("admin", "jose@teste.com");
        Set<UserRole> roles = new HashSet<>();
        roles.add(new UserRole(user, new Role(RolesEnum.PRO)));
        userService.createUser(user, PlansEnum.PRO, roles);
    }
}
