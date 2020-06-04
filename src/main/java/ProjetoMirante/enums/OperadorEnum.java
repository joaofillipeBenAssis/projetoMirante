package ProjetoMirante.enums;

public enum OperadorEnum 
{
    ADMINISTRADOR("Administrador"), 
    ANALISTA("Analista"), 
    GERENTE("Gerente");
    
    private final String tipoOPerador;

    OperadorEnum(String tipoOPerador) 
    {
        this.tipoOPerador = tipoOPerador;
    }

    public String getTipoOperador()
    {
        return tipoOPerador;
    }
}
