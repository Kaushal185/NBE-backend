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

   	// Page<SwiftModel> findAll(Pageable pageable);
	
	Optional<MsgLog> findById(Long id);

	Optional<MsgLog> findMessageById(Long id);

	@Query(value =
	 "SELECT * FROM RMWB.MSG_LOG WHERE (MESSAGE_TYPE = :messageType OR :messageType IS NULL) AND (STATUS = :status OR :status IS NULL) AND (CREATED_ON >= to_timestamp(:from,'DD-MM-YY') OR :from IS NULL) AND (CREATED_ON < to_timestamp(:to,'DD-MM-YY') OR :to IS NULL) ORDER BY CREATED_ON DESC",
	  nativeQuery = true)
	List<MsgLog> customQ(@Param("messageType") String messageType, @Param("status") String status, @Param("from") String from, @Param("to") String end);

// 	@Query("SELECT m FROM MsgLog m " +
//             "WHERE (:messageType IS NULL OR m.messageType = :messageType) " +
//             "AND (:status IS NULL OR m.status = :status) " +
//             "AND (:from IS NULL OR m.createdOn >= :from) " +
//             "AND (:to IS NULL OR m.createdOn < :to) " +
//             "ORDER BY m.createdOn DESC")
//     List<MsgLog> findByMessageTypeAndStatusAndCreatedOnBetween(
//             @Param("messageType") String messageType,
//             @Param("status") String status,
//             @Param("from") Timestamp from,
//             @Param("to") LocalDateTime to
//     );


    
}
