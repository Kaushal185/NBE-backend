package com.grs.angproject.msgrelation;

import java.util.List;
import java.util.Optional;

public interface MsgRelationService {
    List<MsgRelation> getAllMsgRelations();
    Optional<MsgRelation> getMsgRelationById(Long id);

    Optional<MsgRelation> getMsg1RelationObject(Long id);

    Optional<MsgRelation> getMsg2RelationObject(Long id);
}
