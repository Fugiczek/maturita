package eu.fugiczek.maturita.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eu.fugiczek.maturita.domain.Inquiry;
import eu.fugiczek.maturita.model.repository.InquiryRepository;

@Service
public class InquiryService {

	@Autowired
	private InquiryRepository inquiryRepository;
	
	public Page<Inquiry> findAll(int page) {
		return inquiryRepository.findAll(new PageRequest(page, 10, Direction.DESC, "publishedDate"));
	}
	
	public void save(Inquiry inquiry) {
		inquiry.setProcessed(false);
		inquiryRepository.save(inquiry);
	}
	
	@Transactional
	public void setProcessed(boolean processed, int id) {
		inquiryRepository.setProcessed(processed, id);
	}

	public Inquiry findOne(int id) {
		return inquiryRepository.findOne(id);
	}
}
