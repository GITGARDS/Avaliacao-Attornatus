package solucao.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import solucao.domain.models.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
