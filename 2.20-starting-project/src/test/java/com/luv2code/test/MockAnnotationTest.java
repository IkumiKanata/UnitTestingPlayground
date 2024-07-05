package com.luv2code.test;

import com.luv2code.component.MvcTestingExampleApplication;
import com.luv2code.component.dao.ApplicationDao;
import com.luv2code.component.models.CollegeStudent;
import com.luv2code.component.models.StudentGrades;
import com.luv2code.component.service.ApplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = MvcTestingExampleApplication.class)
public class MockAnnotationTest {

    @Autowired
    ApplicationContext context;

    @Autowired
    CollegeStudent collegeStudent;

    @Autowired
    StudentGrades studentGrades;

    @Mock
    private ApplicationDao applicationDao;

    @InjectMocks
    private ApplicationService applicationService;

    @BeforeEach
    public void setUp() {
        collegeStudent.setFirstname("John");
        collegeStudent.setLastname("Doe");
        collegeStudent.setEmailAddress("test.com");
        collegeStudent.setStudentGrades(studentGrades);
    }

    @Test
    @DisplayName("when and verify")
    void assertEqualsTestAddGrades() {
        when(applicationDao.addGradeResultsForSingleClass(
                studentGrades.getMathGradeResults())).thenReturn(100.00);

        assertEquals(100.00,
                applicationService.addGradeResultsForSingleClass(
                        studentGrades.getMathGradeResults()));

        verify(applicationDao, times(1)).addGradeResultsForSingleClass(studentGrades.getMathGradeResults()
        );
    }

}

//`@Mock` と `@InjectMocks` は、モックオブジェクトを作成し、依存性を注入するために Mockito テストフレームワークで使用されるアノテーションですが、彼らの役割は異なります。
//
//        - `@Mock`: このアノテーションは、テスト対象のクラスが依存しているクラスのモック（模擬）オブジェクトを作成するために使用されます。`@Mock`で作成されたオブジェクトは、実際の実装の代わりにテスト中に使用され、テストの際に特定の振る舞いをシミュレートするために設定できます。
//
//        - `@InjectMocks`: このアノテーションは、テスト対象のクラスのインスタンスを作成し、そのクラス内の`@Mock`（または`@Spy`）で注釈されたフィールドに対して、自動的に依存性注入を行います。つまり、`@InjectMocks`で作成されたインスタンス内のモックオブジェクトが、自動的に関連するフィールドに注入されます。
//
//簡単に言うと、`@Mock`はモックオブジェクトを作成するために、`@InjectMocks`はそのモックオブジェクトをテスト対象のクラスに注入するために使用されます。
