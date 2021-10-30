package com.example.dmaker.service;

import com.example.dmaker.code.StatusCode;
import com.example.dmaker.dto.CreateDeveloper;
import com.example.dmaker.dto.DeveloperDetailDto;
import com.example.dmaker.dto.DeveloperDto;
import com.example.dmaker.entity.Developer;
import com.example.dmaker.exception.DMakerException;
import com.example.dmaker.repository.DeveloperRepository;
import com.example.dmaker.repository.RetiredDeveloperRepository;
import com.example.dmaker.type.DeveloperLevel;
import com.example.dmaker.type.DeveloperSkillType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;


import static com.example.dmaker.exception.DMakerErrorCode.DUPLICATED_MEMBER_ID;
import static com.example.dmaker.type.DeveloperLevel.*;
import static com.example.dmaker.type.DeveloperSkillType.FRONT_END;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DMakerServiceTest {

    @Mock
    private DeveloperRepository developerRepository;

    @Mock
    private RetiredDeveloperRepository retiredDeveloperRepository;

    // 격리성 위해 mock으로 만들어준다
    @InjectMocks
    private DMakerService dMakerService;

    @Test
    public void testSomething(){

        given(developerRepository.findByMemberId(anyString()))
                .willReturn(Optional.of(Developer.builder()
                        .developerLevel(JUNGNIOR)
                        .developerSkillType(FRONT_END)
                        .experienceYears(5)
                        .statusCode(StatusCode.EMPLOYED)
                        .name("name")
                        .age(12)
                        .build()
                ));

        var developerDetail = dMakerService.getDeveloperDetail("memberId");

        assertEquals(JUNGNIOR, developerDetail.getDeveloperLevel());
    }

    @Test
    void createDeveloperTest_success() {
        //given
        CreateDeveloper.Request request = CreateDeveloper.Request.builder()
                .developerLevel(JUNGNIOR)
                .developerSkillType(FRONT_END)
                .experienceYears(7)
                .memberId("memberId")
                .name("name")
                .age(28)
                .build();
        ArgumentCaptor<Developer> captor =
                ArgumentCaptor.forClass(Developer.class);

        //when
        CreateDeveloper.Response response = dMakerService.createDeveloper(request);

        //then
        verify(developerRepository, times(1))
                .save(captor.capture());
        Developer savedDeveloper = captor.getValue();
        assertEquals(JUNGNIOR, savedDeveloper.getDeveloperLevel());
        assertEquals(FRONT_END, savedDeveloper.getDeveloperSkillType());
        assertEquals(7, savedDeveloper.getExperienceYears());

        assertEquals(JUNGNIOR, response.getDeveloperLevel());
        assertEquals(FRONT_END, response.getDeveloperSkillType());
        assertEquals(7, response.getExperienceYears());
    }

}