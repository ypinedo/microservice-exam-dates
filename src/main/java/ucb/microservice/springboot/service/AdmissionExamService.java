package ucb.microservice.springboot.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import ucb.microservice.springboot.exception.ResourceNotFoundException;
import ucb.microservice.springboot.model.AdmissionExam;
import ucb.microservice.springboot.repository.AdmissionExamRepository;

public class AdmissionExamService {
	@Autowired
	private AdmissionExamRepository admissionExamRepository;
	
	public List<AdmissionExam> getAllAdmissionExams() {
		return admissionExamRepository.findAll();
	}
	
	public List<AdmissionExam> getAdmissionExamByPeriod(String period)
			throws ResourceNotFoundException{
		List<AdmissionExam> admissionExam = admissionExamRepository.findByPeriod(period)
				.orElseThrow(() -> new ResourceNotFoundException("Admission Exams not found for this period :: " + period));
		List<AdmissionExam> response = new ArrayList<AdmissionExam>();
		response = getAdmissionExamRandom(admissionExam);
		return response;
	}
	
	public AdmissionExam getAdmissionExamById(Long admissionExamId)
			throws ResourceNotFoundException {
		AdmissionExam admissionExam = admissionExamRepository.findById(admissionExamId)
				.orElseThrow(() -> new ResourceNotFoundException("Admission Exam not found for this id :: " + admissionExamId));
		return admissionExam;
	}
	
	public AdmissionExam createAdmissionExam(AdmissionExam admissionExam) {
		return admissionExamRepository.save(admissionExam);
	}
	
	public AdmissionExam updateAdmissionExam(Long admissionExamId,
			AdmissionExam admissionExamDetails) throws ResourceNotFoundException {
		AdmissionExam admissionExam = admissionExamRepository.findById(admissionExamId)
				.orElseThrow(() -> new ResourceNotFoundException("Admission Exam not found for this id :: " + admissionExamId));
		admissionExam.setPeriod(admissionExamDetails.getPeriod());
		admissionExam.setSemester(admissionExamDetails.getSemester());
		admissionExam.setFaculty(admissionExamDetails.getFaculty());
		admissionExam.setCareer(admissionExamDetails.getCareer());
		admissionExam.setDateExam(admissionExamDetails.getDateExam());
		admissionExam.setCourse(admissionExamDetails.getCourse());
		return admissionExamRepository.save(admissionExam);
	}
	
	public Map<String, Boolean> deleteAdmissionExam(Long admissionExamId)
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
