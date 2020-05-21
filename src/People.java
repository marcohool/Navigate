public class People {

    private int personID;
    private String name;
    private String firmName;
    private String practiseArea;
    private String speciality;
    private String jobTitle;
    private String ethnicity;
    private String admissionDate;
    private String admissionJuristiction;
    private String firmProfile;
    private String linkedinProfile;
    private String approached;
    private String phoneNo;
    private String email;


    public People(int personID, String name, String firmName, String practiseArea, String speciality, String jobTitle, String ethnicity, String admissionDate, String admissionJuristiction, String firmProfile, String linkedinProfile, String approached, String phoneNo, String email) {
        this.personID = personID;
        this.name = name;
        this.firmName = firmName;
        this.practiseArea = practiseArea;
        this.speciality = speciality;
        this.jobTitle = jobTitle;
        this.ethnicity = ethnicity;
        this.admissionDate = admissionDate;
        this.admissionJuristiction = admissionJuristiction;
        this.firmProfile = firmProfile;
        this.linkedinProfile = linkedinProfile;
        this.approached = approached;
        this.phoneNo = phoneNo;
        this.email = email;
    }

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    public String getPractiseArea() {
        return practiseArea;
    }

    public void setPractiseArea(String practiseArea) {
        this.practiseArea = practiseArea;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    public String getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(String admissionDate) {
        this.admissionDate = admissionDate;
    }

    public String getAdmissionJuristiction() {
        return admissionJuristiction;
    }

    public void setAdmissionJuristiction(String admissionJuristiction) {
        this.admissionJuristiction = admissionJuristiction;
    }

    public String getFirmProfile() {
        return firmProfile;
    }

    public void setFirmProfile(String firmProfile) {
        this.firmProfile = firmProfile;
    }

    public String getLinkedinProfile() {
        return linkedinProfile;
    }

    public void setLinkedinProfile(String linkedinProfile) {
        this.linkedinProfile = linkedinProfile;
    }

    public String getApproached() {
        return approached;
    }

    public void setApproached(String approached) {
        this.approached = approached;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
