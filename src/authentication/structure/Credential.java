package src.authentication.structure;

public class Credential {
    private String username;
    private String password;
    private String fingerprint;
    private String eyeScanData;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public String getEyeScanData() {
        return eyeScanData;
    }

    public void setEyeScanData(String eyeScanData) {
        this.eyeScanData = eyeScanData;
    }

}

