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
    public ArrayList<MsgLog> getMsgRelationById(@PathVariable Long id) {
        ArrayList<MsgLog> responseList; //
        Optional<MsgRelation> obj1 = msgRelationService.getMsg1RelationObject(id);
        Optional<MsgRelation> obj2 = msgRelationService.getMsg2RelationObject(id);
        if (obj1.isPresent()) {
            Optional<MsgLog> temp = swiftService.getSelectedId(id);
            MsgLog relObj1 = temp.get();
            // String relObj1Msg = relObj1.getMessage(); // message 1
            // String plusIndex1 = findPlusIndices(relObj1Msg); // finding indices for message 1

            MsgRelation msgRelation = obj1.get();
            Long id2 = msgRelation.getMsg2();
            Optional<MsgLog> temp2 = swiftService.getSelectedId(id2);
            MsgLog relObj2 = temp2.get();
            // String relObj2Msg = swiftModel.getMessage(); // message 2
            // String plusIndex2 = findPlusIndices(relObj2Msg); // finding indices for message 2
            responseList = new ArrayList<MsgLog>(List.of(relObj1, relObj2));
        } else if (obj2.isPresent()){
            MsgRelation msgRelation = obj2.get();
            Long id1 = msgRelation.getMsg1();
            Optional<MsgLog> temp1 = swiftService.getSelectedId(id1);
            MsgLog relObj1 = temp1.get();

            Long id2 = msgRelation.getMsg2();
            Optional<MsgLog> temp2 = swiftService.getSelectedId(id2);
            MsgLog relObj2 = temp2.get();

            responseList = new ArrayList<MsgLog>(List.of(relObj1, relObj2));
        }
        else {
            responseList = new ArrayList<MsgLog>();
        }

        return responseList;
    }

}
