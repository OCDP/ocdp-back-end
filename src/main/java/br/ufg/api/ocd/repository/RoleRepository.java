package br.ufg.api.ocd.repository;

import br.ufg.api.ocd.model.Role;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface RoleRepository extends CrudRepository<Role, String> {

    Role findByName(String name);
}
