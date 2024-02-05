package com.grs.angproject.msgrelation;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MsgRelationRepository extends JpaRepository<MsgRelation, Long> {

    @Query(value = "SELECT * FROM rmwb.msg_relation WHERE MSG1 = :id", nativeQuery = true)
    Optional<MsgRelation> customQueryByMsg1(@Param("id") Long id);
}
