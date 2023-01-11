package solucao.models;

import org.hibernate.annotations.ForeignKey;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Data
@Entity(name = "endereco")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "pessoa_id", "id" }, name = "uk_endereco_pessoa_id_id"))
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "pessoa_id", referencedColumnName = "id", nullable = false)
	@ForeignKey(name = "fk_endereco_pessoa_id")
	private Pessoa pessoaId;

	@Column(nullable = false)
	private String logradouro;

	@Column(length = 8)
	private String cep;

	private Integer numero;

	private String cidade;

	@Column(name = "endereco_principal")
	private boolean enderecoPrincipal;

}
