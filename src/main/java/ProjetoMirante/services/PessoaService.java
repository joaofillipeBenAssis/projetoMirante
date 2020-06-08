package ProjetoMirante.services;

import ProjetoMirante.entidades.Operador;
import ProjetoMirante.entidades.Pessoa;
import ProjetoMirante.entidades.Telefone;
import ProjetoMirante.enums.PessoaEnum;
import ProjetoMirante.enums.TelefoneEnum;
import ProjetoMirante.repository.OperadorRepository;
import ProjetoMirante.repository.PessoaRepository;
import ProjetoMirante.repository.TelefoneRepository;

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

    @Autowired
    private TelefoneRepository telefoneRepository;

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
             throw new  Exception("Erro ao adicionar Pessoa.");
        }
    }

    @Transactional
    public void adicionarTelefone(Pessoa pessoa,  Operador operador, Telefone telefone) throws Exception
    {
        try
        {
            if (buscarTelefone(telefone.getNumero()) != null)
            {
                throw new Exception("Telefone já cadastrado.");
            }

            Operador o = operadorRepository.buscarOperador(operador.getLogin());
            Pessoa p = buscarPessoa(pessoa.getDocumento());

            
            telefone.setDataCadastro(new Date());
            telefone.setOperador(o);
            telefone.setPessoa(p);

            telefoneRepository.save(telefone);
        } 

        catch (final Exception e)
        {
             throw new  Exception("Erro ao adicionar Telefone.");
        }
    }

    @Transactional (readOnly = true)
    public Pessoa buscarPessoa(String documento)
    {
        return pessoaRepository.buscarPessoa(documento);
    }

    @Transactional (readOnly = true)
    public Telefone buscarTelefone(String telefone)
    {
        return telefoneRepository.buscarTelefone(telefone);
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

    @Transactional (readOnly = true)
    public ArrayList<Telefone> buscarTelefones(long id)
    {
        ArrayList<Telefone> telefones = new ArrayList<>();

        for(Telefone t : telefoneRepository.buscarPorPessoaId(id))
        {
            telefones.add(t);
        }

        return telefones;
    }

    public ArrayList<String> buscarTiposPessoa()
    {
        ArrayList<String> tiposPessoas = new ArrayList<>();
        
        tiposPessoas.add(PessoaEnum.FISICA.getTipoPessoa());
        tiposPessoas.add(PessoaEnum.JURÍDICA.getTipoPessoa());

        return tiposPessoas;
    }

    public ArrayList<String> buscarTiposTelefone()
    {
        ArrayList<String> tiposTelefone = new ArrayList<>();
        
        tiposTelefone.add(TelefoneEnum.CELULAR.getTipoTelefone());
        tiposTelefone.add(TelefoneEnum.COMERCIAL.getTipoTelefone());
        tiposTelefone.add(TelefoneEnum.FIXO.getTipoTelefone());

        return tiposTelefone ;
    }

    @Transactional
    public void editarTelefone(Telefone telefone) throws Exception
    {
        try
        {
            Telefone t = buscarTelefone(telefone.getNumero());

            t.editar
            (telefone.getDdd(), telefone.getNumero(), telefone.getTipoTelefone());

            telefoneRepository.save(telefone);
        } 
        
        catch (Exception e)
        {
            throw e;
        }
    }

}
