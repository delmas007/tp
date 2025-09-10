package ci.overnetflow.tp.application;

import ci.overnetflow.tp.domain.Departement;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;

public class DepartementVOMockBuilder {
    private Long id;
    private Long code;
    private String designation;

    public DepartementVO build() {
        DepartementVO mock = mock(DepartementVO.class);

        lenient().when(mock.getId()).thenReturn(id);
        lenient().when(mock.getCode()).thenReturn(code);
        lenient().when(mock.getDesignation()).thenReturn(designation);

        return mock;
    }

    public DepartementVOMockBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public DepartementVOMockBuilder setCode(Long code) {
        this.code = code;
        return this;
    }

    public DepartementVOMockBuilder setDesignation(String designation) {
        this.designation = designation;
        return this;
    }
}
