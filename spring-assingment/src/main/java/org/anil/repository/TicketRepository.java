package org.anil.repository;

import java.util.List;

import org.anil.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>  {
	
	@Query("select u from Ticket u where u.user.id = :id")
	List<Ticket> findTicketByUser(@Param("id") Long id);
	
	
	@Query("select u from Ticket u where u.user.id = :id and u.id = :ticketId")
	List<Ticket> findTicketByUserAndTicketId(@Param("id") Long id,@Param("ticketId") Long ticketId);
	
	
}
