package com.example.leadershipranking;

import com.example.leadershipranking.models.UserProfile;
import com.fasterxml.jackson.annotation.JsonValue;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class UserControllerTest extends AbstractTest
{

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void createUser() throws Exception {
        String uri = "https://limitless-thicket-20437.herokuapp.com/leadership";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("display_name", "user1");
        jsonObject.put("country", "TR");

        String inputJson = super.mapToJson(jsonObject);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();


        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        UserProfile userProfile = super.mapFromJson(content, UserProfile.class);
        assertNotNull(userProfile.getUuid());
    }
}
