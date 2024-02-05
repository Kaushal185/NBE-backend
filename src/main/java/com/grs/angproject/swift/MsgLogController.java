package com.grs.angproject.swift;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.domain.Page;
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

   @GetMapping("/pg")
   public Page<MsgLog> getUsers(
           @RequestParam(defaultValue = "0") int page,
           @RequestParam(defaultValue = "100") int pageSize) {
       return msgLogService.getUsers(page, pageSize);
   }
    
    @GetMapping("/all")
    public List<MsgLog> getAllRecords() {
        return msgLogService.getAllRecords();
    }

    @GetMapping("/loadMore")
    public Page<MsgLog> getMoreRecords(@RequestParam int page, @RequestParam(defaultValue = "100") int size) {
        return msgLogService.getMoreRecords(page, size);
    }
    
    @PostMapping("/id")
    public Optional<MsgLog> getSelectedId(@RequestBody MsgLog record) {
    	return msgLogService.getSelectedId(record.getId());
    }

    @GetMapping("/search")
     public ResponseEntity<?> getMethodName(@RequestParam Long id) {
        Optional<MsgLog> swiftModelOptional = msgLogService.getSelectedId(id);

        if (swiftModelOptional.isPresent()) {
            return ResponseEntity.ok(swiftModelOptional.get());
        } else {
            String notFoundMessage = "Record with ID " + id + " not found.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(notFoundMessage); // You can customize the body or provide additional information
        }
    
     }

    //  @GetMapping("/search2")
    //  public ResponseEntity<?> getSearchId(
    //     @RequestParam(defaultValue = "") String messageType,
    //     @RequestParam(defaultValue = "") String status,
    //     @RequestParam(defaultValue = "") Timestamp from,
    //     @RequestParam(defaultValue = "") Timestamp to
    //     ) {
    //         List<MsgLog> swiftModelData = msgLogService.getSearchData(messageType, status, from, to);
    //      return  ResponseEntity.ok(swiftModelData);
    //  }
     
}