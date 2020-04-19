
package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "donacion")
public class Donacion extends BaseEntity {

	@Column(name = "cantidad")
	@Min(value = 0)
	@NotNull
	private Integer		cantidad;

	@ManyToOne
	@JoinColumn(name = "causa_id")
	private Causa		causa;

	@ManyToOne
	@JoinColumn(name = "owner_id")
	private Owner		owner;

	@Column(name = "fecha")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate	fecha;

}
