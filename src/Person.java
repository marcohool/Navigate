public class Person {

    private int recordID;
    private String name;
    private String firm;
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
    private String status;


    public Person(int recordID, String name, String firm, String practiseArea, String speciality, String jobTitle, String ethnicity, String admissionDate, String admissionJuristiction, String firmProfile, String linkedinProfile, String approached, String phoneNo, String email, String status) {
        this.recordID = recordID;
        this.name = name;
        this.firm = firm;
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
        this.status = status;
    }

}
