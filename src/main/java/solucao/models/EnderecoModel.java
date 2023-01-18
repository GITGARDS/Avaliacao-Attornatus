package solucao.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "endereco")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "logradouro",
		"pessoa_id" }, name = "uk_endereco_logradouro_pessoa_id"))
public class EnderecoModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "pessoa_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "fk_endereco_pessoa_id"))
	@JsonIgnore	
	private PessoaModel pessoaId;

	@Column(nullable = false)
	@Size(min = 3, max = 100, message = "Campo deve ter de 3 a 100 caracteres!")
	@NotBlank(message = "Campo de preenchimento obrigatorio!")
	private String logradouro;

	@Size(min = 8, max = 8, message = "Campo deve ter 8 caracteres")
	private String cep;

	private Integer numero;

	@Size(min = 3, max = 100, message = "Campo deve ter de 3 a 100 caracteres!")
	private String cidade;

	@Column(name = "endereco_principal")
	private boolean enderecoPrincipal;

}
