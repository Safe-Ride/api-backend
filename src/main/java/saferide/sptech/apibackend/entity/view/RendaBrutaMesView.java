package saferide.sptech.apibackend.entity.view;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "v_renda_bruta_mes")
public class RendaBrutaMesView {

    @Id
    private LocalDate data;
    private Integer valor;

}
