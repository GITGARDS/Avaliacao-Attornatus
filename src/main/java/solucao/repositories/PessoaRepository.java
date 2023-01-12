package solucao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import solucao.models.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
