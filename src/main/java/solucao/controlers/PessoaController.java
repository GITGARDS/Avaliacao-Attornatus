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
import solucao.models.Pessoa;
import solucao.models.utils.PessoaEnderecos;
import solucao.services.PessoaService;

@RestController
@RequestMapping(value = "/api/v1/pessoa")
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;

	@PostMapping(value = "/criarUmaPessoa")
	public ResponseEntity<PessoaEnderecos> criarUmaPessoa(@Valid @RequestBody Pessoa body) {
		PessoaEnderecos resp = this.pessoaService.novo(body);
		if (resp == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.ok(resp);
	}

	@PutMapping(value = "/editarUmaPessoa")
	public ResponseEntity<PessoaEnderecos> editarUmaPessoa(@Valid @RequestBody Pessoa body) {
		PessoaEnderecos resp = this.pessoaService.editar(body);
		if (resp == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.ok(resp);
	}

	@GetMapping(value = "/consultarUmaPessoa/{id}")
	public ResponseEntity<PessoaEnderecos> consultarUmaPessoa(@PathVariable Long id) {
		PessoaEnderecos resp = this.pessoaService.findById(id);
		if (resp == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.ok(resp);
	}

	@GetMapping(value = "/listarPessoas")
	public ResponseEntity<List<PessoaEnderecos>> listarPessoas() {
		List<PessoaEnderecos> resp = this.pessoaService.findAll();
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
