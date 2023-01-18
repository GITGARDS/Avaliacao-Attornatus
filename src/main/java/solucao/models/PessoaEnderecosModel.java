package solucao.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "pessoa_enderecos")
public class PessoaEnderecosModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "pessoa_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "fk_pessoa_enderecos_pessoa_id"))
	private PessoaModel pessoa_id;

	@ManyToOne
	@JoinColumn(name = "endereco_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "fk_pessoa_enderecos_endereco_id"))

	private EnderecoModel endereco_id;

}
