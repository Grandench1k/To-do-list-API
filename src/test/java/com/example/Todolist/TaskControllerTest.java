package com.example.Todolist;


import com.example.Todolist.Auth.AuthenticationController;
import com.example.Todolist.Auth.AuthenticationResponse;
import com.example.Todolist.Auth.RegisterRequest;
import com.example.Todolist.Board.Board;
import com.example.Todolist.Board.BoardRepository;
import com.example.Todolist.Config.ApplicationConfiguration;
import com.example.Todolist.Config.Impl.JwtServiceImpl;
import com.example.Todolist.Config.JwtService;
import com.example.Todolist.Task.Task;
import com.example.Todolist.Task.TaskController;
import com.example.Todolist.Task.TaskRepository;
import com.example.Todolist.User.User;
import com.example.Todolist.User.UserRepository;
import com.example.Todolist.User.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerTest {

    @Autowired
    UserService userService;
    @Autowired
    TaskController underTest;
    @Autowired
    JwtService jwtService = new JwtServiceImpl();
    @Autowired
    AuthenticationController authenticationController;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ApplicationConfiguration applicationConfiguration;
    @Autowired
    BoardRepository boardRepository;
    String jwtToken;
    List<Task> taskList = new ArrayList<>();
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        String encodedPassword = applicationConfiguration.passwordEncoder().encode("example");
        ResponseEntity<AuthenticationResponse> lol = authenticationController.register(RegisterRequest.builder().firstname("firstname").lastname("lastname").email("exapmle@mail.com").password(encodedPassword).build());
        jwtToken = Objects.requireNonNull(lol.getBody()).getToken();
        User user = userRepository.findAll().stream().findFirst().get();

        boardRepository.save(Board.builder().userID(user.getId()).name("Board").build());
        Board board = boardRepository.findBoardByName("Board");
        Task task1 = Task.builder().boardUUID(board.getUUID()).name("Task1").userID(user.getId()).build();
        Task task2 = Task.builder().boardUUID(board.getUUID()).name("Task2").userID(user.getId()).build();

        taskList.add(task1);
        taskList.add(task2);
        taskRepository.save(task1);
        taskRepository.save(task2);
    }

    @AfterEach
    void tearDown() throws Exception {
        taskRepository.deleteAll();
        userRepository.deleteAll();
        boardRepository.deleteAll();
    }


    @Test
    public void getAllTasks() throws Exception {
        //When
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/tasks")
                .header("Authorization", "Bearer " + jwtToken));
        //Then
        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getTaskByUUID() throws Exception {
        //given
        String taskUUID = taskRepository.findTaskByName("Task1").getUUID();
        //When
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/tasks/" + taskUUID)
                .header("Authorization", "Bearer " + jwtToken));
        //Then
        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void createTask() throws Exception {
        //given
        String body = "{ \"name\" : " + "\"Task3\", \"boardUUID\" : " + "\"" + boardRepository.findBoardByName("Board").getUUID() + "\"" + "}";
        //When
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/tasks/create").header("Authorization", "Bearer " + jwtToken)
                .content(body)
                .contentType("application/json"));
        //Then
        response.andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void updateTask() throws Exception {
        //given
        String UUIDOfTaskToUpdate = taskRepository.findTaskByName("Task1").getUUID();
        String body = "{ \"name\" : " + "\"Task3\"}";
        //When
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                .put("/api/tasks/" + UUIDOfTaskToUpdate + "/update").header("Authorization", "Bearer " + jwtToken)
                .content(body)
                .contentType("application/json"));
        //Then
        response.andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void deleteTask() throws Exception {
        //given
        String UUIDOfTaskToDelete = taskRepository.findTaskByName("Task1").getUUID();
        //When
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/tasks/" + UUIDOfTaskToDelete + "/delete").header("Authorization", "Bearer " + jwtToken));
        //Then
        response.andExpect(MockMvcResultMatchers.status().isOk());
    }
}
