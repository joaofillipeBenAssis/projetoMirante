package ProjetoMirante.enums;

public enum PessoaEnum 
{
    FISICA("Física"), 
    JURÍDICA("Jurídica");
    
    private final String tipoPessoa;

    PessoaEnum(String tipoPessoa) 
    {
        this.tipoPessoa = tipoPessoa;
    }

    public String getTipoPessoa()
    {
        return tipoPessoa;
    }
}
