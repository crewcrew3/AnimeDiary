package itis.animediary.controller.responsemodel;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ResponseModel {
    private Map<String, Object> responseMap;
    public ResponseModel() {
        responseMap = new HashMap<>();
    }
}
