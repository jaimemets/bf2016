package com.amsystem.bifaces.user.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Title: UserProfile.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 08/09/2016.
 */

@Entity
@Table(name="PROFILE")
public class Profile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PROFILE")
    private Integer idProfile;

    @Column(name = "NAME", length = 45)
    private String name;

    @Column(name="DESCRIPTION", length=45)
    private String description;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "profiles")
    private Set<User> userSet = new HashSet<>();


    public Profile() {
    }


    public Profile(String name) {
        this.name = name;
    }

    public Integer getIdProfile() {
        return idProfile;
    }

    public void setIdProfile(Integer idProfile) {
        this.idProfile = idProfile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Profile)) return false;

        Profile profile = (Profile) o;

        if (idProfile != null ? !idProfile.equals(profile.idProfile) : profile.idProfile != null) return false;
        if (name != null ? !name.equals(profile.name) : profile.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idProfile != null ? idProfile.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}

