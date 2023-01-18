package solucao.configs.bd;

import java.time.LocalDateTime;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import solucao.models.EnderecoModel;
import solucao.models.PessoaEnderecosModel;
import solucao.models.PessoaModel;
import solucao.repositories.EnderecoRepository;
import solucao.repositories.PessoaEnderecosRepository;
import solucao.repositories.PessoaRepository;

@AllArgsConstructor
@Configuration
public class CreateRegistros {

	private final PessoaRepository pessoaRepository;
	private final EnderecoRepository enderecoRepository;
	private final PessoaEnderecosRepository pessoaEnderecosRepository;

	@Bean
	void setUp() {
		create();
	}

	void create() {

		if (this.pessoaRepository.findAll().size() == 0) {
			for (int i = 1; i <= 5; i++) {
				PessoaModel pessoa = new PessoaModel();
				pessoa.setNome("pessoa" + i);
				pessoa.setDataDeNascimento(LocalDateTime.now().toLocalDate());
				PessoaModel pessoaSalva = this.pessoaRepository.save(pessoa);
				for (int j = 0; j <= i; j++) {
					EnderecoModel endereco = new EnderecoModel();
					endereco.setLogradouro("endereco" + j);
					endereco.setCidade("cidade" + j);
					endereco.setCep(i + "0000000");
					endereco.setNumero(j);
					endereco.setPessoaId(pessoa);
					endereco.setEnderecoPrincipal(false);
					if (j == 0)
						endereco.setEnderecoPrincipal(true);
					EnderecoModel enderecoSalvo = this.enderecoRepository.save(endereco);
					PessoaEnderecosModel pessoaEndereco = new PessoaEnderecosModel();
					pessoaEndereco.setPessoa_id(pessoaSalva);
					pessoaEndereco.setEndereco_id(enderecoSalvo);
					this.pessoaEnderecosRepository.save(pessoaEndereco);
				}
			}

		}
	}

}
