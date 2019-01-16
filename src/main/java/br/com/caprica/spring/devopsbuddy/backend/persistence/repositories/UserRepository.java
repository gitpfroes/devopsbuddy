package br.com.caprica.spring.devopsbuddy.backend.persistence.repositories;

import br.com.caprica.spring.devopsbuddy.backend.persistence.domain.backend.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
