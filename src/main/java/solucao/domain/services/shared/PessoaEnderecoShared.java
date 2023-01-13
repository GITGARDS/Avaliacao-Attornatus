package solucao.domain.services.shared;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import solucao.api.mappers.EnderecoMapper;
import solucao.domain.dtos.PessoaEnderecos;
import solucao.domain.models.Pessoa;
import solucao.domain.repository.EnderecoRepository;
import solucao.domain.repository.PessoaRepository;
import solucao.domain.services.exceptions.ObjectNotFoundException;

@AllArgsConstructor
@Service
public class PessoaEnderecoShared {

	private final EnderecoRepository enderecoRepository;

	private final PessoaRepository pessoaRepository;

	private final EnderecoMapper enderecoMapper;

	public Pessoa findById(Long id) {
		return this.pessoaRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Registro nao encontrado: " + id));
	}

	public PessoaEnderecos findAll(long pessoa) {
		PessoaEnderecos pessoaEnderecos = new PessoaEnderecos();
		pessoaEnderecos.setPessoa(this.pessoaRepository.findById(pessoa).orElse(null));
		if (pessoaEnderecos.getPessoa() != null) {
			pessoaEnderecos.setEnderecos(this.enderecoMapper
					.listToListDto(this.enderecoRepository.findByPessoaId(pessoaEnderecos.getPessoa())));
			return pessoaEnderecos;
		}
		return null;
	}

}
