package solucao.models.utils;

import java.util.List;

import solucao.models.Pessoa;
import solucao.models.dtos.EnderecoDto;

public class PessoaEnderecos {

	private Pessoa pessoa;
	private List<EnderecoDto> enderecos;

	public PessoaEnderecos() {
		super();
		this.pessoa = null;
		this.enderecos = null;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<EnderecoDto> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<EnderecoDto> enderecos) {
		this.enderecos = enderecos;
	}

	@Override
	public String toString() {
		return "PessoaEnderecos [pessoa=" + pessoa + ", enderecos=" + enderecos + "]";
	}

}
