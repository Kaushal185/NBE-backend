package com.grs.angproject.msgtrace;

import java.util.List;

public interface MsgTraceService {

    List<MsgTrace> getMessageHistory(Long id);

}
