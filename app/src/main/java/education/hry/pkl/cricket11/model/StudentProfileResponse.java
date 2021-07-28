package education.hry.pkl.cricket11.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class StudentProfileResponse {

    @SerializedName("StudentProfile")
    @Expose
    private List<StudentProfile> studentProfile = new ArrayList<StudentProfile>();

    public List<StudentProfile> getStudentProfile() {
        return studentProfile;
    }

    public void setStudentProfile(List<StudentProfile> studentProfile) {
        this.studentProfile = studentProfile;
    }


    public static class StudentProfile {

        @SerializedName("AccountType")
        @Expose
        private String AccountType;


        @SerializedName("Registration_Id")
        @Expose
        private Integer registrationId;
        @SerializedName("First_Name")
        @Expose
        private String firstName;
        @SerializedName("Last_Name")
        @Expose
        private String lastName;
        @SerializedName("Mobile")
        @Expose
        private String mobile;
        @SerializedName("Email")
        @Expose
        private String email;
        @SerializedName("Gender")
        @Expose
        private String gender;
        @SerializedName("District_Id")
        @Expose
        private String districtId;
        @SerializedName("College_Id")
        @Expose
        private String collegeId;

        @SerializedName("District")
        @Expose
        private String district;
        @SerializedName("College")
        @Expose
        private String college;
        @SerializedName("Course_Type")
        @Expose
        private String courseType;
        @SerializedName("Course_Name")
        @Expose
        private String courseName;
        @SerializedName("Course_Year")
        @Expose
        private String courseYear;
        @SerializedName("OTP")
        @Expose
        private String oTP;

        @SerializedName("Entry_Date")
        @Expose
        private String entryDate;

         @SerializedName("Designation")
        @Expose
        private String Designation;


         @SerializedName("ProfilePic")
        @Expose
        private String ProfilePic;


         @SerializedName("StateName")
        @Expose
        private String StateName;

 @SerializedName("profession")
        @Expose
        private String profession;

        public String getProfession() {
            return profession;
        }

        public void setProfession(String profession) {
            this.profession = profession;
        }

        public String getDesignation() {
            return Designation;
        }

        public void setDesignation(String designation) {
            Designation = designation;
        }

        public String getProfilePic() {
            return ProfilePic;
        }

        public void setProfilePic(String profilePic) {
            ProfilePic = profilePic;
        }

        public String getStateName() {
            return StateName;
        }

        public void setStateName(String stateName) {
            StateName = stateName;
        }

        public String getAccountType() {
            return AccountType;
        }

        public void setAccountType(String accountType) {
            AccountType = accountType;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getCollege() {
            return college;
        }

        public void setCollege(String college) {
            this.college = college;
        }

        public String getoTP() {
            return oTP;
        }

        public void setoTP(String oTP) {
            this.oTP = oTP;
        }

        public Integer getRegistrationId() {
            return registrationId;
        }

        public void setRegistrationId(Integer registrationId) {
            this.registrationId = registrationId;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getDistrictId() {
            return districtId;
        }

        public void setDistrictId(String districtId) {
            this.districtId = districtId;
        }

        public String getCollegeId() {
            return collegeId;
        }

        public void setCollegeId(String collegeId) {
            this.collegeId = collegeId;
        }

        public String getCourseType() {
            return courseType;
        }

        public void setCourseType(String courseType) {
            this.courseType = courseType;
        }

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public String getCourseYear() {
            return courseYear;
        }

        public void setCourseYear(String courseYear) {
            this.courseYear = courseYear;
        }

        public String getOTP() {
            return oTP;
        }

        public void setOTP(String oTP) {
            this.oTP = oTP;
        }

        public String getEntryDate() {
            return entryDate;
        }

        public void setEntryDate(String entryDate) {
            this.entryDate = entryDate;
        }

    }
}


