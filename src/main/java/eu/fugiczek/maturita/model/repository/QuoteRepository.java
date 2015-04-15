package eu.fugiczek.maturita.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import eu.fugiczek.maturita.domain.Quote;

public interface QuoteRepository extends JpaRepository<Quote, Integer>{
	
	@Query("select quo from Quote quo order by RAND()")
	public List<Quote> findRandomQuote();
	
}
