package com.luv2code.springmvc;


import com.luv2code.springmvc.models.CollegeStudent;
import com.luv2code.springmvc.service.StudentAndGradeService;
import com.luv2code.springmvc.service.StudentDao;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestPropertySource("/application.properties")
@AutoConfigureMockMvc
@SpringBootTest
public class GradeBookControllerTest {

    private static MockHttpServletRequest request;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private StudentDao studentDao;

    @Mock
    private StudentAndGradeService studentAndGradeService;



    //    @BeforeAll`アノテーションを使用するメソッドは、テストクラスの全テストメソッドの実行前に一度だけ実行されるセットアップメソッドです。このメソッドが `static`である必要があるのは、
//    JUnit Jupiter（JUnit 5）において、`
//    @BeforeAll`アノテーションが付与されたメソッドはクラスレベルで実行されるためです。つまり、テストクラスのインスタンスが生成される前に実行されるため、このメソッドにアクセスするためにはメソッドがクラスメソッド（`static`メソッド）である必要があります。
//
//            `static`メソッドは、クラスのインスタンスを生成しなくても呼び出すことができるメソッドです。`
//    @BeforeAll`アノテーションでマークされたメソッドは、テストクラスのどのインスタンスにも属さないクラスレベルの初期化を行うために使用されます。これにより、テストクラス内のすべてのテストメソッドが実行される前に一度だけ必要なセットアップを行うことができます。例えば、データベースの接続を開始する、テストデータを準備する、システムプロパティを設定するなどの操作が含まれます。
//
//    非 `static`メソッドは、クラスのインスタンスに属しており、そのインスタンスが存在しないと実行できません。しかし、`
//    @BeforeAll`アノテーションが付与されたメソッドはテストクラスのインスタンスが生成される前に実行されるため、非 `static`コンテキストでは使用できません。そのため、
//    JUnit Jupiterはこのメソッドが `static`であることを要求します。
    @BeforeAll
    public static void setUp() {
        request = new MockHttpServletRequest();
        request.setParameter("firstname", "Ikumi");
        request.setParameter("lastname", "Kanata");
        request.setParameter("emailAddress", "gundam.com");
    }

    @BeforeEach
    public void beforeTest() {
        jdbcTemplate.execute("insert into student (id, firstname, lastname, email_address) values (1, 'Taco', 'Bell', 'test.com')");
    }

    @AfterEach
    public void tearDown() {
        jdbcTemplate.execute("delete from student");
    }

    @Test
    public void getStudentsHttpRequest() throws Exception {

        CollegeStudent student = new CollegeStudent("John", "Doe", "test2.com");

        CollegeStudent student2 = new CollegeStudent("Jane", "Doe", "test3.com");

        List<CollegeStudent> collegeStudents = List.of(student, student2);

        when(studentAndGradeService.getGradeBook()).thenReturn(collegeStudents);

        assertIterableEquals(collegeStudents, studentAndGradeService.getGradeBook());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(status().isOk()).andReturn();

        ModelAndView modelAndView = result.getModelAndView();

        ModelAndViewAssert.assertViewName(modelAndView, "index");
    }

    @Test
    public void createStudentHttpRequest() throws Exception {

        CollegeStudent student = new CollegeStudent("John", "Doe", "test2.com");

        List<CollegeStudent> collegeStudents = List.of(student);

        when(studentAndGradeService.getGradeBook()).thenReturn(collegeStudents);

        assertIterableEquals(collegeStudents, studentAndGradeService.getGradeBook());


        MvcResult mvcResult = this.mockMvc.perform(post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("firstname", request.getParameterValues("firstname"))
                        .param("lastname", request.getParameterValues("lastname"))
                        .param("emailAddress", request.getParameterValues("emailAddress")))
                .andExpect(status().isOk()).andReturn();

        ModelAndView modelAndView = mvcResult.getModelAndView();
        ModelAndViewAssert.assertViewName(modelAndView, "index");

        CollegeStudent verifyStudent = studentDao.findByEmailAddress("gundam.com");
        assertEquals("Ikumi", verifyStudent.getFirstname());
    }
}
