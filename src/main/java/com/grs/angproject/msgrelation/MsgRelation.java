package com.grs.angproject.msgrelation;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "msg_relation")
public class MsgRelation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "CREATED_ON")
    private Timestamp createdOn;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @Column(name = "UPDATED_ON")
    private Timestamp updatedOn;

    @Column(name = "RELATION_KIND")
    private String relationKind;

    @Column(name = "MSG1")
    private Long msg1;

    @Column(name = "MSG2")
    private Long msg2;

    // Getters and setters
}
