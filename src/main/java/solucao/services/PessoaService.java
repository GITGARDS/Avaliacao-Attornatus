package solucao.services;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import solucao.exceptions.ApplicationNotFoundException;
import solucao.models.PessoaModel;
import solucao.repositories.PessoaRepository;

@AllArgsConstructor
@Service
public class PessoaService {

	private final PessoaRepository pessoaRepository;

	@Transactional
	public PessoaModel criar(PessoaModel model) throws ApplicationNotFoundException {

		PessoaModel modelVeri = this.pessoaRepository.save(model);
		if (modelVeri != null) {
			return modelVeri;
		}
		throw new ApplicationNotFoundException("A operação incluir foi cancelada!");
	}

	@Transactional
	public PessoaModel editar(PessoaModel model) throws ApplicationNotFoundException {
		PessoaModel find = this.findById(model.getId());
		if (find != null) {
			PessoaModel modelVeri = this.pessoaRepository.save(model);
			if (modelVeri != null) {
				return modelVeri;
			}
		}
		throw new ApplicationNotFoundException(
				"A operação editar foi cancelada, registro: " + model.getId() + ", nao encontrado!");
	}

	@Transactional
	public PessoaModel excluir(PessoaModel model) throws ApplicationNotFoundException {
		PessoaModel find = this.findById(model.getId());
		if (find != null) {
			this.pessoaRepository.delete(model);
			return find;
		}
		throw new ApplicationNotFoundException(
				"A operação excluir foi cancelada, registro: " + model.getId() + ", nao encontrado!");
	}

	public List<PessoaModel> findAll() throws ApplicationNotFoundException {
		List<PessoaModel> modelVeri = this.pessoaRepository.findAll();
		if (modelVeri.size() > 0) {
			return modelVeri;
		}
		throw new ApplicationNotFoundException("Nenhum registro encontrado!");

	}

	public PessoaModel findById(Long id) throws ApplicationNotFoundException {
		PessoaModel find = this.pessoaRepository.findById(id).orElse(null);
		if (find != null) {
			return find;
		}
		throw new ApplicationNotFoundException("Registro: " + id + ", nao encontrado!");
	}
}
