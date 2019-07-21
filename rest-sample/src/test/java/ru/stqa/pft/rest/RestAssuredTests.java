package ru.stqa.pft.rest;

  import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;

  import org.apache.http.client.fluent.Request;
  import org.testng.SkipException;
  import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
  import java.util.Set;

import static org.testng.Assert.assertEquals;

  public class RestAssuredTests extends TestBase{

   // @BeforeClass
   // public void init() {
   //   RestAssured.authentication = RestAssured.basic("288f44776e7bec4bf44fdfeb1e646490", "");
   // }

    @Test
    public void testOpenIssue() throws IOException {
      skipIfNotFixed(1615);
      System.out.println("Да, детка!");
    }

    @Test(enabled = false)
    public void testCreateIssue() throws IOException {
      Set<Issue> oldIssues = getIssues();
      Issue newIssue = new Issue().withSubject("Test issue GIA").withDescription("New test issue GIA");
      int issueId = createIssue(newIssue);
      Set<Issue> newIssues = getIssues();
      oldIssues.add(newIssue.withId(issueId));
      assertEquals(newIssues, oldIssues);
    }

    private Set<Issue> getIssues() throws IOException {
      String json = RestAssured.get("http://bugify.stqa.ru/api/issues.json?limit=500").asString();
      JsonElement parsed = new JsonParser().parse(json);
      JsonElement issues = parsed.getAsJsonObject().get("issues");
      return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
    }


    private int createIssue(Issue newIssue) throws IOException {
      String json = RestAssured.given()
              .parameter("subject", newIssue.getSubject())
              .parameter("description", newIssue.getDescription())
              .parameter("state_name", newIssue.getState_name())
              .post("http://bugify.stqa.ru/api/issues.json?limit=500").asString();
      JsonElement parsed = new JsonParser().parse(json);
      return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }
  }