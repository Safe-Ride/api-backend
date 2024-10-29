package school.sptech.saferide.model.enums;

public enum HorarioTrajeto {
    MANHA, TARDE;

    public static HorarioTrajeto mapNumber(Integer number) {
        HorarioTrajeto[] values = HorarioTrajeto.values();

        if (number == null || number < 1 || number > values.length) {
            throw new IllegalArgumentException("Número inválido: " + number);
        }

        return values[number - 1];
    }
}
