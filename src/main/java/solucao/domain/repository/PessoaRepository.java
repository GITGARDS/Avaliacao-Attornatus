package solucao.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import solucao.domain.models.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	Optional<Pessoa> findByNome(String nome);

}
