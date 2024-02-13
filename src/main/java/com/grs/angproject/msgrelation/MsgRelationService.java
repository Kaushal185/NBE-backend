package com.grs.angproject.msgrelation;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MsgRelationService {
    List<MsgRelation> getAllMsgRelations();
    Optional<MsgRelation> getMsgRelationById(Long id);

    Optional<MsgRelation> getMsg1RelationObject(Long id);
    // Add other service methods as needed

    Optional<MsgRelation> getMsg2RelationObject(Long id);
}
