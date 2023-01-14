package solucao.domain.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import solucao.api.mappers.EnderecoMapper;
import solucao.domain.dtos.PessoaEnderecos;
import solucao.domain.models.Pessoa;
import solucao.domain.repository.EnderecoRepository;
import solucao.domain.repository.PessoaRepository;
import solucao.domain.services.exceptions.DataIntegratyViolationException;
import solucao.domain.services.exceptions.ObjectNotFoundException;

@AllArgsConstructor
@Service
public class PessoaService {

	private final PessoaRepository pessoaRepository;
	private final EnderecoRepository enderecoRepository;
	private final EnderecoMapper enderecoMapper;

	@Transactional
	public PessoaEnderecos novo(Pessoa model) {
		findByName(model);

		PessoaEnderecos pessoaEnderecos = new PessoaEnderecos();
		pessoaEnderecos.setPessoa(this.pessoaRepository.save(model));

		if (pessoaEnderecos.getPessoa() != null) {
			return this.findAll(pessoaEnderecos.getPessoa().getId());
		}
		return pessoaEnderecos;
	}

	@Transactional
	public PessoaEnderecos editar(Pessoa model) {
		findByName(model);
		PessoaEnderecos findPessoa = this.findById(model.getId());
		if (findPessoa != null) {
			this.pessoaRepository.save(model);
			return this.findAll(model.getId());
		}
		return findPessoa;
	}

	public List<PessoaEnderecos> findAll() {
		List<Pessoa> listaPessoas = this.pessoaRepository.findAll();
		List<PessoaEnderecos> enderecos = new ArrayList<>();
		for (Pessoa pessoa : listaPessoas) {
			PessoaEnderecos endereco = this.findAll(pessoa.getId());
			enderecos.add(endereco);
		}
		if (enderecos.size() > 0) {
			return enderecos;
		}
		return enderecos;

	}

	@Transactional
	public PessoaEnderecos findById(Long id) {
		Pessoa findPessoa = this.pessoaRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Registro nao encontrado: " + id));
		if (findPessoa != null) {
			return this.findAll(id);
		}
		return null;
	}

	public PessoaEnderecos findAll(Long pessoa) {
		PessoaEnderecos pessoaEnderecos = new PessoaEnderecos();
		pessoaEnderecos.setPessoa(this.pessoaRepository.findById(pessoa).orElse(null));
		if (pessoaEnderecos.getPessoa() != null) {
			pessoaEnderecos.setEnderecos(this.enderecoMapper
					.listToListDto(this.enderecoRepository.findByPessoaId(pessoaEnderecos.getPessoa())));
			return pessoaEnderecos;
		}
		return pessoaEnderecos;
	}

	private void findByName(Pessoa obj) {
		Optional<Pessoa> pessoa = this.pessoaRepository.findByNome(obj.getNome());
		if (pessoa.isPresent() && !pessoa.get().getId().equals(obj.getId())) {
			throw new DataIntegratyViolationException("Pessoa ja cadastrada");
		}

	}

}
