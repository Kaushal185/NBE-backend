package com.grs.angproject.msgtrace;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MsgTraceServiceImpl implements MsgTraceService {

    @Autowired
    MsgTraceRepository msgTraceRepository;

    @Override
    public List<MsgTrace> getMessageHistory(Long id) {
        return msgTraceRepository.findMessageHistory(id);
    }

}
