package mockito;

import org.mockito.ArgumentMatcher;

import java.util.List;

class IsListOfTwoElements implements ArgumentMatcher<List> {
    @Override
    public boolean matches(List list) {
        return ((List) list).size() == 2;
    }
}
