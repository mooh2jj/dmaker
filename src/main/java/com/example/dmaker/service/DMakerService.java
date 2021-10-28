package com.example.dmaker.service;

import com.example.dmaker.entity.Developer;
import com.example.dmaker.repository.DeveloperRepository;
import com.example.dmaker.type.DeveloperLevel;
import com.example.dmaker.type.DeveloperSkillType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class DMakerService {

    private final DeveloperRepository developerRepository;

    @Transactional      // AOP 공통모듈 => 어노테이션으로 , TranctionInterceptor 내 invocation : Aop joint 호출이었다!
    public void createDeveloper() {
        Developer developer = Developer.builder()
                .developerLevel(DeveloperLevel.JUNGNIOR)
                .developerSkillType(DeveloperSkillType.BACK_END)
                .experienceYears(2)
                .name("sg.do")
                .age(31)
                .build();

        developerRepository.save(developer);
    }
}
