package solucao.domain.models;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "pessoa")
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Valor não pode ser nulo ou vazio")
	@Size(min = 3, max = 100, message = "Campo nome deve ter de 3 a 100 caracteres")
	@Column(unique = true, nullable = false)
	private String nome;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "data_de_nascimento", nullable = false)
	private LocalDate dataDeNascimento;
}
