package solucao.domain.services;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import solucao.domain.dtos.PessoaEnderecos;
import solucao.domain.models.Endereco;
import solucao.domain.repository.EnderecoRepository;

@AllArgsConstructor
@Service
public class EnderecoService {

	private final EnderecoRepository repository;

	private final PessoaService pessoaService;

	@Transactional
	public PessoaEnderecos novo(long pessoa, Endereco model) {
		PessoaEnderecos findPessoa = this.pessoaService.findById(pessoa);
		if (findPessoa.getPessoa() != null) {
			model.setPessoaId(findPessoa.getPessoa());
			this.repository.save(model);
			return this.findAll(pessoa);
		}
		return findPessoa;
	}

	@Transactional
	public PessoaEnderecos editarEnderecoPrincipal(Long id) {
		Endereco endereco = this.findById(id);
		if (endereco != null) {
			endereco.setEnderecoPrincipal(!endereco.isEnderecoPrincipal());
			return this.findAll(endereco.getPessoaId().getId());
		}
		return null;
	}

	@Transactional
	public Endereco findById(Long id) {
		Endereco resp = this.repository.findById(id).orElse(null);
		if (resp != null) {
			return resp;
		}
		return resp;
	}

	public List<Endereco> findAll() {
		List<Endereco> resp = this.repository.findAll();
		if (resp != null) {
			return resp;
		}
		return resp;
	}

	public PessoaEnderecos findAll(Long pessoa) {
		PessoaEnderecos resp = this.pessoaService.findAll(pessoa);
		if (resp != null) {
			return resp;
		}
		return resp;
	}

}
