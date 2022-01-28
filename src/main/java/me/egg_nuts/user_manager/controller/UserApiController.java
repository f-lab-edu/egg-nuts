package me.egg_nuts.user_manager.controller;



import me.egg_nuts.user_manager.defined.USER_URL_DEFINED;
import me.egg_nuts.user_manager.dto.UserDto;

import me.egg_nuts.user_manager.message.NoErrorCodeDetailEnum;
import me.egg_nuts.user_manager.message.NoErrorMessage;
import me.egg_nuts.user_manager.message.Response;
import me.egg_nuts.user_manager.service.UserService;
import io.swagger.annotations.ApiModelProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(USER_URL_DEFINED.USER_BASE_URL)
public class UserApiController {

    private final UserService userService;




    @PostMapping
    @ApiModelProperty(value="회원가입")
    public ResponseEntity userJoin(@RequestBody UserDto userDto){

        userService.join(userDto);

        NoErrorCodeDetailEnum noErrorCodeDetailEnum = NoErrorCodeDetailEnum.getResponse(NoErrorMessage.JOIN_OK);
        Response response = new Response.
                ResponseBuilder()
                .statusCode(noErrorCodeDetailEnum.getStatusCode())
                .responseText(noErrorCodeDetailEnum.getResponseText())
                .builder();


         return new ResponseEntity<>(response, HttpStatus.resolve(noErrorCodeDetailEnum.getStatusCode()));
    }


    @PutMapping
    @ApiModelProperty(value="사용자 상태 변경",notes = "모든 사용자 정보를 다 넘겨줘야 한다..")
    public ResponseEntity modifyUser(@RequestBody UserDto userDto){

        userService.modifyUser(userDto);

        NoErrorCodeDetailEnum noErrorCodeDetailEnum = NoErrorCodeDetailEnum.getResponse(NoErrorMessage.LEAVE_OK);
        Response response = new Response.
                ResponseBuilder()
                .statusCode(noErrorCodeDetailEnum.getStatusCode())
                .responseText(noErrorCodeDetailEnum.getResponseText())
                .builder();


        return new ResponseEntity<>(response, HttpStatus.resolve(noErrorCodeDetailEnum.getStatusCode()));

    }


    @ApiModelProperty(value = "사용자 조회")
    @GetMapping(value=USER_URL_DEFINED.USER_SEARCH_BY_ID)
    public ResponseEntity userSearchById(@PathVariable String user_id){


        UserDto userDto = userService.userSearchById(user_id);

        NoErrorCodeDetailEnum noErrorCodeDetailEnum = NoErrorCodeDetailEnum.getResponse(NoErrorMessage.UNKNOWN);
        Response response = new Response.
                ResponseBuilder()
                .statusCode(noErrorCodeDetailEnum.getStatusCode())
                .responseText(noErrorCodeDetailEnum.getResponseText())
                .result(userDto)
                .builder();


        return new ResponseEntity<>(response, HttpStatus.resolve(noErrorCodeDetailEnum.getStatusCode()));

    }





}
