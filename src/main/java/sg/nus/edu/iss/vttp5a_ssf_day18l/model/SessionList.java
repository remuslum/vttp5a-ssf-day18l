package sg.nus.edu.iss.vttp5a_ssf_day18l.model;

import java.util.ArrayList;
import java.util.List;

public class SessionList {
    private List<Session> sessionList;

    public SessionList() {
        this.sessionList = new ArrayList<>();
    }

    public List<Session> getSessionList() {
        return sessionList;
    }

    public void setSessionList(List<Session> sessionList) {
        this.sessionList = sessionList;
    }

    
}
