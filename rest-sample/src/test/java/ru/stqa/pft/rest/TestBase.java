package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.SkipException;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

public class TestBase {
  public boolean isIssueOpen(int issueId) throws IOException {
    String json = getExecutor().execute(Request.Get("http://bugify.stqa.ru/api/issues/" + issueId + ".json"))
            .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    Set<Issue> singleElementSet = new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
    }.getType());
    Issue issue = singleElementSet.iterator().next();
    String issueStatus = issue.getState_name();
    System.out.println(issueStatus);
    if (issueStatus.equals("Closed")) {
      return false;
    }
    return true;
  }

  //private String statusIssue(int id) throws IOException {
  //  String json = getExecutor()
  //          .execute(Request.Get("http://bugify.stqa.ru/api/issues/"+ id +".json?limit=500")).returnContent().asString();
  //  String parsed = new JsonParser().parse(json).toString();
  //  return parsed;
  //}

  public void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

  private Executor getExecutor() {
    return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
  }
}
