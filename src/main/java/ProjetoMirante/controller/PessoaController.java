package ProjetoMirante.controller;

import ProjetoMirante.entidades.Operador;
import ProjetoMirante.entidades.Pessoa;
import ProjetoMirante.entidades.Telefone;
import ProjetoMirante.services.OperadorService;
import ProjetoMirante.services.PessoaService;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/pessoa")
public class PessoaController
{
    @Autowired
    private OperadorService operadorService;

    @Autowired
    private PessoaService pessoaService;

    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    public ResponseEntity<?> salvar(@RequestBody Pessoa pessoa) 
    {
        try 
        {
            Operador operador = buscarUsuario();

            pessoaService.adicionarPessoa(pessoa, operador);
            return null;
        }

        catch (final Exception e) 
        {
            return httpStatusInfo("Erro ao Salvar Pessoa. Tente Novamente.", HttpStatus.FORBIDDEN);
        }

    }

    @RequestMapping(value = "/salvarTelefone", method = RequestMethod.POST)
    public ResponseEntity<?> salvarTelefone(@RequestBody Telefone telefone) 
    {
        try 
        {
            Operador operador = buscarUsuario();

            pessoaService.adicionarTelefone(telefone.getPessoa(), operador, telefone);
            return null;
        }

        catch (final Exception e) 
        {
            return httpStatusInfo("Erro ao Salvar Telefone. Tente Novamente.", HttpStatus.FORBIDDEN);
        }

    }

    @RequestMapping(value = "/exibirTiposPessoa", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<String> exibirTiposPessoa() throws JsonProcessingException 
    {
        try
        {
            return pessoaService.buscarTiposPessoa();
        } 
        
        catch (Exception e)
        {
            return null;
        } 
    }

    @RequestMapping(value = "/exibirTiposTelefone", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<String> exibirTiposTelefone() throws JsonProcessingException 
    {
        try
        {
            return pessoaService.buscarTiposTelefone();
        } 
        
        catch (Exception e)
        {
            return null;
        } 
    }

    @RequestMapping(value = "/exibirPessoas", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> exibirPessoas() throws JsonProcessingException 
    {

        try
        {
            return returnList(pessoaService.buscarPessoas());
        } 
        
        catch (Exception e)
        {
            return httpStatusInfo(e, HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(value = "/exibirTelefones", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> exibirTelefones(@RequestParam long id) throws JsonProcessingException 
    {

        try
        {
            return returnList(pessoaService.buscarTelefones(id));
        } 
        
        catch (Exception e)
        {
            return httpStatusInfo(e, HttpStatus.FORBIDDEN);
        }
    }

    private Operador buscarUsuario()
    {        
        return convertePrincipal((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    private Operador convertePrincipal(org.springframework.security.core.userdetails.User o)
    {
        return operadorService.buscarOperador(o.getUsername());
    }

    private ResponseEntity<?> returnList(Object object) throws JsonProcessingException
    {
        ObjectMapper mapper = new ObjectMapper();

        mapper.registerModule(new Hibernate4Module().disable(Hibernate4Module.Feature.USE_TRANSIENT_ANNOTATION));

        return new ResponseEntity<>(mapper.writer().writeValueAsString(object), HttpStatus.OK);
    }

    public ResponseEntity<Object> httpStatusInfo(final Object msg, final HttpStatus h)
    {
        return new ResponseEntity<>(msg, h);
    }

}
