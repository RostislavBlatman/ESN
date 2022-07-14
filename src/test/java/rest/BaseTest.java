package rest;

import org.testng.annotations.BeforeSuite;
import rest.utils.RestWrapper;

public class BaseTest {
    protected RestWrapper api;

    @BeforeSuite
    public void prepare() {
        api = RestWrapper.getApi();
    }
}
