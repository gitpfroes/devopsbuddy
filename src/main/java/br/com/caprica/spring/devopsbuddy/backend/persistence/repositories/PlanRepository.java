package br.com.caprica.spring.devopsbuddy.backend.persistence.repositories;

import br.com.caprica.spring.devopsbuddy.backend.persistence.domain.backend.Plan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends CrudRepository<Plan, Integer> {
}