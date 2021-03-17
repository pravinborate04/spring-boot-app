package com.borate.pravin.pim.entities;

import com.borate.pravin.pim.config.AuthUserDetails;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Pravin Borate
 * 11/03/21
 */
@MappedSuperclass
public abstract class BaseEntity {

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "Asia/Kolkata")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", columnDefinition = "DATETIME NULL DEFAULT CURRENT_TIMESTAMP")
    private Date createdDate;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "Asia/Kolkata")
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_date", columnDefinition = "DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date modifiedDate;

    @Column(name = "version", columnDefinition = "INT NULL DEFAULT 1")
    private int version;

    @OneToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @OneToOne
    @JoinColumn(name = "modified_by")
    private User modifiedBy;

    @Column(name = "deleted", columnDefinition = "TINYINT(1) NULL DEFAULT 0")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean deleted;


    /**
     * @return the createdBy
     */
    public User getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return the createdDate
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * @return the deleted
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * @param deleted the deleted to set
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * @return the modifiedBy
     */
    public User getModifiedBy() {
        return modifiedBy;
    }

    /**
     * @param modifiedBy the modifiedBy to set
     */
    public void setModifiedBy(User modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    /**
     * @return the modifiedDate
     */
    public Date getModifiedDate() {
        return modifiedDate;
    }

    /**
     * @param modifiedDate the modifiedDate to set
     */
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    /**
     * @return the version
     */
    public int getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(int version) {
        this.version = version;
    }

    @PrePersist
    public void prePersist() {
        Date d = new Date();
        createdDate = d;
        modifiedDate = d;

        User loggedUser = getLoggedUser();
        createdBy = loggedUser;
        modifiedBy = loggedUser;
    }

    @PreUpdate
    public void preUpdate() {
        Date d = new Date();
        modifiedDate = d;
        if (createdDate == null) {
            createdDate = d;
        }

        User loggedUser = getLoggedUser();
        modifiedBy = loggedUser;
        if (createdBy == null) {
            createdBy = loggedUser;
        }
    }

    /**
     * @return the id
     */
    public abstract Long getId();

    /**
     * @param id the id to set
     */
    public abstract void setId(Long id);

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BaseEntity other = (BaseEntity) obj;
        if (getId() == null) {
            if (other.getId() != null)
                return false;
        } else if (!getId().equals(other.getId()))
            return false;
        return true;
    }


    private User getLoggedUser() {
        if (!(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
            AuthUserDetails loggedUser = (AuthUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return loggedUser.getUser();
        }
        return null;
    }
}
