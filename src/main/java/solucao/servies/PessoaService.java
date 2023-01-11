package solucao.servies;

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
	private PessoaRepository repository;

	public Pessoa novo(Pessoa model) {
		return this.repository.save(model);
	}

	public Pessoa editar(Pessoa model) {
		Pessoa ret = this.findById(model.getId());
		if (ret != null) {
			return this.repository.save(model);
		}
		return null;
	}

	public Pessoa findById(Integer id) {
		return this.repository.findById(id).orElse(null);
	}

	public List<Pessoa> findAll() {
		return this.repository.findAll();
	}

}
