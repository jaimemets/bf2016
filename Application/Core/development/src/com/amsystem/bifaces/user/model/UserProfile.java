package com.amsystem.bifaces.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Title: UserProfile.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 08/09/2016.
 */

@Entity
@Table(name="PROFILE")
public class UserProfile implements Serializable{

    @Id
	@Column(name="COD_PROFILE", length=5)
    private String codProfile;

    @Column(name="DESCRIPTION", length=45)
    private String description;

    public String getCodProfile() {
        return codProfile;
    }

    public void setCodProfile(String codProfile) {
        this.codProfile = codProfile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "codProfile='" + codProfile + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserProfile)) return false;

        UserProfile that = (UserProfile) o;

        if (codProfile != null ? !codProfile.equals(that.codProfile) : that.codProfile != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = codProfile != null ? codProfile.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}

