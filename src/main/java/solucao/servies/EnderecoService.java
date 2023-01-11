package solucao.servies;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import solucao.models.Endereco;
import solucao.models.Pessoa;
import solucao.repositories.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository repository;

	@Autowired
	private PessoaService pessoaService;

	public Endereco novo(Integer pessoa, Endereco model) {
		Pessoa findPessoa = this.pessoaService.findById(pessoa);
		if (findPessoa != null) {
			model.setPessoaId(findPessoa);
			return this.repository.save(model);
		}
		return null;
	}

	public Endereco editar_endereco_principal(Integer id) {
		Endereco ret = this.findById(id);
		if (ret != null) {
			ret.setEnderecoPrincipal(true);
			return this.repository.save(ret);
		}
		return null;
	}

	public Endereco findById(Integer id) {
		return this.repository.findById(id).orElse(null);
	}

	public List<Endereco> findAll() {
		return this.repository.findAll();
	}

	public List<Endereco> findAll(Integer pessoa) {
		Pessoa findPessoa = this.pessoaService.findById(pessoa);
		if (findPessoa != null) {
			return this.repository.findAllByPessoa(findPessoa);
		}

		return null;
	}

}
