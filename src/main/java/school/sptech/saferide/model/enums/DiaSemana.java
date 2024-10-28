package school.sptech.saferide.model.enums;

public enum DiaSemana {
    SEGUNDA, TERCA, QUARTA, QUINTA, SEXTA;

    public static DiaSemana mapNumber(Integer number) {
        DiaSemana[] values = DiaSemana.values();

        if (number == null || number < 1 || number > values.length) {
            throw new IllegalArgumentException("Número inválido: " + number);
        }

        return values[number - 1];
    }
}
