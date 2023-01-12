package solucao.domain.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import solucao.api.dtos.PessoaEnderecos;
import solucao.domain.models.Pessoa;
import solucao.domain.repository.PessoaRepository;
import solucao.domain.services.shared.PessoaEnderecoShared;
import solucao.exceptions.ApplicationNotFoundException;

@Service
@RequiredArgsConstructor
public class PessoaService {

	private final PessoaRepository pessoaRepository;

	private final PessoaEnderecoShared shared;

	@Transactional
	public PessoaEnderecos novo(Pessoa model) throws ApplicationNotFoundException {
		PessoaEnderecos pessoaEnderecos = new PessoaEnderecos();
		pessoaEnderecos.setPessoa(this.pessoaRepository.save(model));
		if (pessoaEnderecos.getPessoa() != null) {
			return this.findAll(pessoaEnderecos.getPessoa().getId());
		}
		throw new ApplicationNotFoundException("Erro ao tentar adicionar Registro!");
	}

	@Transactional
	public PessoaEnderecos editar(Pessoa model) throws ApplicationNotFoundException {
		PessoaEnderecos findPessoa = this.findById(model.getId());
		if (findPessoa != null) {
			this.pessoaRepository.save(model);
			return this.findAll(model.getId());
		}
		throw new ApplicationNotFoundException("Erro ao tentar editar Registro!");
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
		throw new ApplicationNotFoundException("Nehum registro encontrado");

	}

	@Transactional
	public PessoaEnderecos findById(Long id) throws ApplicationNotFoundException {
		Pessoa findPessoa = this.shared.findById(id);
		if (findPessoa != null) {
			return this.findAll(id);
		}
		throw new ApplicationNotFoundException("Erro ao tentar localizar Registro: " + id);

	}

	public PessoaEnderecos findAll(Long pessoa) throws ApplicationNotFoundException {
		PessoaEnderecos pessoaEnderecos = this.shared.findAll(pessoa);
		if (pessoaEnderecos != null) {
			return pessoaEnderecos;
		}
		throw new ApplicationNotFoundException("Erro ao tentar localizar Registro: " + pessoa);

	}

}