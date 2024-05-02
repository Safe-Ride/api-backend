package saferide.sptech.apibackend.entity;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.http.HttpStatus;

public enum DiaSemana {
    SEGUNDA, TERCA, QUARTA, QUINTA, SEXTA;

    public static DiaSemana get(String diaSemana){
        for (var value: values()){
            if (value.name().equalsIgnoreCase(diaSemana)){
                return value;
            }
        }
        throw new IllegalArgumentException();
    }

}
