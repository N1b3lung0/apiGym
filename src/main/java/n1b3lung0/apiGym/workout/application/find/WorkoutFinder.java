package n1b3lung0.apiGym.workout.application.find;

import lombok.RequiredArgsConstructor;
import n1b3lung0.apiGym.common.application.dto.PageResponse;
import n1b3lung0.apiGym.common.application.utils.uuid.UUIDUtils;
import n1b3lung0.apiGym.common.domain.criteria.Page;
import n1b3lung0.apiGym.workout.domain.Workout;
import n1b3lung0.apiGym.workout.domain.WorkoutRepository;
import n1b3lung0.apiGym.workout.domain.exception.WorkoutNotFound;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WorkoutFinder {

    private final WorkoutRepository repository;

    @Transactional(readOnly = true)
    public Workout findById(String id) {
        return repository.findById(UUIDUtils.fromString(id))
                .orElseThrow(() -> new WorkoutNotFound(id));
    }

    @Transactional(readOnly = true)
    public PageResponse<WorkoutResponse> find(WorkoutFindRequest request) {
        Page<Workout> page = repository.find(request.)
    }
}
