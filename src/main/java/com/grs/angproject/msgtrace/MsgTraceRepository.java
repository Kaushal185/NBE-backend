package com.grs.angproject.msgtrace;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MsgTraceRepository extends JpaRepository<MsgTrace, Long> {

    // List<MsgTrace> findAll(Long id);

    @Query(
		"SELECT m FROM MsgTrace m WHERE m.swiftMsgId = :id ORDER BY m.createdOn DESC"
	)
	List<MsgTrace> findMessageHistory(@Param("id") Long id);

}
