package ProjetoMirante.services;

import ProjetoMirante.entidades.Operador;
import ProjetoMirante.entidades.Pessoa;
import ProjetoMirante.enums.PessoaEnum;
import ProjetoMirante.enums.TelefoneEnum;
import ProjetoMirante.repository.OperadorRepository;
import ProjetoMirante.repository.PessoaRepository;

import java.util.ArrayList;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PessoaService 
{
    @Autowired
    private OperadorRepository operadorRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Transactional
    public void adicionarPessoa(Pessoa pessoa,  Operador operador) throws Exception
    {
        try
        {
            if (buscarPessoa(pessoa.getDocumento()) != null)
            {
                throw new Exception("Pessoa já cadastrada.");
            }

            Operador o = operadorRepository.buscarOperador(operador.getLogin());
            
            pessoa.setDataCadastro(new Date());
            pessoa.setOperador(o);

            pessoaRepository.save(pessoa);
        } 

        catch (final Exception e)
        {
             throw new  Exception("Erro ao adicionar Administrador.");
        }
    }

    @Transactional (readOnly = true)
    public Pessoa buscarPessoa(String documento)
    {
        return pessoaRepository.buscarPessoa(documento);
    }

    @Transactional (readOnly = true)
    public ArrayList<Pessoa> buscarPessoas()
    {
        ArrayList<Pessoa> pessoas = new ArrayList<>();

        for(Pessoa p : pessoaRepository.buscarPessoas())
        {
            pessoas.add(p);
        }

        return pessoas;
    }

    public ArrayList<String> buscarTiposPessoa()
    {
        ArrayList<String> tiposPessoas = new ArrayList<>();
        
        tiposPessoas.add(PessoaEnum.FISICA.getTipoPessoa());
        tiposPessoas.add(PessoaEnum.JURÍDICA.getTipoPessoa());

        return tiposPessoas;
    }

    public ArrayList<String> buscarTipoTelefone()
    {
        ArrayList<String> tiposTelefone = new ArrayList<>();
        
        tiposTelefone.add(TelefoneEnum.CELULAR.getTipoTelefone());
        tiposTelefone.add(TelefoneEnum.COMERCIAL.getTipoTelefone());
        tiposTelefone.add(TelefoneEnum.FIXO.getTipoTelefone());

        return tiposTelefone ;
    }

    @Transactional
    public void editarOperador(Operador operador) throws Exception
    {
        try
        {
            /*
            operador.editar
            (operador.getNome(), operador.getLogin(), operador.getPerfil(), operador.isAtivo());

            ope(operador);

            */
        } 
        
        catch (Exception e)
        {
            throw e;
        }
    }

}
