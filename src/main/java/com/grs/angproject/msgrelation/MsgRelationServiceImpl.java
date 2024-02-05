package com.grs.angproject.msgrelation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MsgRelationServiceImpl implements MsgRelationService {

    @Autowired
    private MsgRelationRepository msgRelationRepository;

    @Override
    public List<MsgRelation> getAllMsgRelations() {
        return msgRelationRepository.findAll();
    }

    @Override
    public Optional<MsgRelation> getMsgRelationById(Long id) {
        return msgRelationRepository.findById(id);
    }

    @Override
    public Optional<MsgRelation> getMsgRelationObject(Long id) {
        return msgRelationRepository.customQueryByMsg1(id);
    }

}
