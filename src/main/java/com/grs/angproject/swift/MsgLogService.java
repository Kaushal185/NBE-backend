package com.grs.angproject.swift;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

public interface MsgLogService {

   Page<MsgLog> getUsers(int page, int pageSize);
    
    List<MsgLog> getAllRecords();
    
    Optional<MsgLog> getSelectedId(Long id);

    Optional<MsgLog> getMessageById(Long id);

    Page<MsgLog> getMoreRecords(int page, int size);

    // List<MsgLog> getSearchData(String messageType, String status, Timestamp from, Timestamp to);
}