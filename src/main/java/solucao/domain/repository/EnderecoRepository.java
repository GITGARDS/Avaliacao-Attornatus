package solucao.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import solucao.domain.models.Endereco;
import solucao.domain.models.Pessoa;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

	List<Endereco> findByPessoaId(Pessoa findPessoa);

}
