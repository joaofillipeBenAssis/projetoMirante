package ProjetoMirante.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ProjetoMirante.entidades.Role;

public interface RoleRepository extends CrudRepository<Role, Long>
{
    @Query("select r from Role r where r.description = :role")
    Role buscarRole(@Param("role") String role);
}
