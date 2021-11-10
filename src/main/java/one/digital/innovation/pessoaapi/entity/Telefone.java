package one.digital.innovation.pessoaapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;
import one.digital.innovation.pessoaapi.enums.TipoTelefone;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Telefone {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoTelefone tipo;
	
	
	@Column(nullable = false)
	private String numero;

}
