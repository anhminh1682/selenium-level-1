package com.tests.base;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.railway.constant.Constants;
import com.railway.driver.DriverManager;
import com.railway.utilities.LogUtils;
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
import java.util.List;
import java.lang.Object;
import java.util.Map;

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

    @DataProvider(name = "dataTestProvider")
    private Object[][] getDataFromDataset(Method method) throws IOException {
        String testClass = method.getDeclaringClass().getSimpleName();
        File file = new File(Constants.URL.DATA_PATH);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode testCaseNodes = mapper.readTree(file).get(testClass);

        if (testCaseNodes == null || testCaseNodes.isNull()) {
            throw new IllegalArgumentException("No data found for test class: " + testClass);
        }

        List<Map<String, Object>> dataList = new ArrayList<>();

        if (testCaseNodes.isArray()) {
            for (JsonNode node : testCaseNodes) {
                Map<String, Object> map = mapper.convertValue(node, Map.class);
                dataList.add(map);
            }
        } else {
            Map<String, Object> map = mapper.convertValue(testCaseNodes, Map.class);
            dataList.add(map);
        }

        Object[][] datas = new Object[dataList.size()][1];
        for (int i = 0; i < dataList.size(); i++) {
            datas[i][0] = dataList.get(i);
        }

        return datas;
    }

}
