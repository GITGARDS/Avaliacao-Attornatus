package solucao.controlers;

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

import solucao.models.Pessoa;
import solucao.models.utils.PessoaEnderecos;
import solucao.services.PessoaService;

@RestController
@RequestMapping(value = "/api/pessoa")
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;

	@PostMapping(value = "/criar-uma-pessoa")
	private ResponseEntity<PessoaEnderecos> novo(@RequestBody Pessoa body) {
		PessoaEnderecos resp = this.pessoaService.novo(body);
		if (resp == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.ok(resp);
	}

	@PutMapping(value = "/editar-uma-pessoa")
	private ResponseEntity<PessoaEnderecos> editar(@RequestBody Pessoa body) {
		PessoaEnderecos resp = this.pessoaService.editar(body);
		if (resp == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.ok(resp);
	}

	@GetMapping(value = "/consultar-uma-pessoa/{id}")
	private ResponseEntity<PessoaEnderecos> findByIdr(@PathVariable Integer id) {
		PessoaEnderecos resp = this.pessoaService.findById(id);
		if (resp == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.ok(resp);
	}

	@GetMapping(value = "/listar-pessoas")
	private ResponseEntity<List<PessoaEnderecos>> findAll() {
		List<PessoaEnderecos> resp = this.pessoaService.findAll();
		if (resp == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.ok(resp);
	}

}
