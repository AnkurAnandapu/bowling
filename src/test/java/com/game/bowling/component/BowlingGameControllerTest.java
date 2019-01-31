package com.game.bowling.component;


import com.game.bowling.BowlingApplicationTests;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BowlingGameControllerTest extends BowlingApplicationTests {

    @Test
    public void whenTheFirstRollThrowOnePinAndMissOthersRolls() throws Exception {

        getMockMvc().perform(post("/bowling/scores/calculate")
                .contentType(APPLICATION_JSON_UTF8)
                .param("frames", "1- -- -- -- -- -- -- -- -- --"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.scoresPerFrame", equalTo(Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1))));
    }

    @Test
    public void whenTheFirstRollThrowTwoPinAndMissOthersRolls() throws Exception {

        getMockMvc().perform(post("/bowling/scores/calculate")
                .contentType(APPLICATION_JSON_UTF8)
                .param("frames", "2- -- -- -- -- -- -- -- -- --"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.scoresPerFrame", equalTo(Arrays.asList(2, 2, 2, 2, 2, 2, 2, 2, 2, 2))));
    }

    @Test
    public void whenAllRollsThrowOnePin() throws Exception {

        getMockMvc().perform(post("/bowling/scores/calculate")
                .contentType(APPLICATION_JSON_UTF8)
                .param("frames", "11 11 11 11 11 11 11 11 11 11"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.scoresPerFrame", equalTo(Arrays.asList(2, 4, 6, 8, 10, 12, 14, 16, 18, 20))));
    }

    @Test
    public void whenTheFirstTurnDoAStrikeAndMissOtherRolls() throws Exception {

        getMockMvc().perform(post("/bowling/scores/calculate")
                .contentType(APPLICATION_JSON_UTF8)
                .param("frames", "X -- -- -- -- -- -- -- -- --"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.scoresPerFrame", equalTo(Arrays.asList(10, 10, 10, 10, 10, 10, 10, 10, 10, 10))));
    }

    @Test
    public void whenTheFirstTurnDoASpareAndMissOtherRolls() throws Exception {

        getMockMvc().perform(post("/bowling/scores/calculate")
                .contentType(APPLICATION_JSON_UTF8)
                .param("frames", "3/ -- -- -- -- -- -- -- -- --"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.scoresPerFrame", equalTo(Arrays.asList(10, 10, 10, 10, 10, 10, 10, 10, 10, 10))));
    }

    @Test
    public void getNextThrowOfBonusAfterSpare() throws Exception {

        getMockMvc().perform(post("/bowling/scores/calculate")
                .contentType(APPLICATION_JSON_UTF8)
                .param("frames", "3/ 3- -- -- -- -- -- -- -- --"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.scoresPerFrame", equalTo(Arrays.asList(13, 16, 16, 16, 16, 16, 16, 16, 16, 16))));
    }

    @Test
    public void whenNotBeASpareTheScoreOfTenInTwoConsecutiveRollsButInDifferentTurns() throws Exception {

        getMockMvc().perform(post("/bowling/scores/calculate")
                .contentType(APPLICATION_JSON_UTF8)
                .param("frames", "35 53 -- -- -- -- -- -- -- --"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.scoresPerFrame", equalTo(Arrays.asList(8, 16, 16, 16, 16, 16, 16, 16, 16, 16))));
    }


    @Test
    public void whenNextTwoRollsOfBonusAfterStrike() throws Exception {
        getMockMvc().perform(post("/bowling/scores/calculate")
                .contentType(APPLICATION_JSON_UTF8)
                .param("frames", "X 53 -- -- -- -- -- -- -- --"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.scoresPerFrame", equalTo(Arrays.asList(18, 26, 26, 26, 26, 26, 26, 26, 26, 26))));
    }

    @Test
    public void when300PerfectGame() throws Exception {

        getMockMvc().perform(post("/bowling/scores/calculate")
                .contentType(APPLICATION_JSON_UTF8)
                .param("frames", "X X X X X X X X X XXX"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.scoresPerFrame", equalTo(Arrays.asList(30, 60, 90, 120, 150, 180, 210, 240, 270, 300))));
    }

    @Test
    public void when150AllTheTurnsWithSpareAndTheLastBonus5() throws Exception {

        getMockMvc().perform(post("/bowling/scores/calculate")
                .contentType(APPLICATION_JSON_UTF8)
                .param("frames", "5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.scoresPerFrame", equalTo(Arrays.asList(15, 30, 45, 60, 75, 90, 105, 120, 135, 150))));
    }

    @Test
    public void whenAllSpares() throws Exception {

        getMockMvc().perform(post("/bowling/scores/calculate")
                .contentType(APPLICATION_JSON_UTF8)
                .param("frames", "9/ 9/ 9/ 9/ 9/ 9/ 9/ 9/ 9/ 9/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.scoresPerFrame", equalTo(Arrays.asList(19, 38, 57, 76, 95, 114, 133, 152, 171, 184))));
    }

    @Test
    public void whenInvalidMoreNumberOfFrames() throws Exception {

        getMockMvc().perform(post("/bowling/scores/calculate")
                .contentType(APPLICATION_JSON_UTF8)
                .param("frames", "9/ 9/ 9/ 9/ 9/ 9/ 9/ 9/ 9/ 9/ 9/3"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.url", endsWith("/bowling/scores/calculate")))
                .andExpect(jsonPath("$.errorCode", equalTo("400")))
                .andExpect(jsonPath("$.message", equalTo("Number of frames per game should be ten, but there are 11 frames in the input")))
                .andExpect(jsonPath("$.exception", equalTo("InvalidParameterException")));
    }

    @Test
    public void whenInvalidLessNumberOfFrames() throws Exception {

        getMockMvc().perform(post("/bowling/scores/calculate")
                .contentType(APPLICATION_JSON_UTF8)
                .param("frames", "9/ 9/"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.url", endsWith("/bowling/scores/calculate")))
                .andExpect(jsonPath("$.errorCode", equalTo("400")))
                .andExpect(jsonPath("$.message", equalTo("Number of frames per game should be ten, but there are 2 frames in the input")))
                .andExpect(jsonPath("$.exception", equalTo("InvalidParameterException")));
    }

    @Test
    public void whenInvalidTenthFrame() throws Exception {

        getMockMvc().perform(post("/bowling/scores/calculate")
                .contentType(APPLICATION_JSON_UTF8)
                .param("frames", "9/ 9/ 9/ 9/ 9/ 9/ 9/ 9/ 9/ 9/31"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.url", endsWith("/bowling/scores/calculate")))
                .andExpect(jsonPath("$.errorCode", equalTo("400")))
                .andExpect(jsonPath("$.message", equalTo("Invalid Frame in the input: 9/31")))
                .andExpect(jsonPath("$.exception", equalTo("InvalidParameterException")));
    }

    @Test
    public void whenInvalidFrame() throws Exception {

        getMockMvc().perform(post("/bowling/scores/calculate")
                .contentType(APPLICATION_JSON_UTF8)
                .param("frames", "9/1 9/ 9/ 9/ 9/ 9/ 9/ 9/ 9/ 9/3"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.url", endsWith("/bowling/scores/calculate")))
                .andExpect(jsonPath("$.errorCode", equalTo("400")))
                .andExpect(jsonPath("$.message", equalTo("Invalid Frame in the input: 9/1")))
                .andExpect(jsonPath("$.exception", equalTo("InvalidParameterException")));
    }

    @Test
    public void whenInvalidSparePlaceFrame() throws Exception {

        getMockMvc().perform(post("/bowling/scores/calculate")
                .contentType(APPLICATION_JSON_UTF8)
                .param("frames", "/1 9/ 9/ 9/ 9/ 9/ 9/ 9/ 9/ 9/3"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.url", endsWith("/bowling/scores/calculate")))
                .andExpect(jsonPath("$.errorCode", equalTo("400")))
                .andExpect(jsonPath("$.message", equalTo("Invalid Frame in the input: /1")))
                .andExpect(jsonPath("$.exception", equalTo("InvalidParameterException")));
    }
}
