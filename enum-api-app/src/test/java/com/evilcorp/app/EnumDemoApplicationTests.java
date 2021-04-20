package com.evilcorp.app;

import com.evilcorp.app.TextConstant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EnumDemoApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    void testEnumDeserialization() throws Exception {
        this.mvc.perform(post("/make-decision")
                .contentType(APPLICATION_JSON)
                .content("{\n" +
                        "  \"occupation\": \"WORKER\"\n" +
                        "}")
        )
                .andExpect(status().isOk())
                .andExpect(content().string(TextConstant.IT_S_OK));
    }

    @Test
    void textComparingWithRealEnum() throws Exception {
        this.mvc.perform(post("/enrich-application")
                .contentType(APPLICATION_JSON)
                .content("{\n" +
                        "  \"occupation\": \"RETIRED\"\n" +
                        "}")
        )
                .andExpect(status().isOk())
                .andExpect(content().json("{" +
                        "  \"occupation\": \"RETIRED\"," +
                        "  \"someImportantData\": \"" + TextConstant.SOME_IMPORTANT_DATA + "\"" +
                        "}"));
    }

    @Test
    void testComparingTwoDeserializedEnum() throws Exception {
        this.mvc.perform(post("/compare-with-existingState")
                .contentType(APPLICATION_JSON)
                .content("{\n" +
                        "  \"occupation\": \"WORKER\"\n" +
                        "}")
        )
                .andExpect(status().isOk())
                .andExpect(content().string(TextConstant.IT_S_SAME_OCCUPATION_TYPE));
    }

    @Test
    void testEnumComparingWithGender() throws Exception {
        this.mvc.perform(post("/compare-with-existingState")
                .contentType(APPLICATION_JSON)
                .content("{\n" +
                        "  \"occupation\": \"RETIRED\",\n" +
                        "  \"gender\": \"MALE\"\n" +
                        "}")
        )
                .andExpect(status().isOk())
                .andExpect(content().string(TextConstant.IT_IS_NOT_SAME_OCCUPATION_TYPE));
    }

    @Test
    void testProxyUnknownOccupationEnum() throws Exception {
        this.mvc.perform(post("/enrich-application")
                .contentType(APPLICATION_JSON)
                .content("{\n" +
                        "  \"occupation\": \"GAMER\"\n" +
                        "}")
        )
                .andExpect(status().isOk())
                .andExpect(content().json("{" +
                        "  \"occupation\": \"GAMER\"," +
                        "  \"someImportantData\": \"" + TextConstant.SOME_IMPORTANT_DATA + "\"" +
                        "}"));
    }

    @Test
    void comparingTwoUnknownEnumValues() throws Exception {
        this.mvc.perform(post("/compare-with-existingState")
                .contentType(APPLICATION_JSON)
                .content("{\n" +
                        "  \"occupation\": \"GAMER\",\n" +
                        "  \"gender\": \"FEMALE\"\n" +
                        "}")
        )
                .andExpect(status().isOk())
                .andExpect(content().string(TextConstant.IT_S_SAME_OCCUPATION_TYPE));
    }

    @Test
    void testUnknownGenderEnumSending() throws Exception {
        this.mvc.perform(post("/compare-with-existingState")
                .contentType(APPLICATION_JSON)
                .content("{\n" +
                        "  \"occupation\": \"RETIRED\",\n" +
                        "  \"gender\": \"NONE\"\n" +
                        "}")
        )
                .andExpect(status().isOk());
    }

    @Test
    void testDeserializationSameUnknownGenderAndOccupationEnum() throws Exception {
        this.mvc.perform(post("/compare-with-existingState")
                .contentType(APPLICATION_JSON)
                .content("{\n" +
                        "  \"occupation\": \"NONE\",\n" +
                        "  \"gender\": \"NONE\"\n" +
                        "}")
        )
                .andExpect(status().isOk())
                .andExpect(content().string(TextConstant.IT_IS_NOT_SAME_OCCUPATION_TYPE));
    }

}
