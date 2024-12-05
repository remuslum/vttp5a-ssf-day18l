package sg.nus.edu.iss.vttp5a_ssf_day18l.model;

public class Session {
    private String sessionName;
    private String dateOfBirth;

    public Session(){
        this.sessionName = "";
        this.dateOfBirth = "";
    }

    public Session(String sessionName, String dateOfBirth) {
        this.sessionName = sessionName;
        this.dateOfBirth = dateOfBirth;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    
}
