package solucao.api.mappers;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import solucao.domain.dtos.EnderecoDto;
import solucao.domain.models.Endereco;

@Component
public class EnderecoMapper {

	public List<EnderecoDto> listToListDto(List<Endereco> enderecos) {
		List<EnderecoDto> lista = enderecos.stream().filter(Objects::nonNull).map(this::enderecoToEnderecoDto)
				.filter(obj -> obj.isEnderecoPrincipal() == true).collect(Collectors.toList());
		if (lista.size() == 0) {
			lista.add(new EnderecoDto(null, null, null, null, null, null, false));
		}
		return lista;
	}

	public EnderecoDto enderecoToEnderecoDto(Endereco endereco) {
		return new EnderecoDto(endereco.getId(), null, endereco.getLogradouro(), endereco.getCep(),
				endereco.getNumero(), endereco.getCidade(), endereco.isEnderecoPrincipal());
	}

	public List<Endereco> listDtoToList(List<EnderecoDto> enderecosDto) {
		return enderecosDto.stream().filter(Objects::nonNull).map(this::enderecoDtoToEndereco)
				.collect(Collectors.toList());
	}

	public Endereco enderecoDtoToEndereco(EnderecoDto enderecoDto) {
		if (enderecoDto == null) {
			return null;
		}
		Endereco endereco = new Endereco();
		endereco.setId(enderecoDto.getId());
		endereco.setLogradouro(enderecoDto.getLogradouro());
		endereco.setCep(enderecoDto.getCep());
		endereco.setNumero(enderecoDto.getNumero());
		endereco.setCidade(enderecoDto.getCidade());
		return endereco;
	}

}
