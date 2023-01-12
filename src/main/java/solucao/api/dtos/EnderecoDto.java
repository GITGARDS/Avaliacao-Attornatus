package solucao.api.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EnderecoDto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@NotBlank(message = "Valor não pode ser nulo ou vazio")
	@Size(min = 8, max = 100, message = "Campo logradouro deve ter entre 9 e 100 caracteres")
	private String logradouro;

	@Column(length = 8)
	@Size(min = 8, max = 8, message = "Campo cep deve ter 8 caracteres")
	private String cep;

	private Integer numero;

	@Size(min = 8, max = 100, message = "Campo cidade deve ter entre 9 e 100 caracteres")
	private String cidade;

	@Column(name = "endereco_principal")
	private boolean enderecoPrincipal;

}
