
package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "booking")
public class Booking extends BaseEntity {

	@Column(name = "entry_date")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate	entryDate;

	@Column(name = "exit_date")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate	exitDate;

	@ManyToOne
	@JoinColumn(name = "pet_id")
	private Pet			pet;


	/**
	 * Creates a new instance of Booking empty
	 */
	public Booking() {
	}

	/**
	 * Getter for property entry_date.
	 *
	 * @return Value of property entry_date.
	 */
	public LocalDate getEntryDate() {
		return this.entryDate;
	}

	/**
	 * Setter for property entry_date.
	 *
	 * @param date
	 *            New value of property entry_date.
	 */
	public void setEntryDate(final LocalDate entryDate) {
		this.entryDate = entryDate;
	}

	/**
	 * Getter for property exit_date.
	 *
	 * @return Value of property exit_date.
	 */
	public LocalDate getExitDate() {
		return this.exitDate;
	}

	/**
	 * Setter for property exit_date.
	 *
	 * @param date
	 *            New value of property exit_date.
	 */
	public void setExitDate(final LocalDate exitDate) {
		this.exitDate = exitDate;
	}

	/**
	 * Getter for property pet.
	 *
	 * @return Value of property pet.
	 */
	public Pet getPet() {
		return this.pet;
	}

	/**
	 * Setter for property pet.
	 *
	 * @param pet
	 *            New value of property pet.
	 */
	public void setPet(final Pet pet) {
		this.pet = pet;
	}
}
