package solucao.domain.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "endereco")
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "pessoa_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "fk_endereco_pessoa_id"))
	private Pessoa pessoaId;

	@Column(nullable = false)
	@NotBlank(message = "Valor não pode ser nulo ou vazio")
	@Size(min = 3, max = 100, message = "Campo logradouro deve ter de 3 a 100 caracteres")
	private String logradouro;

	@Column(length = 8)
	@Size(min = 8, max = 8, message = "Campo cep deve ter 8 caracteres")
	private String cep;

	private Integer numero;

	@Size(min = 3, max = 100, message = "Campo cidade deve ter de 3 a 100 caracteres")
	private String cidade;

	@Column(name = "endereco_principal")
	private boolean enderecoPrincipal;

}
