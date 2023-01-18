package solucao.services;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import solucao.exceptions.ApplicationNotFoundException;
import solucao.models.EnderecoModel;
import solucao.models.PessoaModel;
import solucao.repositories.EnderecoRepository;

@AllArgsConstructor
@Service
public class EnderecoService {

	private final PessoaService pessoaService;

	private final EnderecoRepository enderecoRepository;

	private final PessoaEnderecoService pessoaEnderecoService;

	@Transactional
	public EnderecoModel criar(Long pessoaId, EnderecoModel model) throws ApplicationNotFoundException {
		PessoaModel findPessoa = this.pessoaService.findById(pessoaId);
		if (findPessoa != null) {
			model.setPessoaId(findPessoa);
			EnderecoModel modelVeri = this.enderecoRepository.save(model);
			if (modelVeri != null) {
				this.pessoaEnderecoService.salvar(findPessoa, modelVeri);
				return modelVeri;
			}
		}

		throw new ApplicationNotFoundException(
				"A operação novo foi cancelada, pessoa: " + findPessoa + ", nao encontrado!");
	}

	@Transactional
	public EnderecoModel editar(Long pessoaId, EnderecoModel model) throws ApplicationNotFoundException {
		PessoaModel findPessoa = this.pessoaService.findById(pessoaId);
		if (findPessoa != null) {
			EnderecoModel findEndereco = this.findById(model.getId());
			if (findEndereco != null) {
				EnderecoModel modelVeri = this.enderecoRepository.save(model);
				if (modelVeri != null) {
					this.pessoaEnderecoService.salvar(findPessoa, modelVeri);
					return modelVeri;
				}

			}

		}

		throw new ApplicationNotFoundException(
				"A operação novo foi cancelada, pessoa: " + findPessoa + ", nao encontrado!");
	}

	@Transactional
	public List<EnderecoModel> editarEnderecoPrincipal(Long id) throws ApplicationNotFoundException {
		EnderecoModel findEndereco = this.findById(id);
		if (findEndereco != null) {
			findEndereco.setEnderecoPrincipal(!findEndereco.isEnderecoPrincipal());
			this.enderecoRepository.save(findEndereco);
			return this.findAll(findEndereco.getPessoaId().getId());
		}
		throw new ApplicationNotFoundException(
				"A operação editar foi cancelada, registro: " + id + ", nao encontrado!");
	}

	@Transactional
	public EnderecoModel excluir(Long pessoaId, EnderecoModel model) throws ApplicationNotFoundException {
		PessoaModel findPessoa = this.pessoaService.findById(pessoaId);
		if (findPessoa != null) {
			EnderecoModel findEndereco = this.findById(model.getId());
			if (findEndereco != null) {
				this.enderecoRepository.delete(model);
				return findEndereco;
			}
		}
		return null;
	}

	public List<EnderecoModel> findAll() throws ApplicationNotFoundException {
		List<EnderecoModel> modelVeri = this.enderecoRepository.findAll();
		if (modelVeri.size() > 0) {
			return modelVeri;
		}
		throw new ApplicationNotFoundException("Nenhum registro nao encontrado!");
	}

	public List<EnderecoModel> findAll(Long id) throws ApplicationNotFoundException {
		PessoaModel findPessoa = this.pessoaService.findById(id);
		if (findPessoa != null) {
			List<EnderecoModel> modelVeri = this.enderecoRepository.findByPessoaId(findPessoa);
			if (modelVeri.size() > 0) {
				return modelVeri;
			}
		}
		throw new ApplicationNotFoundException("Nenum registro encontrado para a pessoa: " + findPessoa + "informada!");
	}

	public EnderecoModel findById(Long id) throws ApplicationNotFoundException {
		EnderecoModel resp = this.enderecoRepository.findById(id).orElse(null);
		if (resp != null) {
			return resp;
		}
		throw new ApplicationNotFoundException("Registro: " + id + ", nao encontrado!");
	}

}
