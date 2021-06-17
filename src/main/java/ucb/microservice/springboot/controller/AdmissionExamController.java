package ucb.microservice.springboot.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ucb.microservice.springboot.exception.ResourceNotFoundException;
import ucb.microservice.springboot.model.AdmissionExam;
import ucb.microservice.springboot.repository.AdmissionExamRepository;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class AdmissionExamController {
	@Autowired
	private AdmissionExamRepository admissionExamRepository;
	
	@GetMapping("/admissionExams")
	public List<AdmissionExam> getAllAdmissionExams() {
		return admissionExamRepository.findAll();
	}
	
	@GetMapping("/admissionExams/period/{period}")
	public ResponseEntity<List<AdmissionExam>> getAdmissionExamByPeriod(@PathVariable(value = "period") String period)
			throws ResourceNotFoundException {
		List<AdmissionExam> admissionExam = admissionExamRepository.findByPeriod(period)
				.orElseThrow(() -> new ResourceNotFoundException("Admission Exams not found for this period :: " + period));
		List<AdmissionExam> response = new ArrayList<AdmissionExam>();
		response = getAdmissionExamRandom(admissionExam);
		return ResponseEntity.ok().body(response);
	}
	
	@GetMapping("/admissionExams/{id}")
	public ResponseEntity<AdmissionExam> getAdmissionExamById(@PathVariable(value = "id") Long admissionExamId)
			throws ResourceNotFoundException {
		AdmissionExam admissionExam = admissionExamRepository.findById(admissionExamId)
				.orElseThrow(() -> new ResourceNotFoundException("Admission Exam not found for this id :: " + admissionExamId));
		return ResponseEntity.ok().body(admissionExam);
	}
	
	@PostMapping("/admissionExams")
	public AdmissionExam createAdmissionExam(@Valid @RequestBody AdmissionExam admissionExam) {
		return admissionExamRepository.save(admissionExam);
	}
	
	@PutMapping("/admissionExam/{id}")
	public ResponseEntity<AdmissionExam> updateAdmissionExam(@PathVariable(value = "id") Long admissionExamId,
			@Valid @RequestBody AdmissionExam admissionExamDetails) throws ResourceNotFoundException {
		AdmissionExam admissionExam = admissionExamRepository.findById(admissionExamId)
				.orElseThrow(() -> new ResourceNotFoundException("Admission Exam not found for this id :: " + admissionExamId));
		
		admissionExam.setPeriod(admissionExamDetails.getPeriod());
		admissionExam.setSemester(admissionExamDetails.getSemester());
		admissionExam.setFaculty(admissionExamDetails.getFaculty());
		admissionExam.setCareer(admissionExamDetails.getCareer());
		admissionExam.setDateExam(admissionExamDetails.getDateExam());
		admissionExam.setCourse(admissionExamDetails.getCourse());
		final AdmissionExam updateAdmissionExam = admissionExamRepository.save(admissionExam);
		return ResponseEntity.ok(updateAdmissionExam);
	}
	
	@DeleteMapping("/admissionExam/{id}")
	public Map<String, Boolean> deleteAdmissionExam(@PathVariable(value = "id") Long admissionExamId)
			throws ResourceNotFoundException {
		AdmissionExam admissionExam = admissionExamRepository.findById(admissionExamId)
				.orElseThrow(() -> new ResourceNotFoundException("Admission Exam not found for this id :: " + admissionExamId));

		admissionExamRepository.delete(admissionExam);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
	public List<AdmissionExam> getAdmissionExamRandom(List<AdmissionExam> admissionExamList){
		List<AdmissionExam> response = new ArrayList<AdmissionExam>();
		int index = 0;
		Random random = new Random();
		int upperBound = 10;
		int numberRamdom = random.nextInt(upperBound);
		for (AdmissionExam admissionExam : admissionExamList) {
			if(index <= numberRamdom) {
				response.add(admissionExam);
			}
			index += 1;
		}
		return response;
	}

}