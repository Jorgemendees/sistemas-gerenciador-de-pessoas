package one.digital.innovation.pessoaapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import one.digital.innovation.pessoaapi.entity.Pessoa;
import one.digital.innovation.pessoaapi.exception.PessoaNãoEncotradaException;
import one.digital.innovation.pessoaapi.repository.PessoaRepository;

@Service
public class CadastroPessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	public CadastroPessoaService(PessoaRepository pessoaRepository) {
		super();
		this.pessoaRepository = pessoaRepository;
	}

	public MensagemResponse adiconar(Pessoa pessoa) {
		Pessoa pessoaAdicionar = pessoaRepository.save(pessoa);
		return MensagemResponse.builder().mensagem("Pessoa adicionada com id " + pessoaAdicionar.getId()).build();

	}
	
	public void excluir(Long pessoaId) {
		try {
			pessoaRepository.deleteById(pessoaId);

		} catch (EmptyResultDataAccessException e) {
			throw new PessoaNãoEncotradaException(
					String.format("Não existe um cadastro de pessoa com id %d", pessoaId));
		}
	}
}
