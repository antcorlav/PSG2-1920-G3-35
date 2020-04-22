
package org.springframework.samples.petclinic.repository.springdatajpa;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Causa;
import org.springframework.samples.petclinic.repository.CausaRepository;
import org.springframework.samples.petclinic.repository.OwnerRepository;

/**
 * Spring Data JPA specialization of the {@link OwnerRepository} interface
 *
 * @author Michael Isvy
 * @since 15.1.2013
 */

public interface SpringDataCausaRepository extends CausaRepository, Repository<Causa, Integer> {

	//SELECT * FROM Causa c WHERE c.id IN(SELECT causa_id FROM Donacion a WHERE a.persona_username=:username)
	//	SELECT * FROM Causa causa WHERE causa.id IN(SELECT donacion.causa_id FROM Donacion donacion WHERE donacion.persona_username LIKE :username%)

	@Override
	@Query("SELECT causa FROM Causa causa WHERE causa.id =:id")
	Causa findById(@Param("id") int id);

	@Override
	@Query("SELECT causa FROM Causa causa WHERE causa.valido = false")
	Collection<Causa> findCausaByValidoFalse();

	@Override
	@Query("SELECT causa FROM Causa causa WHERE causa.valido = true")
	Collection<Causa> findCausaByValidoTrue();

}
