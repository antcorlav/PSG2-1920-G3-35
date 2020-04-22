
package org.springframework.samples.petclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Causa;
import org.springframework.samples.petclinic.repository.springdatajpa.SpringDataCausaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CausaService {

	private SpringDataCausaRepository causaRepo;


	@Autowired
	public CausaService(final SpringDataCausaRepository springCausaRepo) {
		this.causaRepo = springCausaRepo;
	}

	@Transactional
	public int causaCount() {
		return (int) this.causaRepo.count();
	}

	@Transactional
	public void saveCausa(final Causa causa) {
		if (causa == null) {
			throw new NullPointerException();
		}
		this.causaRepo.save(causa);
	}

	@Transactional
	public Iterable<Causa> findAll() {
		return this.causaRepo.findAll();
	}

	@Transactional
	public Causa findCausaById(final int id) {
		Causa causa = this.causaRepo.findById(id);
		return causa;
	}

}
