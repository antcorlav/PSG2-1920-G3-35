
package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Booking;

public interface BookingRepository {

	void save(Booking booking) throws DataAccessException;

	List<Booking> findByPetId(Integer petId);

}
