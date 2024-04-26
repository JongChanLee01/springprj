package com.validation1.service;

import com.validation1.dto.Professor;
import com.validation1.mapper.ProfessorMapper;
import com.validation1.model.ProfessorEdit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {
    @Autowired
    ProfessorMapper professorMapper;

    public ProfessorEdit findOne(int id){
        Professor professorDto = professorMapper.findOne(id);
        return toEditModel(professorDto);
    }

    public Professor findByOffice(String office) {
        return professorMapper.findByOffice(office);
    }

    public List<Professor> findAll() {
        List<Professor> professors = professorMapper.findAll();
        return professors;
    }

    public void insert(ProfessorEdit professorEdit) {
        Professor professor = toDto(professorEdit);
        professorMapper.insert(professor);
    }

    public void update(ProfessorEdit professorEdit) {
        Professor professor = toDto(professorEdit);
        professorMapper.update(professor);
    }

    public void delete(int id) {
        professorMapper.delete(id);
    }

    public Professor toDto(ProfessorEdit professorEdit) {
        Professor professorDto = new Professor();
        professorDto.setId(professorEdit.getId());
        professorDto.setName(professorEdit.getName());
        professorDto.setDepartmentId(professorEdit.getDepartmentId());
        professorDto.setEmail(professorEdit.getEmail());
        professorDto.setPhone(professorEdit.getPhone());
        professorDto.setOffice(professorEdit.getOffice());
        return professorDto;
    }

    public ProfessorEdit toEditModel(Professor professorDto) {
        ProfessorEdit professorEdit = new ProfessorEdit();
        professorEdit.setId(professorDto.getId());
        professorEdit.setName(professorDto.getName());
        professorEdit.setDepartmentId(professorDto.getDepartmentId());
        professorEdit.setEmail(professorDto.getEmail());
        professorEdit.setPhone(professorDto.getPhone());
        professorEdit.setOffice(professorDto.getOffice());
        return professorEdit;
    }
}
