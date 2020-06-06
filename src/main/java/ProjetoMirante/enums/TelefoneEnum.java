package ProjetoMirante.enums;

public enum TelefoneEnum 
{
    CELULAR("Celular"), 
    COMERCIAL("Comercial"), 
    FIXO("Fixo");
    
    private final String tipoTelefone;

    TelefoneEnum(String tipoTelefone) 
    {
        this.tipoTelefone = tipoTelefone;
    }

    public String getTipoTelefone()
    {
        return tipoTelefone;
    }
}
