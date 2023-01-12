package solucao.domain.services;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import solucao.api.dtos.PessoaEnderecos;
import solucao.domain.models.Endereco;
import solucao.domain.models.Pessoa;
import solucao.domain.repository.EnderecoRepository;
import solucao.domain.services.shared.PessoaEnderecoShared;
import solucao.exceptions.ApplicationNotFoundException;

@Service
@RequiredArgsConstructor
public class EnderecoService {

	private final EnderecoRepository repository;

	private final PessoaEnderecoShared shared;

	@Transactional
	public PessoaEnderecos novo(long pessoa, Endereco model) throws ApplicationNotFoundException {
		Pessoa findPessoa = this.shared.findById(pessoa);
		if (findPessoa != null) {
			model.setPessoaId(findPessoa);
			this.repository.save(model);
			return this.findAll(pessoa);
		}
		throw new ApplicationNotFoundException("Erro ao tentar adicionar Registro!");
	}

	@Transactional
	public PessoaEnderecos editarEnderecoPrincipal(Long id) throws ApplicationNotFoundException {
		Endereco endereco = this.findById(id);
		if (endereco != null) {
			endereco.setEnderecoPrincipal(!endereco.isEnderecoPrincipal());
			return this.findAll(endereco.getPessoaId().getId());
		}
		throw new ApplicationNotFoundException("Erro ao tentar editar Registro!");
	}

	@Transactional
	public Endereco findById(Long id) throws ApplicationNotFoundException {
		Endereco resp = this.repository.findById(id).orElse(null);
		if (resp != null) {
			return resp;
		}
		throw new ApplicationNotFoundException("Nehum registro encontrado: " + id);
	}

	public List<Endereco> findAll() throws ApplicationNotFoundException {
		List<Endereco> resp = this.repository.findAll();
		if (resp != null) {
			return resp;
		}
		throw new ApplicationNotFoundException("Nehum registro encontrado");

	}

	public PessoaEnderecos findAll(Long pessoa) throws ApplicationNotFoundException {
		PessoaEnderecos resp = this.shared.findAll(pessoa);
		if (resp != null) {
			return resp;
		}
		throw new ApplicationNotFoundException("Nehum registro encontrado: " + pessoa);

	}

}
