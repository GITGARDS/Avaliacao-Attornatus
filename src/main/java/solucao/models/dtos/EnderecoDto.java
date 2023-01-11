package solucao.models.dtos;

public class EnderecoDto {

	private Integer id;

	private String logradouro;

	private String cep;

	private Integer numero;

	private String cidade;

	private boolean enderecoPrincipal;

	public EnderecoDto(Integer id, String logradouro, String cep, Integer numero, String cidade,
			boolean enderecoPrincipal) {
		super();
		this.id = id;
		this.logradouro = logradouro;
		this.cep = cep;
		this.numero = numero;
		this.cidade = cidade;
		this.enderecoPrincipal = enderecoPrincipal;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
	public String toString() {
		return "EnderecoDto [id=" + id + ", logradouro=" + logradouro + ", cep=" + cep + ", numero=" + numero
				+ ", cidade=" + cidade + ", enderecoPrincipal=" + enderecoPrincipal + "]";
	}

}
