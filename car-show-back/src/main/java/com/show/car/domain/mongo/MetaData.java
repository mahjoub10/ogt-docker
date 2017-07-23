package com.show.car.domain.mongo;

import java.util.Date;

/**
 * Metadata block for Grid fs file.
 */
public class MetaData {

    private String id;

    private Date creationDate;

    private String referenceCar;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getReferenceCar() {
        return referenceCar;
    }

    public void setReferenceCar(String referenceCar) {
        this.referenceCar = referenceCar;
    }
}
