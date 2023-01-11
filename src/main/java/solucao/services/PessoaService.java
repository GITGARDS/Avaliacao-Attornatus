package solucao.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import solucao.models.Pessoa;
import solucao.models.utils.PessoaEnderecos;
import solucao.repositories.PessoaRepository;

@Service
@Transactional
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private PessoaEnderecoShared shared;

	public PessoaEnderecos novo(Pessoa model) {
		Pessoa pessoa = this.pessoaRepository.save(model);
		if (pessoa != null) {
			return this.findAll(pessoa.getId());
		}
		return null;
	}

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
		return enderecos;
	}

	public PessoaEnderecos findById(Integer id) {
		Pessoa findPessoa = this.shared.findById(id);
		if (findPessoa != null) {
			return this.findAll(id);
		}
		return null;

	}

	public PessoaEnderecos findAll(Integer pessoa) {
		return this.shared.findAll(pessoa);
	}

}
