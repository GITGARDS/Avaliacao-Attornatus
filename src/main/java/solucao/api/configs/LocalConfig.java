package solucao.api.configs;

import java.time.LocalDateTime;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import solucao.domain.models.Endereco;
import solucao.domain.models.Pessoa;
import solucao.domain.repository.EnderecoRepository;
import solucao.domain.repository.PessoaRepository;

@AllArgsConstructor
@Configuration
public class LocalConfig {

	private final PessoaRepository pessoaRepository;

	private final EnderecoRepository enderecoRepository;

	@Bean
	public void startUp() {

		for (int i = 0; i < 5; i++) {

			Pessoa pessoa = new Pessoa();

			pessoa.setNome("Pessoa" + i);
			pessoa.setDataDeNascimento(LocalDateTime.now().toLocalDate());
			this.pessoaRepository.save(pessoa);
			for (int j = 0; j <= i; j++) {
				Endereco endereco = new Endereco();
				endereco.setLogradouro("endereco" + j);
				endereco.setCidade("cidade" + j);
				endereco.setCep(i + "0000000");
				endereco.setNumero(j);
				endereco.setPessoaId(pessoa);
				endereco.setEnderecoPrincipal(false);
				this.enderecoRepository.save(endereco);
			}
		}

	}

}
