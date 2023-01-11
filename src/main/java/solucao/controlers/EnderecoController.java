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

import solucao.models.Endereco;
import solucao.models.utils.PessoaEnderecos;
import solucao.services.EnderecoService;

@RestController
@RequestMapping(value = "/api/endereco")
public class EnderecoController {

	@Autowired
	private EnderecoService enderecoService;

	@PostMapping(value = "/criar-um-endereco-para-pessoa/{id}")
	private ResponseEntity<PessoaEnderecos> novo(@PathVariable Integer id, @RequestBody Endereco body) {
		PessoaEnderecos resp = this.enderecoService.novo(id, body);
		if (resp == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.ok(resp);
	}

	@GetMapping(value = "/findAll")
	private ResponseEntity<List<Endereco>> findAll() {
		List<Endereco> resp = this.enderecoService.findAll();
		if (resp == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.ok(resp);
	}

	@GetMapping(value = "/listar-endereços-da-pessoa/{id}")
	private ResponseEntity<PessoaEnderecos> findAll(@PathVariable Integer id) {
		PessoaEnderecos resp = this.enderecoService.findAll(id);
		if (resp == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.ok(resp);
	}

	@PutMapping(value = "/endereço-principal-da-pessoa/{id}")
	private ResponseEntity<PessoaEnderecos> editar_endereco_principal(@PathVariable Integer id) {
		PessoaEnderecos resp = this.enderecoService.editar_endereco_principal(id);
		if (resp == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.ok(resp);
	}

}
