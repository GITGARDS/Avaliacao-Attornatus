package solucao.models;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
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
@Entity(name = "pessoa")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "nome" }, name = "uk_pessoa_nome"))

@Filter(name="enderecoPrincipalFiltro", condition="enderecos.enderecoPrincipal = true")

public class PessoaModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@Size(min = 3, max = 100, message = "Campo deve ter de 3 a 100 caracteres!")
	@NotBlank(message = "Campo de preenchimento obrigatorio!")
	private String nome;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "data_de_nascimento", nullable = false)
	private LocalDate dataDeNascimento;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "pessoa_enderecos", joinColumns = { @JoinColumn(name = "pessoa_id") }, inverseJoinColumns = {
			@JoinColumn(name = "endereco_id") })
	@Where(clause = "endereco_principal = true")
	private List<EnderecoModel> enderecos;

}
