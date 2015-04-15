package eu.fugiczek.maturita.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import eu.fugiczek.maturita.domain.Inquiry;

public interface InquiryRepository extends JpaRepository<Inquiry, Integer>{
	
	@Modifying
	@Query("update Inquiry i set i.processed = ?1 where i.id = ?2")
	void setProcessed(boolean processed, int id);
	
}
