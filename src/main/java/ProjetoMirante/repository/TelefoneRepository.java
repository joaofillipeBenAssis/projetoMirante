package ProjetoMirante.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ProjetoMirante.entidades.Telefone;

public interface TelefoneRepository extends CrudRepository<Telefone, Long>
{
    @Query("Select t from Telefone t where t.numero = :telefone")
    Telefone buscarTelefone(@Param("telefone") String telefone);

    @Query("Select t from Telefone t where t.pessoa.id = :id order by t.tipoTelefone asc")
    ArrayList<Telefone> buscarPorPessoaId(@Param("id") long id);
}
