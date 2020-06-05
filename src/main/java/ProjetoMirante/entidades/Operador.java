package ProjetoMirante.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Operador implements Serializable
{
    private static final long serialVersionUID = 3231692012109406169L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String nome;

    private String login;

    private String senha;

    private String perfil;

    @Temporal(TemporalType.DATE)
    private Date dataCadastro;
    
    private String status;


    public Operador() {

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

    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }
    

    @Override
    public String toString() {
        return "Operador{" + "id=" + id + ", nome=" + nome + ", login=" + login + ", senha=" + senha + ", perfil=" + perfil + ", dataCadastro=" + dataCadastro + ", satus=" + status + '}';
    }
    
    public void editar(String nome, String login, String perfil, String status)
    {
        this.setNome(nome);
        this.setLogin(login);
        this.setPerfil(perfil);
        this.setStatus(status);
    }
}
