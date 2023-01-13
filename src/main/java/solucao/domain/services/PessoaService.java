package solucao.domain.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import solucao.domain.dtos.PessoaEnderecos;
import solucao.domain.models.Pessoa;
import solucao.domain.repository.PessoaRepository;
import solucao.domain.services.shared.PessoaEnderecoShared;

@AllArgsConstructor
@Service
public class PessoaService {

	private final PessoaRepository pessoaRepository;

	private final PessoaEnderecoShared shared;

	@Transactional
	public PessoaEnderecos novo(Pessoa model) {
		PessoaEnderecos pessoaEnderecos = new PessoaEnderecos();
		pessoaEnderecos.setPessoa(this.pessoaRepository.save(model));
		if (pessoaEnderecos.getPessoa() != null) {
			return this.findAll(pessoaEnderecos.getPessoa().getId());
		}
		return null;
	}

	@Transactional
	public PessoaEnderecos editar(Pessoa model) {
		PessoaEnderecos findPessoa = this.findById(model.getId());
		if (findPessoa != null) {
			this.pessoaRepository.save(model);
			return this.findAll(model.getId());
		}
		return null;
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
		return null;

	}

	@Transactional
	public PessoaEnderecos findById(Long id) {
		Pessoa findPessoa = this.shared.findById(id);
		if (findPessoa != null) {
			return this.findAll(id);
		}
		return null;

	}

	public PessoaEnderecos findAll(Long pessoa) {
		PessoaEnderecos pessoaEnderecos = this.shared.findAll(pessoa);
		if (pessoaEnderecos != null) {
			return pessoaEnderecos;
		}
		return null;

	}

}
