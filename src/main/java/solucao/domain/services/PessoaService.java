package solucao.domain.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import solucao.api.exceptions.ApplicationNotFoundException;
import solucao.api.mappers.EnderecoMapper;
import solucao.domain.dtos.PessoaEnderecos;
import solucao.domain.models.Pessoa;
import solucao.domain.repository.EnderecoRepository;
import solucao.domain.repository.PessoaRepository;
import solucao.domain.services.exceptions.DataIntegratyViolationException;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private EnderecoMapper enderecoMapper;

	@Transactional
	public PessoaEnderecos novo(Pessoa model) throws ApplicationNotFoundException {
		findByName(model);

		PessoaEnderecos pessoaEnderecos = new PessoaEnderecos();
		pessoaEnderecos.setPessoa(this.pessoaRepository.save(model));

		if (pessoaEnderecos.getPessoa() != null) {
			return this.findAll(pessoaEnderecos.getPessoa().getId());
		}
		throw new ApplicationNotFoundException("A operação incluir foi cancelada!");
	}

	@Transactional
	public PessoaEnderecos editar(Pessoa model) throws ApplicationNotFoundException {
		findByName(model);
		PessoaEnderecos findPessoa = this.findById(model.getId());
		if (findPessoa != null) {
			this.pessoaRepository.save(model);
			return this.findAll(model.getId());
		}
		throw new ApplicationNotFoundException(
				"A operação editar foi cancelada, registro: " + model.getId() + ", nao encontrado!");
	}

	public List<PessoaEnderecos> findAll() throws ApplicationNotFoundException {
		List<Pessoa> listaPessoas = this.pessoaRepository.findAll();
		List<PessoaEnderecos> enderecos = new ArrayList<>();
		for (Pessoa pessoa : listaPessoas) {
			PessoaEnderecos endereco = this.findAll(pessoa.getId());
			enderecos.add(endereco);
		}
		if (enderecos.size() > 0) {
			return enderecos;
		}
		throw new ApplicationNotFoundException("Nenhum registro encontrado!");

	}

	@Transactional
	public PessoaEnderecos findById(Long id) throws ApplicationNotFoundException {
		Pessoa findPessoa = this.pessoaRepository.findById(id).orElse(null);
		if (findPessoa != null) {
			return this.findAll(id);
		}
		throw new ApplicationNotFoundException(
				"Registro: " + id + ", nao encontrado!");
	}

	public PessoaEnderecos findAll(Long pessoa) throws ApplicationNotFoundException {
		PessoaEnderecos pessoaEnderecos = new PessoaEnderecos();
		pessoaEnderecos.setPessoa(this.pessoaRepository.findById(pessoa).orElse(null));
		if (pessoaEnderecos.getPessoa() != null) {
			pessoaEnderecos.setEnderecos(this.enderecoMapper
					.listToListDto(this.enderecoRepository.findByPessoaId(pessoaEnderecos.getPessoa())));
			return pessoaEnderecos;
		}
		throw new ApplicationNotFoundException(
				"A operação foi cancelada, pessoa: " + pessoa + ", nao encontrado!");
	}

	private void findByName(Pessoa obj) {
		Optional<Pessoa> pessoa = this.pessoaRepository.findByNome(obj.getNome());
		if (pessoa.isPresent() && !pessoa.get().getId().equals(obj.getId())) {
			throw new DataIntegratyViolationException("Pessoa ja cadastrada");
		}

	}

}
