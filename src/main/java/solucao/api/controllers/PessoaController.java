package solucao.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import solucao.domain.dtos.PessoaEnderecos;
import solucao.domain.models.Pessoa;
import solucao.domain.services.PessoaService;

@RestController
@RequestMapping(value = "/api/pessoa")
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;

	@PostMapping
	public ResponseEntity<PessoaEnderecos> criarUmaPessoa(@Valid @RequestBody Pessoa body) {
		PessoaEnderecos resp = this.pessoaService.novo(body);
		return ResponseEntity.ok(resp);
	}

	@PutMapping
	public ResponseEntity<PessoaEnderecos> editarUmaPessoa(@Valid @RequestBody Pessoa body) {
		PessoaEnderecos resp = this.pessoaService.editar(body);
		return ResponseEntity.ok(resp);
	}

	@GetMapping
	public ResponseEntity<List<PessoaEnderecos>> listarPessoas() {
		List<PessoaEnderecos> resp = this.pessoaService.findAll();
		if (resp == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.ok(resp);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<PessoaEnderecos> consultarUmaPessoa(@PathVariable Long id) {
		PessoaEnderecos resp = this.pessoaService.findById(id);
		return ResponseEntity.ok(resp);
	}

}
