package ProjetoMirante.controller;

import ProjetoMirante.entidades.Operador;
import ProjetoMirante.services.OperadorService;

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
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/operador")
public class OperadorController
{
    @Autowired
    private OperadorService operadorService;

    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    public ResponseEntity<?> salvar(@RequestBody Operador operador) {
        try 
        {
            operadorService.adicionarOperador(operador);
            return null;
        }

        catch (final Exception e) 
        {
            return httpStatusInfo("Erro ao Salvar Operador. Tente Novamente.", HttpStatus.FORBIDDEN);
        }

    }

    @RequestMapping(value = "/exibirPerfis", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<String> exibirPerfis() throws JsonProcessingException 
    {
        try
        {
            return operadorService.buscarPerfis();
        } 
        
        catch (Exception e)
        {
            return null;
        } 
    }

    @RequestMapping(value = "/exibirTodos", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> exibirTodos() throws JsonProcessingException 
    {

        try
        {
            return returnList(operadorService.buscarTodos());
        } 
        
        catch (Exception e)
        {
            e.printStackTrace();
            return httpStatusInfo(e, HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(value = "/exibirUsuario", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?>  exibirUsuario()
    {
        try
        {
            return returnList(buscarUsuario());
        } 
        
        catch (Exception e)
        {
            e.printStackTrace();
            return httpStatusInfo(e, HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(value = "/editar", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<?> editar(@RequestBody Operador operador)
    {
        try
        {
            operadorService.editarOperador(operador);
            return httpStatusInfo(null, HttpStatus.OK);
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
