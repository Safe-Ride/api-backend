package school.sptech.saferide.constants;

public class SafeRideConstants {
    public final static String REGEX_EMAIL = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    public final static String REGEX_DATA_NASCIMENTO = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$\n";
    public final static String USUARIO_INVALID_NAME = "Campo 'nome' nulo ou inválido. Deve possuir no mínimo 3 caracteres.";
    public final static String USUARIO_INVALID_EMAIL = "Campo 'email' nulo ou inválido.";
    public final static String USUARIO_INVALID_PASSWORD = "Campo 'senha' nulo ou inválido. Deve conter no mínimo 4 caracteres";
    public final static String USUARIO_INVALID_TIPO = "Campo 'tipo' nulo ou inválido. Deve ser entre: RESPONSAVEL e MOTORISTA.";
    public final static String USUARIO_INVALID_DATA_NASCIMENTO = "Campo 'dataNascimento' nulo ou inválido. Deve estar no formato YYYY-MM-DD.";
    public final static String BUCKET_FOTO_PERFIL_NAME = "bucket-foto-perfil-saferide1";
}
