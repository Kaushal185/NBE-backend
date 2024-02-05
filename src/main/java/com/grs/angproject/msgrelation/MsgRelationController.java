package com.grs.angproject.msgrelation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.grs.angproject.swift.MsgLog;
import com.grs.angproject.swift.MsgLogService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/msg-relations")
@CrossOrigin(origins = "http://localhost:5000")
public class MsgRelationController {

    @Autowired
    private MsgRelationService msgRelationService;
    @Autowired
    private MsgLogService swiftService;

    @GetMapping
    public List<MsgRelation> getAllMsgRelations() {
        return msgRelationService.getAllMsgRelations();
    }

    @GetMapping("/{id}")
    public ArrayList<String> getMsgRelationById(@PathVariable Long id) {
        ArrayList<String> responseList; //
        Optional<MsgRelation> obj = msgRelationService.getMsgRelationObject(id);
        if (obj.isPresent()) {
            Optional<MsgLog> relObj1 = swiftService.getMessageById(id);
            MsgLog swiftModel = relObj1.get();
            String relObj1Msg = swiftModel.getMessage(); // message 1
            // String plusIndex1 = findPlusIndices(relObj1Msg); // finding indices for message 1

            MsgRelation msgRelation = obj.get();
            Long id2 = msgRelation.getMsg2();
            Optional<MsgLog> relObj2 = swiftService.getMessageById(id2);
            swiftModel = relObj2.get();
            String relObj2Msg = swiftModel.getMessage(); // message 2
            // String plusIndex2 = findPlusIndices(relObj2Msg); // finding indices for message 2
            responseList = new ArrayList<String>(List.of(relObj1Msg, relObj2Msg));
        } else {
            responseList = new ArrayList<String>(List.of("DATA NOT PRESENT", "DATA NOT PRESENT"));
        }

        return responseList;
    }

    private String findPlusIndices(String source) {
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < source.length(); i++){
            if(source.charAt(i) == '+'){
                res.append(i);
                res.append(",");
            }
        }
        if( res.length() == 0) return "NoPlus";
        return res.toString();
    }

    // You can add more methods for CRUD operations

}
