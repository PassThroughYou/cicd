package gna.crud.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@JsonInclude(JsonInclude.Include.NON_NULL) // null 필드 노출 X
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Success<T> implements Result {
    private T data;
}


