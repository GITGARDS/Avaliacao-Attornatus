package solucao.services;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import solucao.exceptions.ApplicationNotFoundException;
import solucao.models.EnderecoModel;
import solucao.models.PessoaEnderecosModel;
import solucao.models.PessoaModel;
import solucao.repositories.PessoaEnderecosRepository;

@AllArgsConstructor
@Service
public class PessoaEnderecoService {

	private final PessoaEnderecosRepository pessoaEnderecosRepository;

	@Transactional
	public void salvar(PessoaModel pessoaModel, EnderecoModel enderecoModel) throws ApplicationNotFoundException {

		PessoaEnderecosModel model = new PessoaEnderecosModel();
		model.setPessoa_id(pessoaModel);
		model.setEndereco_id(enderecoModel);
		this.pessoaEnderecosRepository.save(model);
	}

}
