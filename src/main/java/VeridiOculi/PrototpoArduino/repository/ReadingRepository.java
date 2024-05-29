package VeridiOculi.PrototpoArduino.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import VeridiOculi.PrototpoArduino.entity.Reading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ReadingRepository  extends JpaRepository<Reading, Long> {
}
