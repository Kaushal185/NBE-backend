package com.grs.angproject.swift;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grs.angproject.user.Users;

@RestController
@RequestMapping("/api/swift")
@CrossOrigin(origins = "http://localhost:5000")
public class MsgLogController {

    // private final SwiftService swiftService;

    // @Autowired
    // public SwiftController(SwiftService swiftService) {
    //     this.swiftService = swiftService;
    // }

    @Autowired
    private MsgLogService msgLogService;

    // get paginated data
   @GetMapping("/initialPage")
   public Page<MsgLog> getUsers(
           @RequestParam(defaultValue = "0") int page,
           @RequestParam(defaultValue = "100") int pageSize,
           @RequestParam(defaultValue = "MX") String messageType) {

        Sort sort = Sort.by(Sort.Direction.DESC, "createdOn");
       return msgLogService.getUsersSort(page, pageSize, sort, messageType);
   }
    
   // to test api in postman
    @GetMapping("/all")
    public List<MsgLog> getAllRecords() {
        return msgLogService.getAllRecords();
    }

    // load more records button call - replace with Pagination
    @GetMapping("/loadMore")
    public Page<MsgLog> getMoreRecords(@RequestParam int page, @RequestParam(defaultValue = "100") int size) {
        return msgLogService.getMoreRecords(page, size);
    }
    
    // search record by id - not in use
    @PostMapping("/id")
    public Optional<MsgLog> getSelectedId(@RequestBody MsgLog record) {
    	return msgLogService.getSelectedId(record.getId());
    }

    // @GetMapping("/search")
    //  public ResponseEntity<?> getMethodName(@RequestParam Long id) {
    //     Optional<MsgLog> msgLogOptional = msgLogService.getSelectedId(id);
    //     if (msgLogOptional.isPresent()) {
    //         return ResponseEntity.ok(msgLogOptional.get());
    //     } else {
    //         String notFoundMessage = "Record with ID " + id + " not found.";
    //         return ResponseEntity.status(HttpStatus.NOT_FOUND)
    //                 .body(notFoundMessage); // You can customize the body or provide additional information
    //     }
    //  }

     @GetMapping("/search")
     public ResponseEntity<?> getSearchId(
        @RequestParam(defaultValue = "MX") String messageType,
        @RequestParam(defaultValue = "") String identifier,
        @RequestParam(defaultValue = "") String status,
        @RequestParam(defaultValue = "") String from,
        @RequestParam(defaultValue = "") String to,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "15") int pageSize
        ) {
            Sort sort = Sort.by(Sort.Direction.DESC, "createdOn");
            Page<MsgLog> swiftModelData = msgLogService.getSearchData(messageType, identifier, status, from, to, page, pageSize, sort);
            if (!swiftModelData.isEmpty()){
         return  ResponseEntity.ok(swiftModelData);
            } else {
                String notFoundMessage = "No Records found.";
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundMessage);
            }
     }
     
}