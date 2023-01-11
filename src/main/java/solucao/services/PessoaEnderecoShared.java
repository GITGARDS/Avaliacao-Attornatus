package solucao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import solucao.models.Pessoa;
import solucao.models.utils.PessoaEnderecos;
import solucao.repositories.EnderecoRepository;
import solucao.repositories.PessoaRepository;

@Service
@Transactional
public class PessoaEnderecoShared {

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private PessoaRepository pessoaRepository;

	public Pessoa findById(Integer id) {
		return this.pessoaRepository.findById(id).orElse(null);
	}

	public PessoaEnderecos findAll(Integer pessoa) {
		PessoaEnderecos pessoaEnderecos = new PessoaEnderecos();
		pessoaEnderecos.setPessoa(this.pessoaRepository.findById(pessoa).orElse(null));
		if (pessoaEnderecos.getPessoa() != null) {
			pessoaEnderecos.setEnderecos(this.enderecoRepository.findByPessoaId(pessoaEnderecos.getPessoa()));
			return pessoaEnderecos;
		}
		return null;
	}

}
