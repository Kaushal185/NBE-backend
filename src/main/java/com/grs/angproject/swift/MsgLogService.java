package com.grs.angproject.swift;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface MsgLogService {

    Page<MsgLog> getUsersSort(int page, int pageSize, Sort sort, String messageType);

    List<MsgLog> getAllRecords();

    Optional<MsgLog> getSelectedId(Long id);

    Optional<MsgLog> getMessageById(Long id);

    Page<MsgLog> getMoreRecords(int page, int size);

    Page<MsgLog> getSearchData(String messageType, String identifier, String status, String from, String to, int page,
            int pageSize, Sort sort);

    List<MsgLogExport> getFilteredForListToExcel(String messageType, String identifier, String status, String from,
            String to);
}