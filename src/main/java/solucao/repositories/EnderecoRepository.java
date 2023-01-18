package solucao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import solucao.models.EnderecoModel;
import solucao.models.PessoaModel;

public interface EnderecoRepository extends JpaRepository<EnderecoModel, Long> {

	List<EnderecoModel> findByPessoaId(PessoaModel findPessoa);

}
