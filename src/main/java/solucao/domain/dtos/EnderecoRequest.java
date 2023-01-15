package solucao.domain.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import solucao.domain.models.Pessoa;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoRequest {

	@JsonIgnore
	private Pessoa pessoaId;

	private String logradouro;

	private String cep;

	private Integer numero;

	private String cidade;

	private boolean enderecoPrincipal;

}
