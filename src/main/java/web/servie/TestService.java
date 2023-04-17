package web.servie;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import web.models.TestModel;
import web.repository.TestRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {
    private final TestRepository autoRepository;

    public List<TestModel> findAll() {
        return autoRepository.findAll();
    }

    public void saveTest(TestModel testModel) {
        autoRepository.save(testModel);
    }

    public void deleteTest(Long id) {
        autoRepository.deleteById(id);
    }

    public TestModel getTestById(Long id) {
        return autoRepository.findById(id).orElse(null);
    }
}
