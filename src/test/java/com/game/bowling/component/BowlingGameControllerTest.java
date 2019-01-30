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
    public void scores_when_the_first_roll_throw_one_pin_and_miss_others_rolls() throws Exception {

        getMockMvc().perform(post("/bowling/scores/calculate")
                .contentType(APPLICATION_JSON_UTF8)
                .param("frames", "1- -- -- -- -- -- -- -- -- --"))
                .andDo(print())
                .andDo(mvcResult -> System.out.println(mvcResult.getResponse()))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.scoresPerFrame", equalTo(Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1))));
    }

    @Test
    public void when_the_first_roll_throw_two_pin_and_miss_others_rolls() throws Exception {

        getMockMvc().perform(post("/bowling/scores/calculate")
                .contentType(APPLICATION_JSON_UTF8)
                .param("frames", "2- -- -- -- -- -- -- -- -- --"))
                .andDo(print())
                .andDo(mvcResult -> System.out.println(mvcResult.getResponse()))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.scoresPerFrame", equalTo(Arrays.asList(2, 2, 2, 2, 2, 2, 2, 2, 2, 2))));
    }

    @Test
    public void when_all_rolls_throw_one_pin() throws Exception {

        getMockMvc().perform(post("/bowling/scores/calculate")
                .contentType(APPLICATION_JSON_UTF8)
                .param("frames", "11 11 11 11 11 11 11 11 11 11"))
                .andDo(print())
                .andDo(mvcResult -> System.out.println(mvcResult.getResponse()))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.scoresPerFrame", equalTo(Arrays.asList(2, 4, 6, 8, 10, 12, 14, 16, 18, 20))));
    }

    @Test
    public void when_the_first_turn_do_a_strike_and_miss_other_rolls() throws Exception {

        getMockMvc().perform(post("/bowling/scores/calculate")
                .contentType(APPLICATION_JSON_UTF8)
                .param("frames", "X -- -- -- -- -- -- -- -- --"))
                .andDo(print())
                .andDo(mvcResult -> System.out.println(mvcResult.getResponse()))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.scoresPerFrame", equalTo(Arrays.asList(10, 10, 10, 10, 10, 10, 10, 10, 10, 10))));
    }

    @Test
    public void when_the_first_turn_do_a_spare_and_miss_other_rolls() throws Exception {

        getMockMvc().perform(post("/bowling/scores/calculate")
                .contentType(APPLICATION_JSON_UTF8)
                .param("frames", "3/ -- -- -- -- -- -- -- -- --"))
                .andDo(print())
                .andDo(mvcResult -> System.out.println(mvcResult.getResponse()))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.scoresPerFrame", equalTo(Arrays.asList(10, 10, 10, 10, 10, 10, 10, 10, 10, 10))));
    }

    @Test
    public void get_next_throw_of_bonus_after_spare() throws Exception {

        getMockMvc().perform(post("/bowling/scores/calculate")
                .contentType(APPLICATION_JSON_UTF8)
                .param("frames", "3/ 3- -- -- -- -- -- -- -- --"))
                .andDo(print())
                .andDo(mvcResult -> System.out.println(mvcResult.getResponse()))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.scoresPerFrame", equalTo(Arrays.asList(13, 16, 16, 16, 16, 16, 16, 16, 16, 16))));
    }

    @Test
    public void when_not_be_a_spare_the_score_of_10_in_two_consecutive_rolls_but_in_different_turns() throws Exception {

        getMockMvc().perform(post("/bowling/scores/calculate")
                .contentType(APPLICATION_JSON_UTF8)
                .param("frames", "35 53 -- -- -- -- -- -- -- --"))
                .andDo(print())
                .andDo(mvcResult -> System.out.println(mvcResult.getResponse()))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.scoresPerFrame", equalTo(Arrays.asList(8, 16, 16, 16, 16, 16, 16, 16, 16, 16))));
    }


    @Test
    public void be_next_two_rolls_of_bonus_after_strike() throws Exception {

        getMockMvc().perform(post("/bowling/scores/calculate")
                .contentType(APPLICATION_JSON_UTF8)
                .param("frames", "X 53 -- -- -- -- -- -- -- --"))
                .andDo(print())
                .andDo(mvcResult -> System.out.println(mvcResult.getResponse()))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.scoresPerFrame", equalTo(Arrays.asList(18, 26, 26, 26, 26, 26, 26, 26, 26, 26))));
    }

    @Test
    public void When_300_a_perfect_game() throws Exception {

        getMockMvc().perform(post("/bowling/scores/calculate")
                .contentType(APPLICATION_JSON_UTF8)
                .param("frames", "X X X X X X X X X XXX"))
                .andDo(print())
                .andDo(mvcResult -> System.out.println(mvcResult.getResponse()))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.scoresPerFrame", equalTo(Arrays.asList(30, 60, 90, 120, 150, 180, 210, 240, 270, 300))));
    }

    @Test
    public void when_150_all_the_turns_with_spare_and_the_last_bonus_5() throws Exception {

        getMockMvc().perform(post("/bowling/scores/calculate")
                .contentType(APPLICATION_JSON_UTF8)
                .param("frames", "5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/5"))
                .andDo(print())
                .andDo(mvcResult -> System.out.println(mvcResult.getResponse()))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.scoresPerFrame", equalTo(Arrays.asList(15, 30, 45, 60, 75, 90, 105, 120, 135, 150))));
    }


    @Test
    public void scores_when_all_rolls_throw_one_pin() throws Exception {

        getMockMvc().perform(post("/bowling/scores/calculate")
                .contentType(APPLICATION_JSON_UTF8)
                .param("frames", "11 11 11 11 11 11 11 11 11 11"))
                .andDo(print())
                .andDo(mvcResult -> System.out.println(mvcResult.getResponse()))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.scoresPerFrame", equalTo(Arrays.asList(2, 4, 6, 8, 10, 12, 14, 16, 18, 20))));
    }

    @Test
    public void when_all_Spares() throws Exception {

        getMockMvc().perform(post("/bowling/scores/calculate")
                .contentType(APPLICATION_JSON_UTF8)
                .param("frames", "9/ 9/ 9/ 9/ 9/ 9/ 9/ 9/ 9/ 9/3"))
                .andDo(print())
                .andDo(mvcResult -> System.out.println(mvcResult.getResponse()))
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
                .andDo(mvcResult -> System.out.println(mvcResult.getResponse()))
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
                .andDo(mvcResult -> System.out.println(mvcResult.getResponse()))
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
                .andDo(mvcResult -> System.out.println(mvcResult.getResponse()))
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
                .andDo(mvcResult -> System.out.println(mvcResult.getResponse()))
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
                .andDo(mvcResult -> System.out.println(mvcResult.getResponse()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.url", endsWith("/bowling/scores/calculate")))
                .andExpect(jsonPath("$.errorCode", equalTo("400")))
                .andExpect(jsonPath("$.message", equalTo("Invalid Frame in the input: /1")))
                .andExpect(jsonPath("$.exception", equalTo("InvalidParameterException")));
    }
}
