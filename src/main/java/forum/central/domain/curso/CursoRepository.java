package forum.central.domain.curso;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    @SuppressWarnings("null")
    Page<Curso> findAll(Pageable pagina);
}
