package ucb.microservice.springboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ucb.microservice.springboot.model.AdmissionExam;

@Repository
public interface AdmissionExamRepository  extends JpaRepository<AdmissionExam, Long>{
	Optional<List<AdmissionExam>> findByPeriod(String period);
}
