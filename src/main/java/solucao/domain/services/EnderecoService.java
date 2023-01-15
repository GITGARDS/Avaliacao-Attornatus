package solucao.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import solucao.api.exceptions.ApplicationNotFoundException;
import solucao.domain.dtos.PessoaEnderecos;
import solucao.domain.models.Endereco;
import solucao.domain.repository.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository repository;

	@Autowired
	private PessoaService pessoaService;

	@Transactional
	public PessoaEnderecos novo(long pessoa, Endereco model) throws ApplicationNotFoundException {
		PessoaEnderecos findPessoa = this.pessoaService.findById(pessoa);
		if (findPessoa.getPessoa() != null) {
			model.setPessoaId(findPessoa.getPessoa());
			this.repository.save(model);
			return this.findAll(pessoa);
		}
		throw new ApplicationNotFoundException(
				"A operação novo foi cancelada, pessoa: " + pessoa + ", nao encontrado!");
	}

	@Transactional
	public PessoaEnderecos editarEnderecoPrincipal(Long id) throws ApplicationNotFoundException {
		Endereco endereco = this.findById(id);
		if (endereco != null) {
			endereco.setEnderecoPrincipal(!endereco.isEnderecoPrincipal());
			return this.findAll(endereco.getPessoaId().getId());
		}
		throw new ApplicationNotFoundException(
				"A operação editar foi cancelada, registro: " + id + ", nao encontrado!");
	}

	@Transactional
	public Endereco findById(Long id) throws ApplicationNotFoundException {
		Endereco resp = this.repository.findById(id).orElse(null);
		if (resp != null) {
			return resp;
		}
		throw new ApplicationNotFoundException("Registro: " + id + ", nao encontrado!");
	}

	public List<Endereco> findAll() throws ApplicationNotFoundException {
		List<Endereco> resp = this.repository.findAll();
		if (resp != null) {
			return resp;
		}
		throw new ApplicationNotFoundException("Nenhum registro nao encontrado!");
	}

	public PessoaEnderecos findAll(Long pessoa) throws ApplicationNotFoundException {
		PessoaEnderecos resp = this.pessoaService.findAll(pessoa);
		if (resp != null) {
			return resp;
		}
		throw new ApplicationNotFoundException("Nenum registro encontrado para a pessoa: " + pessoa + "informada!");
	}

}
