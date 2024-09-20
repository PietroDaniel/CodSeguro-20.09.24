import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class SenhaUtil {
    private static final String ALGORITMO_HASH = "SHA-256";
    private static final SecureRandom random = new SecureRandom();

    public static String gerarSalt() {
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    public static String gerarHash(String senha, String salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(ALGORITMO_HASH);
        md.update(Base64.getDecoder().decode(salt));
        byte[] senhaHash = md.digest(senha.getBytes());
        return Base64.getEncoder().encodeToString(senhaHash);
    }
    public static void main(String[] args) {
        try {
            String senha = "minhaSenhaSegura";
            String salt = gerarSalt();
            String hash = gerarHash(senha, salt);

            System.out.println("Senha original: " + senha);
            System.out.println("Salt: " + salt);
            System.out.println("Hash: " + hash);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Erro ao gerar o hash: " + e.getMessage());
        }
    }
}
