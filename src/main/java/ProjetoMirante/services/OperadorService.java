package ProjetoMirante.services;

import ProjetoMirante.entidades.Operador;
import ProjetoMirante.enums.OperadorEnum;
import ProjetoMirante.enums.StatusEnum;
import ProjetoMirante.repository.OperadorRepository;

import java.util.ArrayList;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OperadorService 
{
    @Autowired
    private OperadorRepository operadorRepository;

    @Transactional
    public void adicionarOperador(Operador operador) throws Exception
    {
        try
        {
            if (buscarOperador(operador.getLogin()) != null)
            {
                throw new Exception("Operaddor já cadastrado.");
            }

            operador.setDataCadastro(new Date());

            salvar(operador);
        } 

        catch (final Exception e)
        {
             throw new  Exception("Erro ao adicionar Administrador.");
        }
    }

    @Transactional (readOnly = true)
    public Operador buscarOperador(String login)
    {
        return operadorRepository.buscarOperador(login);
    }

    @Transactional (readOnly = true)
    public ArrayList<Operador> buscarTodos()
    {
        ArrayList<Operador> operadores = new ArrayList<>();

        for(Operador o : operadorRepository.buscarOperadores())
        {
            operadores.add(o);
        }

        return operadores;
    }

    public ArrayList<String> buscarPerfis()
    {
        ArrayList<String> perfis = new ArrayList<>();
        
        perfis.add(OperadorEnum.ADMINISTRADOR.getTipoOperador());
        perfis.add(OperadorEnum.ANALISTA.getTipoOperador());
        perfis.add(OperadorEnum.GERENTE.getTipoOperador());

        return perfis ;
    }

    public ArrayList<String> buscarStatus()
    {
        ArrayList<String> status = new ArrayList<>();
        
        status.add(StatusEnum.ATIVO.getStatus());
        status.add(StatusEnum.INATIVO.getStatus());

        return status ;
    }

    @Transactional
    public void editarOperador(Operador operador) throws Exception
    {
        try
        {
            operador.editar
            (operador.getNome(), operador.getLogin(), operador.getPerfil(), operador.isAtivo());

            salvar(operador);
        } 
        
        catch (Exception e)
        {
            throw e;
        }
    }

    public Operador salvar(final Operador operador)
    {
        return operadorRepository.save(operador);
    }
}
