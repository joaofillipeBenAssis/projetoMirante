package ProjetoMirante.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ProjetoMirante.entidades.Operador;

public interface OperadorRepository extends CrudRepository<Operador, Long>
{
    @Query("Select o from Operador o where o.login = :login")
    Operador buscarOperador(@Param("login") String login);

    @Query("Select o from Operador o order by o.nome asc")
    ArrayList<Operador> buscarOperadores();
}
