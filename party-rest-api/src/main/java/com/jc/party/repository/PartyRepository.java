package com.jc.party.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jc.party.entity.Party;

@Repository
public interface PartyRepository extends CrudRepository<Party, Long> {

	Collection<Party> findAll();
}