package solucao.domain.dtos;

import java.util.List;

import lombok.Data;
import solucao.domain.models.Pessoa;

@Data
public class PessoaEnderecos {

	private Pessoa pessoa;
	private List<EnderecoDto> enderecos;

	public PessoaEnderecos() {
		super();
		this.pessoa = null;
		this.enderecos = null;
	}

}
