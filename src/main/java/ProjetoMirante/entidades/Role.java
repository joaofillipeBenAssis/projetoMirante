package ProjetoMirante.entidades;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role implements GrantedAuthority
{
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private long id;
    
    private String description;
    
    @ManyToMany(mappedBy = "roles")
    private List<Operador> operadores;

    public Role() 
    {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Operador> getOperadores() {
        return operadores;
    }

    public void setUsuarios(List<Operador> operadores) 
    {
        this.operadores = operadores;
    }
	
    @Override
    public String getAuthority() 
    {
	return this.description;
    }

    @Override
    public String toString() {
        return "Role{" + "id=" + id + ", description=" + description + ", operadores=" + operadores + '}';
    }
    
}