package rtbms.terxlabs.com;

public class info_user_details {
    public info_user_details(String name, String age, String state, String blood_group, String contact, String gender) {
        this.name = name;
        this.age = age;
        this.state = state;
        this.blood_group = blood_group;
        this.contact = contact;
        this.gender = gender;
    }
public info_user_details(){

    }
    String name;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    private String city;
    private String age;
    private String state;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getBlood_group() {
        return blood_group;
    }

    public void setBlood_group(String blood_group) {
        this.blood_group = blood_group;
    }





    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    private String blood_group;

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    private String contact;
    String gender;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public info_user_details(String email) {
        this.email = email;
    }

    String email;

}
