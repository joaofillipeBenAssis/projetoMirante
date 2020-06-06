package ProjetoMirante.entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import ProjetoMirante.util.PasswordEncoderGenerator;

@Entity
public class Operador implements UserDetails, Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String nome;

    private String login;

    private String senha;

    private String perfil;

    @Temporal(TemporalType.DATE)
    private Date dataCadastro;
    
    private boolean ativo; 

    @ManyToMany
    @JoinTable( 
	    name = "usuarios_roles", 
	    joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "login"), 
	    inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "nomeRole")) 
    private List<Role> roles;

    public Operador() 
    {
    }

    @PrePersist
    public void prePersistir()
    {
        ativar();
        cifraSenha();
    }

    private void cifraSenha()
    {
        PasswordEncoderGenerator senha = new PasswordEncoderGenerator();
        setSenha(senha.encode("12345"));
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return "Operador{" + "id=" + id + ", nome=" + nome + ", login=" + login + ", perfil=" + perfil + ", dataCadastro=" + dataCadastro + ", ativo=" + ativo + ", roles=" + roles + '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() 
    {
		return (Collection<? extends GrantedAuthority>) this.roles;
	}

	@Override
    public String getPassword() 
    {
		return this.senha;
	}

	@Override
    public String getUsername() 
    {
		return this.login;
	}

	@Override
    public boolean isAccountNonExpired() 
    {
		return true;
	}

	@Override
    public boolean isAccountNonLocked() 
    {
		return true;
	}

	@Override
    public boolean isCredentialsNonExpired() 
    {
		return true;
	}

	@Override
    public boolean isEnabled() 
    {
		return true;
    }
    
    public void ativar()
    {
        this.ativo = true;
    }
    
    public void editar(String nome, String login, String perfil, boolean ativo)
    {
        this.setNome(nome);
        this.setLogin(login);
        this.setPerfil(perfil);
        this.setAtivo(ativo);
    }
}
