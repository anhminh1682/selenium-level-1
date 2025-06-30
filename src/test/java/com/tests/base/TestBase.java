package com.tests.base;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.railway.constant.Constants;
import com.railway.driver.DriverManager;
import com.tests.ultilities.AppListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.lang.Object;

@Listeners(AppListener.class)
public class TestBase {
    protected SoftAssert softAssert;

    public TestBase() {
        softAssert = new SoftAssert();
    }

    @BeforeMethod
    public void beforeTest(ITestResult testResult) {
        DriverManager.createDriver();
        DriverManager.getDriver().get(Constants.URL.RAILWAY_URL);
    }

    @AfterMethod
    public void afterTest() {
        DriverManager.quitDriver();
    }

    @DataProvider(name="dataTestProvider")
    private Object[][] getDataFromDataset(Method method) throws IOException {
        String testClass = method.getDeclaringClass().getSimpleName();

        File file = new File("src/test/java/com/tests/datasets/dataset.json");

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(file);

        JsonNode testCaseNodes = rootNode.get(testClass);

        Object[][] datas;
        Iterator<String> fieldNamesIterator;

        if(testCaseNodes.isArray()) {
            datas = new Object[testCaseNodes.size()][testCaseNodes.get(0).size()];
            fieldNamesIterator = testCaseNodes.get(0).fieldNames();

            List<String> fieldNames = new ArrayList<>();

            while (fieldNamesIterator.hasNext()) {
                fieldNames.add(fieldNamesIterator.next());
            }

            for (int i = 0; i < datas.length; i++) {
                JsonNode rowNode = testCaseNodes.get(i);
                for (int j = 0; j < fieldNames.size(); j++) {
                    JsonNode valueNode = rowNode.get(fieldNames.get(j));
                    datas[i][j] = mapper.convertValue(valueNode, Object.class);
                }
            }
        } else {
            fieldNamesIterator = testCaseNodes.fieldNames();
            List<String> fieldNames = new ArrayList<>();

            while (fieldNamesIterator.hasNext()) {
                fieldNames.add(fieldNamesIterator.next());
            }

            datas = new Object[1][testCaseNodes.size()];
            for (int i = 0; i < fieldNames.size(); i++) {
                datas[0][i] = mapper.convertValue(testCaseNodes.get(fieldNames.get(i)), Object.class);
            }
        }

        return datas;
    }
}
