package forum.central.domain.curso;

public record DadosDetalhamentoCurso(Long id, String nome, String categoria) {
    public DadosDetalhamentoCurso(Curso dados) {
        this(dados.getId(), dados.getNome(), dados.getCategoria());
    }
}
