/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fleetflow.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 *
 * @author henry
 */
@Entity
public class AssetPositionHistory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Long assetId;
    private double latitude;
    private double longitude;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateTime;

    public AssetPositionHistory() {
    }


    public AssetPositionHistory(Asset asset, double latitude, double longitude, Date dateTime) {
        this.assetId = asset.getId();
        this.latitude = latitude;
        this.longitude = longitude;
        this.dateTime = dateTime;
    }

    public Long getAssetId() {
        return assetId;
    }

    public void setAssetId(Long assetId) {
        this.assetId = assetId;
    }
    
//    public AssetPositionHistory() {
//        asset = new Asset();
//    }
    
   

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
    }

//    public void setId(Long id) {
//        this.id = id;
//    }
    
    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AssetPositionHistory)) {
            return false;
        }
        AssetPositionHistory other = (AssetPositionHistory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fleetflow.entity.AssetPosition[ id=" + id + " ]";
    }
    
}
