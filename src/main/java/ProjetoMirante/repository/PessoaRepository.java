package ProjetoMirante.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ProjetoMirante.entidades.Pessoa;

public interface PessoaRepository extends CrudRepository<Pessoa, Long>
{
    @Query("Select p from Pessoa p where p.documento = :documento")
    Pessoa buscarPessoa(@Param("documento") String documento);

    @Query("Select p from Pessoa p order by p.nome asc")
    ArrayList<Pessoa> buscarPessoas();
}
