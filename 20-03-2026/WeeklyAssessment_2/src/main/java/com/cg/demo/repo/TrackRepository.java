package com.cg.demo.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.demo.entity.Abes_Track;

public interface TrackRepository extends JpaRepository<Abes_Track,Integer> {

	List<Abes_Track> findByTrack(String title);

	Optional<Abes_Track> findById(Long id);

}
