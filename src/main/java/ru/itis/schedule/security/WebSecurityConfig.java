package ru.itis.schedule.security;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/auditories").permitAll()
                .antMatchers("/auditories/id/{auditory}").permitAll()
                .antMatchers("/auditories/resources").permitAll()
                .antMatchers("/auditories/resources/generate").permitAll()
                .antMatchers("/auditories/delete/{id}").permitAll()
                .antMatchers("/auditories/resources/auditory-id/{auditory}").permitAll()
                .antMatchers("/auditories/resources/timeslot-id/{timeslot}").permitAll()
                .antMatchers("/cources").permitAll()
                .antMatchers("/exams").permitAll()
                .antMatchers("/groups").permitAll()
                .antMatchers("/groups/groupset-id/{id}").permitAll()
                .antMatchers("/main-subjects").permitAll()
                .antMatchers("/main-subjects/generate").permitAll()
                .antMatchers("/main-subjects/subject-set-id/{id}").permitAll()
                .antMatchers("/main-subjects/id/{id}").permitAll()
                .antMatchers("/optional-subjects").permitAll()
                .antMatchers("/optional-subjects/course-id/{course}").permitAll()
                .antMatchers("/optional-subjects/id/{id}").permitAll()
                .antMatchers("/professors").permitAll()
                .antMatchers("/professors/resources").permitAll()
                .antMatchers("/professors/resources/generate").permitAll()
                .antMatchers("/professors/resources/professor-id/{professor}").permitAll()
                .antMatchers("/professors/resources/timeslot-id/{timeslot}").permitAll()
                .antMatchers("/subjects").permitAll()
                .antMatchers("/subjects/course-id/{course}").permitAll()
                .antMatchers("/timeslots").permitAll()
                .antMatchers("/timeslots/generate").permitAll()
                .antMatchers("/timeslots/id/{id}").permitAll()
                .antMatchers("/timeslots-day").permitAll()
                .antMatchers("/timeslots-day/set-period").permitAll()
                .antMatchers("/timeslots-time").permitAll()
                .antMatchers("/timeslots-time/id/{id}").permitAll();




    }

}
