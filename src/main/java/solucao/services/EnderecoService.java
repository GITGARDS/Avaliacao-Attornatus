package solucao.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import solucao.models.Endereco;
import solucao.models.Pessoa;
import solucao.models.utils.PessoaEnderecos;
import solucao.repositories.EnderecoRepository;
import solucao.services.shared.PessoaEnderecoShared;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository repository;

	@Autowired
	private PessoaEnderecoShared shared;

	@Transactional	
	public PessoaEnderecos novo(long pessoa, Endereco model) {
		Pessoa findPessoa = this.shared.findById(pessoa);
		if (findPessoa != null) {
			model.setPessoaId(findPessoa);
			this.repository.save(model);
			return this.findAll(pessoa);
		}
		return null;
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
		return this.repository.findById(id).orElse(null);
	}

	public List<Endereco> findAll() {
		return this.repository.findAll();
	}

	public PessoaEnderecos findAll(Long pessoa) {
		return this.shared.findAll(pessoa);
	}

}
