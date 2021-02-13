/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fleetflow.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;

/**
 *
 * @author henry
 */
@MappedSuperclass
public class Asset implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long assetId;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateAdded;
    private Double homeLongitude;
    private Double homeLatitude;

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        if (dateAdded != null) {
            this.dateAdded = dateAdded;
        } else {
            this.dateAdded = new Date();
        }
    }

    public Double getHomeLongitude() {
        return homeLongitude;
    }

    public void setHomeLongitude(Double homeLongitude) {
        this.homeLongitude = homeLongitude;
    }

    public Double getHomeLatitude() {
        return homeLatitude;
    }

    public void setHomeLatitude(Double homeLLatitude) {
        this.homeLatitude = homeLLatitude;
    }

    public Long getId() {
        return assetId;
    }

//    public void setId(Long id) {
//        this.id = id;
//    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (assetId != null ? assetId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asset)) {
            return false;
        }
        Asset other = (Asset) object;
        if ((this.assetId == null && other.assetId != null) || (this.assetId != null && !this.assetId.equals(other.assetId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Asset[ id=" + assetId + " ]";
    }

}
