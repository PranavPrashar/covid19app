package pranavprashar.coronavirustracker.models;

public class LocationStats {
    private String state;
    private String country;
    private int LatestReportedCaseNumbers;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getLatestReportedCaseNumbers() {
        return LatestReportedCaseNumbers;
    }

    public void setLatestReportedCaseNumbers(int latestReportedCaseNumbers) {
        LatestReportedCaseNumbers = latestReportedCaseNumbers;
    }
}
