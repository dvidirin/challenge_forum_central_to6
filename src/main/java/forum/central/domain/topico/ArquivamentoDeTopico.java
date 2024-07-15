package forum.central.domain.topico;

import forum.central.domain.resposta.Resposta;
import forum.central.domain.resposta.RespostaRepository;
import forum.central.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArquivamentoDeTopico {
    private final TopicoRepository repository;
    private final RespostaRepository respostaRepository;

    @Autowired
    public ArquivamentoDeTopico(TopicoRepository repository, RespostaRepository respostaRepository) {
        this.repository = repository;
        this.respostaRepository = respostaRepository;
    }

    @SuppressWarnings("unused")
    public void arquivar(Long idTopico, String emailAutor) {
        var topico = repository.getReferenceById(idTopico);
        if (topico == null) {
            throw new RuntimeException("Não foi possível encontrar o tópico selecionado");
        }
        var autor = topico.getAutor().getEmail();
        if (!autor.equals(emailAutor)) {
            throw new ValidacaoException("Você não tem permissão para isso.");
        }
        respostaRepository.buscarRespostasRelacionadasATopico(idTopico).forEach(Resposta::excluirResposta);
        topico.excluirTopico();
    }
}
