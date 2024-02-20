package com.example.kindergarten;

import com.example.kindergarten.entity.Attendance;
import com.example.kindergarten.entity.Child;
import com.example.kindergarten.entity.Parent;
import com.example.kindergarten.entity.School;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class MapperMockFactory {

    public static School getSchoolMock1() {
        var school = new School();
        school.setId(1L);
        school.setName("ZS1");
        school.setHourPrice(BigDecimal.valueOf(10.37));

        return school;
    }

    public static Parent getParentMock1() {
        var parent = new Parent();
        parent.setFirstname("Anna");
        parent.setLastname("Lewandowska");
        parent.setChildren(Set.of(getChildMock3()));

        return parent;
    }

    public static Parent getParentMock2() {
        var parent = new Parent();
        parent.setFirstname("Ela");
        parent.setLastname("Mak");
        parent.setChildren(Set.of(getChildMock1(), getChildMock1()));

        return parent;
    }
    public static List<Parent> getListParentsMock() {
        return List.of(getParentMock1(), getParentMock2());
    }


    public static Child getChildMock1() {
        var child = new Child();
        child.setFirstname("Dariusz");
        child.setLastname("Mak");
        child.setAttendances(Set.of(getAttendanceMock1(), getAttendanceMock4()));
        child.setSchool(getSchoolMock1());

        return child;
    }
    public static Child getChildMock2() {
        var child = new Child();
        child.setFirstname("Katarzyna");
        child.setLastname("Mak");
        child.setAttendances(Set.of(getAttendanceMock3(), getAttendanceMock4()));
        child.setSchool(getSchoolMock1());

        return child;
    }
    public static Child getChildMock3() {
        var child = new Child();
        child.setFirstname("Jan");
        child.setLastname("Lewandowski");
        child.setSchool(getSchoolMock1());

        LinkedHashSet<Attendance> attendances = new LinkedHashSet<>();
        attendances.add(getAttendanceMock2());
        attendances.add(getAttendanceMock4());
        child.setAttendances(attendances);

        return child;
    }

    public static Attendance getAttendanceMock1() {
        var attendance = new Attendance();
        attendance.setEntryDate(LocalDateTime.of(2024,1,2,6,23));
        attendance.setExitDate(LocalDateTime.of(2024,1,2,18,11));

        return attendance;
    }
    public static Attendance getAttendanceMock2() {
        var attendance = new Attendance();
        attendance.setEntryDate(LocalDateTime.of(2024,1,1,6,59));
        attendance.setExitDate(LocalDateTime.of(2024,1,1,12,01));

        return attendance;
    }
    public static Attendance getAttendanceMock3() {
        var attendance = new Attendance();
        attendance.setEntryDate(LocalDateTime.of(2024,1,1,7,00));
        attendance.setExitDate(LocalDateTime.of(2024,1,1,12,01));

        return attendance;
    }
    public static Attendance getAttendanceMock4() {
        var attendance = new Attendance();
        attendance.setEntryDate(LocalDateTime.of(2024,1,1,7,00));
        attendance.setExitDate(LocalDateTime.of(2024,1,1,11,59));

        return attendance;
    }




}
