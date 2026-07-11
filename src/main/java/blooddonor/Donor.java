package blooddonor;

import jakarta.persistence.*;

@Entity
@Table(name = "DONORS")
public class Donor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "donor_seq")

    @SequenceGenerator(
            name = "donor_seq",
            sequenceName = "DONORS_SEQ",
            allocationSize = 1)

    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "BLOOD_GROUP")
    private String bloodGroup;

    @Column(name = "CITY")
    private String city;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "EMAIL")
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}