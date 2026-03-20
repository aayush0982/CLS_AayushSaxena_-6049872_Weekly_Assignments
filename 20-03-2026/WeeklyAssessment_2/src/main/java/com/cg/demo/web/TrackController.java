package com.cg.demo.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.demo.entity.Abes_Track;
import com.cg.demo.repo.TrackRepository;

@RestController
public class TrackController {
	@Autowired
	private TrackRepository trackRepository;
	
	@PostMapping("/tracks")
	public ResponseEntity<String> addTracks(@RequestBody Abes_Track track) {
		trackRepository.save(track);
		return ResponseEntity.ok("Track added successfully");
	}
	
	@GetMapping("/tracks/all")
	public ResponseEntity<List<Abes_Track>> getTracks() {
		List<Abes_Track> tracks = trackRepository.findAll();
		return ResponseEntity.ok(tracks);
	}
	
	@GetMapping("/tracks/byTitle")
	public ResponseEntity<List<Abes_Track>> getTracksByTitle(@RequestParam String title) {
		List<Abes_Track> tracks = trackRepository.findByTrack(title);
		return ResponseEntity.ok(tracks);
	}
	
	@GetMapping("/tracks/byId")
	public ResponseEntity<Object> getTrack(@RequestParam Long id) {
		Optional<Abes_Track> track = trackRepository.findById(id);
		if (track.isPresent()) {
			return ResponseEntity.ok(track.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
}
