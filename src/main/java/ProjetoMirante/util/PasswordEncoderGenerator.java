package ProjetoMirante.util;

import java.security.NoSuchAlgorithmException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderGenerator 
{
    public boolean compare(String senhaNormal,String senhaHash)
    {
        BCryptPasswordEncoder d = new BCryptPasswordEncoder();
        return d.matches(senhaNormal, senhaHash);
        
    }
    public String encode(String senha)
    {
        try
        {
            BCryptPasswordEncoder d = new BCryptPasswordEncoder(12);
            return d.encode(senha);
        }
        
        catch(Exception e)
        {
            e.printStackTrace();
            throw e;
        }
    }

    public static void main(String[] args) throws NoSuchAlgorithmException
    {
        String  originalPassword = "12345";
        PasswordEncoderGenerator d = new PasswordEncoderGenerator();

        String generatedSecuredPasswordHash = d.encode(originalPassword);
        System.out.println(generatedSecuredPasswordHash);
         
        boolean matched = d.compare(originalPassword, generatedSecuredPasswordHash);
        System.out.println(matched);
    }
    
 
}