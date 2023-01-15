package solucao.api.mappers;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import solucao.domain.dtos.EnderecoRequest;
import solucao.domain.models.Endereco;

@Component
public class EnderecoMapper {

	public List<EnderecoRequest> listToListDto(List<Endereco> enderecos) {
		List<EnderecoRequest> lista = enderecos.stream().filter(Objects::nonNull).map(this::enderecoToEnderecoDto)
				.filter(obj -> obj.isEnderecoPrincipal() == true).collect(Collectors.toList());
		if (lista.size() == 0) {
			lista.add(new EnderecoRequest(null, null, null, null, null, false));
		}
		return lista;
	}

	public EnderecoRequest enderecoToEnderecoDto(Endereco endereco) {
		EnderecoRequest request = new EnderecoRequest(
				endereco.getPessoaId(), 
				endereco.getLogradouro(), 
				endereco.getCep(),
				endereco.getNumero(), 
				endereco.getCidade(), 
				endereco.isEnderecoPrincipal()
				);

		return request;
	}

	public List<Endereco> listDtoToList(List<EnderecoRequest> enderecoRequest) {
		return enderecoRequest.stream().filter(Objects::nonNull).map(this::enderecoDtoToEndereco)
				.collect(Collectors.toList());
	}

	public Endereco enderecoDtoToEndereco(EnderecoRequest enderecoRequest) {
		if (enderecoRequest == null) {
			return null;
		}
		Endereco endereco = new Endereco();
		endereco.setId(1l);
		endereco.setLogradouro(enderecoRequest.getLogradouro());
		endereco.setCep(enderecoRequest.getCep());
		endereco.setNumero(enderecoRequest.getNumero());
		endereco.setCidade(enderecoRequest.getCidade());
		return endereco;
	}

}
