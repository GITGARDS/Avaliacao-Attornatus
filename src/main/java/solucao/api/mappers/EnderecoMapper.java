package solucao.api.mappers;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import solucao.api.dtos.EnderecoDto;
import solucao.domain.models.Endereco;

@Component
@AllArgsConstructor
public class EnderecoMapper {

	public List<EnderecoDto> listToListDto(List<Endereco> enderecos) {
		return enderecos.stream().filter(Objects::nonNull).map(this::enderecoToEnderecoDto)
				.collect(Collectors.toList());
	}

	public EnderecoDto enderecoToEnderecoDto(Endereco endereco) {
		return new EnderecoDto(endereco.getId(), endereco.getLogradouro(), endereco.getCep(), endereco.getNumero(),
				endereco.getCidade(), endereco.isEnderecoPrincipal());
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
