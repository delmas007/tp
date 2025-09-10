package ci.overnetflow.tp.domain;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;

public class DepartementMockBuilder {
    private Long id;
    private Long code;
    private String designation;

    public Departement build() {
        Departement mock = mock(Departement.class);

        lenient().when(mock.getId()).thenReturn(id);
        lenient().when(mock.getCode()).thenReturn(code);
        lenient().when(mock.getDesignation()).thenReturn(designation);

        return mock;
    }

    public DepartementMockBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public DepartementMockBuilder setCode(Long code) {
        this.code = code;
        return this;
    }

    public DepartementMockBuilder setDesignation(String designation) {
        this.designation = designation;
        return this;
    }
}
