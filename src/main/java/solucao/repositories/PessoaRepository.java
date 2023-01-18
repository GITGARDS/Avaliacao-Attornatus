package solucao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import solucao.models.PessoaModel;

public interface PessoaRepository extends JpaRepository<PessoaModel, Long> {

}
