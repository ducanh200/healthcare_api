package com.example.healthcare_api.service;

import com.example.healthcare_api.dto.TestDTO;
import com.example.healthcare_api.entities.Device;
import com.example.healthcare_api.entities.Doctor;
import com.example.healthcare_api.entities.Result;
import com.example.healthcare_api.entities.Test;
import com.example.healthcare_api.repositories.DeviceRepository;
import com.example.healthcare_api.repositories.DoctorRepository;
import com.example.healthcare_api.repositories.ResultRepository;
import com.example.healthcare_api.repositories.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestService {
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private ResultRepository resultRepository;

    public List<TestDTO> getAllTests() {
        List<Test> tests = testRepository.findAll();
        List<TestDTO> testDTOs = new ArrayList<>(tests.size());

        tests.forEach(test -> {
            TestDTO testDTO = new TestDTO();
            testDTO.setId(test.getId());
            testDTO.setDiagnose(test.getDiagnose());
            testDTO.setThumbnail(test.getThumbnail());
            testDTO.setTestAt(test.getTestAt());
            testDTO.setExpense(test.getExpense());
            testDTO.setDoctorId(test.getDoctor().getId());
            testDTO.setDeviceId(test.getDevice().getId());
            testDTO.setResultId(test.getResult().getId());

            testDTOs.add(testDTO);
        });

        return testDTOs;
    }


    public TestDTO createTest(@RequestBody TestDTO testDTO) {
        // Tạo một đối tượng Test mới từ DTO
        Test test = new Test();
        test.setDiagnose(testDTO.getDiagnose());
        test.setThumbnail(testDTO.getThumbnail());
        test.setTestAt(testDTO.getTestAt());
        test.setExpense(testDTO.getExpense());

        // Tìm và ánh xạ đối tượng Device từ DTO
        Device device = deviceRepository.findById(testDTO.getDeviceId())
                .orElseThrow(() -> new IllegalArgumentException("Device not found with id: " + testDTO.getDeviceId()));
        test.setDevice(device);

        // Tìm và ánh xạ đối tượng Doctor từ DTO
        Doctor doctor = doctorRepository.findById(testDTO.getDoctorId())
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found with id: " + testDTO.getDoctorId()));
        test.setDoctor(doctor);

        // Tìm và ánh xạ đối tượng Result từ DTO
        Result result = resultRepository.findById(testDTO.getResultId())
                .orElseThrow(() -> new IllegalArgumentException("Result not found with id: " + testDTO.getResultId()));
        test.setResult(result);

        // Lưu đối tượng Test mới vào cơ sở dữ liệu
        Test savedTest = testRepository.save(test);

        // Tạo DTO từ đối tượng Test đã lưu
        TestDTO savedTestDTO = new TestDTO();
        savedTestDTO.setId(savedTest.getId());
        savedTestDTO.setDiagnose(savedTest.getDiagnose());
        savedTestDTO.setThumbnail(savedTest.getThumbnail());
        savedTestDTO.setTestAt(savedTest.getTestAt());
        savedTestDTO.setExpense(savedTest.getExpense());
        savedTestDTO.setDoctorId(savedTest.getDoctor().getId());
        savedTestDTO.setDeviceId(savedTest.getDevice().getId());
        savedTestDTO.setResultId(savedTest.getResult().getId());

        // Thực hiện các thao tác khác nếu cần và set các thông tin vào DTO

        return savedTestDTO;
    }
}
