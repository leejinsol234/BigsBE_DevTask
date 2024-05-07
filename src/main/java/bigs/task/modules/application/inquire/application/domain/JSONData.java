package bigs.task.modules.application.inquire.application.domain;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@RequiredArgsConstructor
public class JSONData {
    public boolean success = true;

    @NonNull
    private Object data;
    private HttpStatus status = HttpStatus.OK;
}