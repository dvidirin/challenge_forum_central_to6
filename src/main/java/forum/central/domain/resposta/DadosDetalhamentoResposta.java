package forum.central.domain.resposta;

import forum.central.infra.utils.FormatadorData;

public record DadosDetalhamentoResposta(Long id, String conteudo, Long topico_id, String dataDeCriacao, String autor,
                                        String solucao) {
    public DadosDetalhamentoResposta(Resposta dados) {
        this(dados.getId(), dados.getConteudo(), dados.getTopico().getId(), FormatadorData.formatarDataPadraoBrasil(dados.getDataDeCriacao()), dados.getAutor().getNome(), dados.getSolucao());
    }
}
