package it.dantonio.bankingapp.utils;


import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@ToString
@Getter
@Setter
@Accessors(chain = true)
@Service
public class CustomResponse implements Serializable {

  public ResponseEntity<Object> generateResponse(ResponseCode rspCode, Object responseObj) {
    Map<String, Object> map = new HashMap<>();
    map.put("rsp_code", rspCode.getCode());
    map.put("data", responseObj);
    return new ResponseEntity<>(map, HttpStatus.OK);
  }

  public ResponseEntity<Object> generateResponse(ResponseCode rspCode, List<?> responseObj) {
    Map<String, Object> map = new HashMap<>();
    map.put("rsp_code", rspCode.getCode());
    map.put("data", responseObj);
    return new ResponseEntity<>(map, HttpStatus.OK);
  }

  public ResponseEntity<Object> generateResponse(String rspCode, String rspMsg,
      Object responseObj) {
    Map<String, Object> map = new HashMap<>();
    map.put("rsp_msg", rspMsg);
    map.put("rsp_code", rspCode);
    map.put("data", responseObj);

    return new ResponseEntity<>(map, HttpStatus.OK);

  }

}
