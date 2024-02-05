package com.grs.angproject.swift;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.grs.angproject.user.Users;

@Service
public class MsgLogServiceImpl implements MsgLogService {

    @Autowired
    MsgLogRepository msgLogRepository;

   @Override
   public Page<MsgLog> getUsers(int page, int pageSize) {
       PageRequest pageRequest = PageRequest.of(page, pageSize);
       return msgLogRepository.findAll(pageRequest);
   }
    
    public List<MsgLog> getAllRecords() {
    	return msgLogRepository.findAll();
    }
     
    @Override
    public Optional<MsgLog> getSelectedId(Long id){
    	return msgLogRepository.findById(id);
    }

    @Override
    public Optional<MsgLog> getMessageById(Long id){
        return msgLogRepository.findMessageById(id);
    }

    @Override
    public Page<MsgLog> getMoreRecords(int page, int size) {
        // Simulate loading more records, adjust as per your actual pagination logic
        // int off = page * size;
        PageRequest pageRequest = PageRequest.of(page, size);
        return msgLogRepository.findAll(pageRequest);
    }

    // @Override
    // public List<MsgLog> getSearchData(String messageType, String status, Timestamp from, Timestamp to) {
    //     // return msgLogRepository.customQ(messageType, status, from, to);
    //     return msgLogRepository.findByMessageTypeAndStatusAndCreatedOnBetween(messageType, status, null, null);
    // }
 
}

