package solucao.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import solucao.exceptions.ApplicationNotFoundException;
import solucao.models.EnderecoModel;
import solucao.services.EnderecoService;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/endereco")

public class EnderecoController {

	private final EnderecoService enderecoService;

	@PostMapping(value = "/{id}")
	public ResponseEntity<EnderecoModel> criarUmEnderecoParaPessoa(@PathVariable Long id,
			@Valid @RequestBody EnderecoModel body) throws ApplicationNotFoundException {
		EnderecoModel resp = this.enderecoService.criar(id, body);
		return ResponseEntity.ok(resp);
	}

	@GetMapping
	public ResponseEntity<List<EnderecoModel>> findAll() throws ApplicationNotFoundException {
		List<EnderecoModel> resp = this.enderecoService.findAll();
		return ResponseEntity.ok(resp);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<List<EnderecoModel>> listarEnderecosDaPessoa(@PathVariable Long id)
			throws ApplicationNotFoundException {
		List<EnderecoModel> resp = this.enderecoService.findAll(id);
		return ResponseEntity.ok(resp);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<List<EnderecoModel>> enderecoPrincipalDaPessoa(@PathVariable Long id)
			throws ApplicationNotFoundException {
		List<EnderecoModel> resp = this.enderecoService.editarEnderecoPrincipal(id);
		return ResponseEntity.ok(resp);
	}

}
