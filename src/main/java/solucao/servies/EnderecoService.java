package solucao.servies;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import solucao.models.Endereco;
import solucao.models.Pessoa;
import solucao.models.utils.PessoaEnderecos;
import solucao.repositories.EnderecoRepository;

@Service
@Transactional
public class EnderecoService {

	@Autowired
	private EnderecoRepository repository;

	@Autowired
	private PessoaService pessoaService;

	public PessoaEnderecos novo(Integer pessoa, Endereco model) {
		Pessoa findPessoa = this.pessoaService.findById(pessoa);
		if (findPessoa != null) {		
			model.setPessoaId(findPessoa);
			this.repository.save(model);			
			return this.findAll(pessoa);
		}
		return null;
	}

	public PessoaEnderecos editar_endereco_principal(Integer id) {
		Endereco endereco = this.findById(id);	
		if (endereco != null) {
			endereco.setEnderecoPrincipal(!endereco.isEnderecoPrincipal());			
			return this.findAll(endereco.getPessoaId().getId());
		}
		return null;
	}

	public Endereco findById(Integer id) {
		return this.repository.findById(id).orElse(null);
	}

	public List<Endereco> findAll() {
		return this.repository.findAll();
	}

	public PessoaEnderecos findAll(Integer pessoa) {
		PessoaEnderecos pessoaEnderecos = new PessoaEnderecos();
		pessoaEnderecos.setPessoa(this.pessoaService.findById(pessoa));
		if (pessoaEnderecos.getPessoa() != null) {
			pessoaEnderecos.setEnderecos(this.repository.findByPessoaId(pessoaEnderecos.getPessoa()));
			return pessoaEnderecos;
		}
		return null;
	}

}
