package com.grs.angproject.swift;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/swift")
@CrossOrigin
public class MsgLogController {

    // private final SwiftService swiftService;

    // @Autowired
    // public SwiftController(SwiftService swiftService) {
    // this.swiftService = swiftService;
    // }

    @Autowired
    private MsgLogService msgLogService;


    // Handle OPTIONS requests for /api/swift/initialPage endpoint
    // @RequestMapping(value = "/initialPage", method = RequestMethod.OPTIONS)
    // @ResponseStatus(HttpStatus.NO_CONTENT)
    // public void handleOptions(HttpServletResponse response) {
    //     // Optionally, you can customize the response for OPTIONS requests
    //     // For example, set CORS headers
    //     response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
    //     response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
    //     response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
    //     response.setHeader("Access-Control-Max-Age", "3600");
    // }



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

    // search using unique reference number
    @GetMapping("/ref")
    public ResponseEntity<?> getSingleId(@RequestParam String reference) {
        Optional<MsgLog> record = msgLogService.getSingleId(reference);
        if (record.isPresent()) {
            return ResponseEntity.ok(record.get());
        } else {
            String notFoundMessage = "Record with Reference " + reference + " not found.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(notFoundMessage);
        }
        // return msgLogService.getSingleId(id);
    }

    // @GetMapping("/search")
    // public ResponseEntity<?> getMethodName(@RequestParam Long id) {
    // Optional<MsgLog> msgLogOptional = msgLogService.getSelectedId(id);
    // if (msgLogOptional.isPresent()) {
    // return ResponseEntity.ok(msgLogOptional.get());
    // } else {
    // String notFoundMessage = "Record with ID " + id + " not found.";
    // return ResponseEntity.status(HttpStatus.NOT_FOUND)
    // .body(notFoundMessage); // You can customize the body or provide additional
    // information
    // }
    // }

    // filtered search
    @GetMapping("/search")
    public ResponseEntity<?> getSearchId(
            @RequestParam(defaultValue = "MX") String messageType,
            @RequestParam(defaultValue = "") String identifier,
            @RequestParam(defaultValue = "") String status,
            @RequestParam(defaultValue = "") String from,
            @RequestParam(defaultValue = "") String to,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int pageSize) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdOn");
        Page<MsgLog> swiftModelData = msgLogService.getSearchData(messageType, identifier, status, from, to, page,
                pageSize, sort);
        if (!swiftModelData.isEmpty()) {
            return ResponseEntity.ok(swiftModelData);
        } else {
            String notFoundMessage = "No Records found.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundMessage);
        }
    }

    // fetch to export
    @GetMapping("/export")
    public ResponseEntity<?> findByFilterForListToExcel(
            @RequestParam(defaultValue = "MX") String messageType,
            @RequestParam(defaultValue = "") String identifier,
            @RequestParam(defaultValue = "") String status,
            @RequestParam(defaultValue = "") String from,
            @RequestParam(defaultValue = "") String to) {
        // Sort sort = Sort.by(Sort.Direction.DESC, "createdOn");
        List<MsgLogExport> swiftModelData = msgLogService.getFilteredForListToExcel(messageType, identifier, status,
                from, to);
        if (!swiftModelData.isEmpty()) {
            return ResponseEntity.ok(swiftModelData);
        } else {
            String notFoundMessage = "No Records found.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundMessage);
        }
    }

}