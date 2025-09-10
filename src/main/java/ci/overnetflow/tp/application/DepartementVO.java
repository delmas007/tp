package ci.overnetflow.tp.application;

import ci.overnetflow.tp.domain.Departement;

public class DepartementVO {
    private Long id;
    private Long code;
    private String designation;

    public DepartementVO(Departement departement) {
        this.id = departement.getId();
        this.code = departement.getCode();
        this.designation = departement.getDesignation();
    }

    public Long getId() {
        return id;
    }

    public Long getCode() {
        return code;
    }

    public String getDesignation() {
        return designation;
    }
}
