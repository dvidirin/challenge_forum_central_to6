package forum.central.domain.curso;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastramentoCurso(
        @NotBlank String nome,
        @NotNull Categoria categoria) {
}
