package solucao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import solucao.models.Endereco;
import solucao.models.Pessoa;
import solucao.models.dtos.EnderecoDto;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

	List<EnderecoDto> findByPessoaId(Pessoa findPessoa);

}
