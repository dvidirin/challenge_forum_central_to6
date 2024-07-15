package forum.central.domain.topico;

import forum.central.domain.curso.CursoRepository;
import forum.central.domain.usuario.UsuarioRepository;
import forum.central.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistroDeTopico {
    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @SuppressWarnings("unused")
    public Topico criarNovoTopico(DadosCadastramentoTopico dados, String emailAutor) {
        if (cursoRepository.getReferenceById(dados.idCurso()) == null) {
            throw new ValidacaoException("Não foi possível encontrar o curso informado");
        }
        var curso = cursoRepository.getReferenceById(dados.idCurso());
        var autor = usuarioRepository.findByEmail(emailAutor);
        var topico = new Topico(dados.titulo(), dados.mensagem(), autor, curso);
        topicoRepository.save(topico);
        return topico;
    }

}
