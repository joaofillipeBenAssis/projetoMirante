package ProjetoMirante.services;

import ProjetoMirante.entidades.Operador;
import ProjetoMirante.entidades.Role;
import ProjetoMirante.enums.OperadorEnum;
import ProjetoMirante.repository.OperadorRepository;
import ProjetoMirante.repository.RoleRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OperadorService 
{
    @Autowired
    private OperadorRepository operadorRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public void adicionarOperador(Operador operador) throws Exception
    {
        try
        {
            if (buscarOperador(operador.getLogin()) != null)
            {
                throw new Exception("Operaddor já cadastrado.");
            }

            Role role = new Role();
        
            if(roleRepository.buscarRole(operador.getPerfil()) == null)
            {
                role.setDescription(operador.getPerfil());
                roleRepository.save(role);
            }

            role = roleRepository.buscarRole(operador.getPerfil());

            List<Role> roles = new ArrayList<>();
            roles.add(role);

            operador.setDataCadastro(new Date());
            operador.setRoles(roles);

            salvar(operador);
        } 

        catch (final Exception e)
        {
             throw new  Exception("Erro ao adicionar Operador.");
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
