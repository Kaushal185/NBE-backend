package com.grs.angproject.swift;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface MsgLogRepository extends JpaRepository<MsgLog, Long> {

	// List<MsgLog> findAll();

	// @Query(
	// 	"SELECT m FROM MsgLog m " +
	// 	"WHERE (:messageType IS NULL) " +
	// 	"ORDER BY m.createdOn DESC"
	// )
	Page<MsgLog> findByMessageType(String messageType, Pageable pageable);

	Optional<MsgLog> findById(Long id);

	Optional<MsgLog> findMessageById(Long id);

	@Query(
	 "SELECT m FROM MsgLog m " +
	 "WHERE (UPPER(m.messageType) = UPPER(:messageType) OR :messageType IS NULL) " +
	 "AND (UPPER(m.identifier) = UPPER(:identifier) OR :identifier IS NULL) " +
	 "AND (UPPER(m.status) = UPPER(:status) OR :status IS NULL) " +
	 "AND (m.createdOn >= to_timestamp(:from,'YYYY-MM-DD') OR :from IS NULL) " +
	 "AND (m.createdOn < to_timestamp(:to,'YYYY-MM-DD') OR :to IS NULL) "+ 
	 "ORDER BY m.createdOn DESC")
	Page<MsgLog> findByMessageTypeAndIdentifierAndStatusAndCreatedOnBetween(
		@Param("messageType") String messageType,
		@Param("identifier") String identifier, 
		@Param("status") String status, 
		@Param("from") String from, 
		@Param("to") String to,
		Pageable pageable
		);


	@Query(
			"SELECT m FROM MsgLogExport m " +
					"WHERE (UPPER(m.messageType) = UPPER(:messageType) OR :messageType IS NULL) " +
					"AND (UPPER(m.identifier) = UPPER(:identifier) OR :identifier IS NULL) " +
					"AND (UPPER(m.status) = UPPER(:status) OR :status IS NULL) " +
					"AND (m.createdOn >= to_timestamp(:from,'YYYY-MM-DD') OR :from IS NULL) " +
					"AND (m.createdOn < to_timestamp(:to,'YYYY-MM-DD') OR :to IS NULL) " +
					"ORDER BY m.createdOn DESC"
					// Add the limit clause for MySQL
					+ " LIMIT 1000"
	)
	List<MsgLogExport> getFilteredForListToExcel(
			@Param("messageType") String messageType,
			@Param("identifier") String identifier,
			@Param("status") String status,
			@Param("from") String from,
			@Param("to") String to
	);


	// @Query("SELECT m FROM MsgLog m " +
    //         "WHERE (:messageType IS NULL OR m.messageType = :messageType) " +
    //         "AND (:status IS NULL OR m.status = :status) " +
    //         "AND (:from IS NULL OR m.createdOn >= :from) " +
    //         "AND (:to IS NULL OR m.createdOn < :to) " +
    //         "ORDER BY m.createdOn DESC")
    // List<MsgLog> findByMessageTypeAndStatusAndCreatedOnBetween(
    //         @Param("messageType") String messageType,
    //         @Param("status") String status,
    //         @Param("from") Timestamp from,
    //         @Param("to") Timestamp to
    // );


    
}
