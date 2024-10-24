package school.sptech.saferide.model.enums;

public enum TipoTrajeto {
    IDA, VOLTA;

    public static TipoTrajeto mapNumber(Integer number) {
        TipoTrajeto[] values = TipoTrajeto.values();

        if (number == null || number < 1 || number > values.length) {
            throw new IllegalArgumentException("Número inválido: " + number);
        }

        return values[number - 1];
    }
}
