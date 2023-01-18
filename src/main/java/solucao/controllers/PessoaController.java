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

import lombok.AllArgsConstructor;
import solucao.exceptions.ApplicationNotFoundException;
import solucao.models.PessoaModel;
import solucao.services.PessoaService;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/pessoa")

public class PessoaController {

	private final PessoaService pessoaService;

	@PostMapping
	private ResponseEntity<PessoaModel> criar(@RequestBody PessoaModel model) throws ApplicationNotFoundException {
		PessoaModel resp = this.pessoaService.criar(model);
		return ResponseEntity.ok(resp);
	}

	@PutMapping
	private ResponseEntity<PessoaModel> editar(@RequestBody PessoaModel model) throws ApplicationNotFoundException {
		PessoaModel resp = this.pessoaService.criar(model);
		return ResponseEntity.ok(resp);
	}

	@GetMapping
	private ResponseEntity<List<PessoaModel>> findAll() throws ApplicationNotFoundException {
		List<PessoaModel> resp = this.pessoaService.findAll();
		return ResponseEntity.ok(resp);
	}

	@GetMapping(value = "/{id}")
	private ResponseEntity<PessoaModel> findById(@PathVariable Long id) throws ApplicationNotFoundException {
		PessoaModel resp = this.pessoaService.findById(id);
		return ResponseEntity.ok(resp);
	}
}
