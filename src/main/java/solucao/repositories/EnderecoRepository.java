package solucao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import solucao.dtos.EnderecoDto;
import solucao.models.Endereco;
import solucao.models.Pessoa;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

	List<EnderecoDto> findByPessoaId(Pessoa findPessoa);

}
