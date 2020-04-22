
package org.springframework.samples.petclinic.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "causa")
public class Causa extends BaseEntity {

	@NotEmpty
	@Column(name = "description")
	private String			description;

	@NotEmpty
	@Column(name = "ong")
	private String			ong;

	@NotNull
	@Column(name = "objetivo")
	private Integer			objetivo;

	@NotNull
	@Column(name = "dinero_recaudado")
	private Integer			dineroRecaudado;

	@Column(name = "valido")
	private boolean			valido;

	//	@ManyToOne
	//	@JoinColumn(name = "vet_id")
	//	private Vet				vet;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "causa")
	private List<Donacion>	donaciones;


	public String getDescription() {

		return this.description;
	}

	public void setDescription(final String description) {

		this.description = description;
	}

	public String getOng() {

		return this.ong;
	}

	public void setOng(final String ong) {

		this.ong = ong;
	}

	public Integer getObjetivo() {

		return this.objetivo;
	}

	public void setObjetivo(final Integer objetivo) {

		this.objetivo = objetivo;
	}

	public Integer getDineroRecaudado() {

		return this.dineroRecaudado;
	}

	public void setDineroRecaudado(final Integer dineroRecaudado) {

		this.dineroRecaudado = dineroRecaudado;
	}

	public boolean isValido() {

		return this.valido;
	}

	public void setValido(final boolean valido) {

		this.valido = valido;
	}

	public List<Donacion> getDonaciones() {

		return this.donaciones;
	}

	public void setDonaciones(final List<Donacion> donaciones) {

		this.donaciones = donaciones;
	}

	@Override
	public String toString() {
		return "Causa [description=" + this.description + ", ong=" + this.ong + ", objetivo=" + this.objetivo + ", dineroRecaudado=" + this.dineroRecaudado + ", valido=" + this.valido + "]";
	}

}
