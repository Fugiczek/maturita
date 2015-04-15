package eu.fugiczek.maturita.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import eu.fugiczek.maturita.domain.Quote;
import eu.fugiczek.maturita.model.repository.QuoteRepository;

@Service
public class QuoteService {
	
	@Autowired
	private QuoteRepository quoteRepository;

	public Page<Quote> findAll(int page) {
		return quoteRepository.findAll(new PageRequest(page, 10));
	}
	
	public void delete(int id) {
		quoteRepository.delete(id);
	}

	public void save(Quote quote) {
		quoteRepository.save(quote);
	}

	public Quote findOne(int id) {
		return quoteRepository.findOne(id);
	}

	public void update(Quote quote) {
		quoteRepository.save(quote);
	}

	public Quote getRandomQuote() {
		return quoteRepository.findRandomQuote().get(0);
	}
	
}
