package ProjetoMirante.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Telefone implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String ddd;

    private String numero;

    private String tipoTelefone;

    @Temporal(TemporalType.DATE)
    private Date dataCadastro;

    @ManyToOne
    private Pessoa pessoa;
    
    @ManyToOne
    private Operador operador;
    
    public Telefone() 
    {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipoTelefone() {
        return tipoTelefone;
    }

    public void setTipoTelefone(String tipoTelefone) {
        this.tipoTelefone = tipoTelefone;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Operador getOperador() {
        return operador;
    }

    public void setOperador(Operador operador) {
        this.operador = operador;
    }

    @Override
    public String toString() {
        return "Telefone{" + "id=" + id + ", ddd=" + ddd + ", numero=" + numero + ", tipoTelefone=" + tipoTelefone + ", dataCadastro=" + dataCadastro + ", pessoa=" + pessoa + ", operador=" + operador + '}';
    }

    public void editar(String ddd, String numero, String tipoTelefone)
    {
        this.setDdd(ddd);
        this.setNumero(numero);
        this.setTipoTelefone(tipoTelefone);
    }
}
