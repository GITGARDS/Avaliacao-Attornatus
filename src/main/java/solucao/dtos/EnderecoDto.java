package solucao.dtos;

import java.util.Objects;

public class EnderecoDto {

	private Long id;

	private String logradouro;

	private String cep;

	private Integer numero;

	private String cidade;

	private boolean enderecoPrincipal;

	public EnderecoDto(Long id, String logradouro, String cep, Integer numero, String cidade,
			boolean enderecoPrincipal) {
		super();
		this.id = id;
		this.logradouro = logradouro;
		this.cep = cep;
		this.numero = numero;
		this.cidade = cidade;
		this.enderecoPrincipal = enderecoPrincipal;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public boolean isEnderecoPrincipal() {
		return enderecoPrincipal;
	}

	public void setEnderecoPrincipal(boolean enderecoPrincipal) {
		this.enderecoPrincipal = enderecoPrincipal;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EnderecoDto other = (EnderecoDto) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "EnderecoDto [id=" + id + ", logradouro=" + logradouro + ", cep=" + cep + ", numero=" + numero
				+ ", cidade=" + cidade + ", enderecoPrincipal=" + enderecoPrincipal + "]";
	}

}
