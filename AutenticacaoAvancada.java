import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AutenticacaoAvancada {
    
    private static Map<String, String[]> usuarios = new HashMap<>();
    public static void main(String[] args) throws NoSuchAlgorithmException {

        Scanner scanner = new Scanner(System.in);
        String usuarioExemplo = "usuarioAvancado";
        String senhaExemplo = "senhaAvancada";
        String saltExemplo = SenhaUtilAvancada.gerarSalt();
        String hashExemplo = SenhaUtilAvancada.gerarHash(senhaExemplo, saltExemplo);
        usuarios.put(usuarioExemplo, new String[]{hashExemplo, saltExemplo});
        System.out.println("Usuário 'usuarioAvancado' registrado com sucesso!");
        System.out.println("Escolha uma opção:");
        System.out.println("1. Registrar");
        System.out.println("2. Login");
        int escolha = scanner.nextInt();
        scanner.nextLine(); 

        if (escolha == 1) {
            System.out.print("Nome de usuário: ");
            String usuario = scanner.nextLine();
            System.out.print("Senha: ");
            String senha = scanner.nextLine();
            String salt = SenhaUtilAvancada.gerarSalt();
            String hash = SenhaUtilAvancada.gerarHash(senha, salt);
            usuarios.put(usuario, new String[]{hash, salt});
            System.out.println("Usuário registrado com sucesso!");

        } else if (escolha == 2) {
            System.out.print("Nome de usuário: ");
            String usuario = scanner.nextLine();
            System.out.print("Senha: ");
            String senha = scanner.nextLine();
            String[] dados = usuarios.get(usuario);

            if (dados != null) {
                String hashArmazenado = dados[0];
                String salt = dados[1];

                String hash = SenhaUtilAvancada.gerarHash(senha, salt);

                if (hash.equals(hashArmazenado)) {
                    System.out.println("Login bem-sucedido!");
                } else {
                    System.out.println("Senha incorreta!");
                }

            } else {
                System.out.println("Usuário não encontrado!");
            }
        }
        scanner.close();
    }
}
