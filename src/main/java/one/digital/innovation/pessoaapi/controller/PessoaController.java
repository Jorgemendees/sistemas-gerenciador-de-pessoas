package one.digital.innovation.pessoaapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import one.digital.innovation.pessoaapi.entity.Pessoa;
import one.digital.innovation.pessoaapi.exception.PessoaNãoEncotradaException;
import one.digital.innovation.pessoaapi.repository.PessoaRepository;
import one.digital.innovation.pessoaapi.service.CadastroPessoaService;
import one.digital.innovation.pessoaapi.service.MensagemResponse;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private CadastroPessoaService cadastroPessoa;

	@GetMapping
	public List<Pessoa> listar() {
		return pessoaRepository.findAll();
	}

	@GetMapping("/{pessoaId}")
	public ResponseEntity<Pessoa> buscar(@PathVariable Long pessoaId) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(pessoaId);

		if (pessoa.isPresent()) {
			return ResponseEntity.ok(pessoa.get());

		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public MensagemResponse adiconar(@RequestBody Pessoa pessoa) {
		return cadastroPessoa.adiconar(pessoa);
	}

	@PutMapping("/{pessoaId}")
	public ResponseEntity<?> atualizar(@PathVariable Long pessoaId, @RequestBody Pessoa pessoa) {
		try {
			Pessoa pessoaAtual = pessoaRepository.findById(pessoaId).orElse(null);

			if (pessoaAtual != null) {
				BeanUtils.copyProperties(pessoa, pessoaAtual, "id");

				pessoaAtual = pessoaRepository.save(pessoaAtual);
				return ResponseEntity.ok(pessoaAtual);
			}

			return ResponseEntity.notFound().build();

		} catch (PessoaNãoEncotradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("/{pessoaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> remover(@PathVariable Long pessoaId) {
		try {
		cadastroPessoa.excluir(pessoaId);
		return ResponseEntity.noContent().build();
		
	} catch (PessoaNãoEncotradaException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	}
	}
	
}
