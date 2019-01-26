package ru.stqa.pft.rest.appmanager;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jayway.restassured.RestAssured;

public class RestHelper {

    private ApplicationManager app;

    public RestHelper(ApplicationManager app) {
        this.app = app;
    }


    public String getIssueInfo(int issueId) {
        init();
        String json = RestAssured.get("http://bugify.stqa.ru/api/issues/" + issueId + ".json").asString();
        return json;
    }

    public String getIssueStatus(int issueId) {
        String json = getIssueInfo(issueId);
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issues").getAsJsonArray().get(0).getAsJsonObject().get("state_name").getAsString();
    }

    private void init() {
        RestAssured.authentication = RestAssured.basic("288f44776e7bec4bf44fdfeb1e646490", "");
    }
}
