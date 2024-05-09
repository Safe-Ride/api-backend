package saferide.sptech.apibackend.dto.dependente;

import saferide.sptech.apibackend.entity.Escola;
import saferide.sptech.apibackend.entity.Usuario;
import saferide.sptech.apibackend.entity.Dependente;

import java.util.List;

public class DependenteMapper {

    public static DependenteResponse toDto(Dependente entity){
        if (entity == null) return null;

        DependenteResponse dto = new DependenteResponse();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setDataNascimento(entity.getDataNascimento());
        dto.setSerie(entity.getSerie());
        dto.setResponsavel(toResponsavelDto(entity.getResponsavel()));
        dto.setEscola(toEscolaDto(entity.getEscola()));
        dto.setMotorista(toMotoristaDto(entity.getMotorista()));
        return dto;

    }

    public static List<DependenteResponse> toDto(List<Dependente> entities){
        return entities.stream()
                .map(DependenteMapper::toDto)
                .toList();
    }

    public static Dependente toEntity(DependenteRequest dto, Usuario responsavel, Escola escola){
        if (dto == null) return null;

        Dependente entity = new Dependente();
        entity.setNome(dto.getNome());
        entity.setDataNascimento(dto.getDataNascimento());
        entity.setSerie(dto.getSerie());
        entity.setResponsavel(responsavel);
        entity.setEscola(escola);
        return entity;
    }

    public static Dependente toEntityAtt(DependenteRequestUpdate dto, Dependente entity){
        if (dto == null) return null;

        if (dto.getNome() != null) entity.setNome(dto.getNome());
        if (dto.getNome() != null) entity.setDataNascimento(dto.getDataNascimento());
        if (dto.getNome() != null) entity.setSerie(dto.getSerie());
        return entity;
    }

    public static DependenteResponsavelResponse toResponsavelDto(Usuario entity) {
        if (entity == null) return null;

        DependenteResponsavelResponse dto = new DependenteResponsavelResponse();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setEmail(entity.getEmail());
        dto.setCpf(entity.getCpf());
        dto.setTelefone(entity.getTelefone());
        dto.setDataNascimento(entity.getDataNascimento());
        return dto;
    }

    public static List<DependenteResponsavelResponse> toResponsavelDto(List<Usuario> entities) {
        return entities.stream()
                .map(DependenteMapper::toResponsavelDto)
                .toList();
    }

    public static DependenteEscolaResponse toEscolaDto(Escola entity) {
        if (entity == null) return null;

        DependenteEscolaResponse dto = new DependenteEscolaResponse();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        return dto;
    }

    public static List<DependenteEscolaResponse> toEscolaDto(List<Escola> entities) {
        return entities.stream()
                .map(DependenteMapper::toEscolaDto)
                .toList();
    }

    public static DependenteMotoristaResponse toMotoristaDto(Usuario entity) {
        if (entity == null) return null;

        DependenteMotoristaResponse dto = new DependenteMotoristaResponse();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setEmail(entity.getEmail());
        dto.setCpf(entity.getCpf());
        dto.setTelefone(entity.getTelefone());
        dto.setDataNascimento(entity.getDataNascimento());
        return dto;
    }

    public static List<DependenteMotoristaResponse> toMotoristaDto(List<Usuario> entities) {
        return entities.stream()
                .map(DependenteMapper::toMotoristaDto)
                .toList();
    }

}
