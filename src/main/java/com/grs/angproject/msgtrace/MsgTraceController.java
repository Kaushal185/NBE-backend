package com.grs.angproject.msgtrace;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/msg-trace")
@CrossOrigin(origins = "http://localhost:5000")
public class MsgTraceController {

    @Autowired
    private MsgTraceService msgTraceService;

    @GetMapping("/history")
    public List<MsgTrace> getMessageHistory(@RequestParam Long id) {
        return msgTraceService.getMessageHistory(id);
    }
}
