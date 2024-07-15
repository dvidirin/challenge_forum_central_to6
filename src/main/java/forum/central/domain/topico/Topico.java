package forum.central.domain.topico;

import forum.central.domain.curso.Curso;
import forum.central.domain.resposta.Resposta;
import forum.central.domain.usuario.Usuario;
import forum.central.infra.exception.ValidacaoException;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "Topico")
@Table(name = "topicos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensagem;
    private LocalDateTime dataDeCriacao;
    private String status;
    private boolean ativo;

    @ManyToOne
    @JoinColumn(name = "id_autor")
    private Usuario autor;

    @ManyToOne
    @JoinColumn(name = "id_curso")
    private Curso curso;

    @OneToMany
    @JoinColumn(name = "resposta_id")
    private List<Resposta> respostas;

    public Topico(String titulo, String mensagem, Usuario autor, Curso curso) {
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.autor = autor;
        this.curso = curso;
        this.status = Status.ABERTO.toString();
        this.dataDeCriacao = LocalDateTime.now();
        this.ativo = true;
    }

    public void fecharTopico() {
        this.status = Status.FECHADO.toString();
    }

    public void atualizarDados(DadosAtualizacaoTopico dados) {
        if (this.status == Status.FECHADO.toString()) {
            throw new ValidacaoException("Este tópico está fechado. Não é possível alterá-lo");
        }
        if (dados.titulo() != null) {
            this.titulo = dados.titulo();
        }
        if (dados.mensagem() != null) {
            this.mensagem = dados.mensagem();
        }
        this.dataDeCriacao = LocalDateTime.now();
    }

    public void excluirTopico() {
        this.ativo = false;
    }
}
