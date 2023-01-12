package solucao.controlers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import solucao.models.Endereco;
import solucao.models.utils.PessoaEnderecos;
import solucao.services.EnderecoService;

@RestController
@RequestMapping(value = "/api/v1/endereco")
public class EnderecoController {

	@Autowired
	private EnderecoService enderecoService;

	@PostMapping(value = "/criarUmEnderecoParaPessoa/{id}")
	public ResponseEntity<PessoaEnderecos> criarUmEnderecoParaPessoa(@Valid @PathVariable Long id,
			@RequestBody Endereco body) {
		PessoaEnderecos resp = this.enderecoService.novo(id, body);
		if (resp == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.ok(resp);
	}

	@GetMapping(value = "/findAll")
	public ResponseEntity<List<Endereco>> findAll() {
		List<Endereco> resp = this.enderecoService.findAll();
		if (resp == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.ok(resp);
	}

	@GetMapping(value = "/listarEnderecosDaPessoa/{id}")
	public ResponseEntity<PessoaEnderecos> listarEnderecosDaPessoa(@PathVariable Long id) {
		PessoaEnderecos resp = this.enderecoService.findAll(id);
		if (resp == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.ok(resp);
	}

	@PutMapping(value = "/endereçoPrincipalDaPessoa/{id}")
	public ResponseEntity<PessoaEnderecos> endereçoPrincipalDaPessoa(@PathVariable Long id) {
		PessoaEnderecos resp = this.enderecoService.editarEnderecoPrincipal(id);
		if (resp == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.ok(resp);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationException(MethodArgumentNotValidException exception) {
		Map<String, String> errors = new HashMap<>();
		exception.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;

	}

}
