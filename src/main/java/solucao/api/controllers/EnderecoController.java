package solucao.api.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import solucao.api.dtos.PessoaEnderecos;
import solucao.domain.models.Endereco;
import solucao.domain.services.EnderecoService;
import solucao.exceptions.ApplicationNotFoundException;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/endereco")
public class EnderecoController {

	private final EnderecoService enderecoService;

	@PostMapping(value = "/{id}")
	public ResponseEntity<PessoaEnderecos> criarUmEnderecoParaPessoa(@PathVariable Long id,
			@Valid @RequestBody Endereco body) throws ApplicationNotFoundException {
		PessoaEnderecos resp = this.enderecoService.novo(id, body);
		return ResponseEntity.ok(resp);
	}

	@GetMapping
	public ResponseEntity<List<Endereco>> findAll() throws ApplicationNotFoundException {
		List<Endereco> resp = this.enderecoService.findAll();
		if (resp == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.ok(resp);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<PessoaEnderecos> listarEnderecosDaPessoa(@PathVariable Long id) throws ApplicationNotFoundException {
		PessoaEnderecos resp = this.enderecoService.findAll(id);
		if (resp == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.ok(resp);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<PessoaEnderecos> enderecoPrincipalDaPessoa(@PathVariable Long id)
			throws ApplicationNotFoundException {
		PessoaEnderecos resp = this.enderecoService.editarEnderecoPrincipal(id);
		return ResponseEntity.ok(resp);
	}

}
