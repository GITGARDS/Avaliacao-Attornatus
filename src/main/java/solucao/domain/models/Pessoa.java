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
import lombok.Data;

@Data
@Entity(name = "pessoa")
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	@NotBlank(message = "Valor não pode ser nulo ou vazio")	
	@Size(min = 8, max = 100, message = "Campo nome deve ter entre 9 e 100 caracteres")	
	private String nome;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "data_de_nascimento", nullable = false)
	
	private LocalDate dataDeNascimento;


}
